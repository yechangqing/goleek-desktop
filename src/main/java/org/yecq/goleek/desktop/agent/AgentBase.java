package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.yecq.baseframework.plain.core.CoreChangeNotifier;
import org.yecq.baseframework.plain.core.Root;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;

/**
 *
 * @author yecq
 */
class AgentBase {

    @Autowired
    protected IpPort addr;

    // 在使用xml进行配置时，参数注入需要有显示的get和set方法，在注解配置里是不需要的
    public IpPort getAddr() {
        return addr;
    }

    public void setAddr(IpPort addr) {
        this.addr = addr;
    }

//     返回url的基础路径，具体的应用在后面加上字段
    protected String getUrlString() {
        String ip = addr.getIp().trim();
        if (ip.endsWith("/")) {
            ip = ip.substring(0, ip.length() - 1);
        }
        String port = addr.getPort().trim();
        if (!port.equals("")) {
            ip += ":" + port;
        }
        String app = addr.getApp().trim();
        if (!app.equals("")) {
            ip += "/" + app;
        }

        return "http://" + ip + "/";
    }

    protected void fireCoreChange(String[] names) {
        Root.getInstance().getBean(CoreChangeNotifier.class).fireCoreChange(names);
    }

    // 从返回的json中得到对象列表
//    protected <T> Sret getSret(String json, Class<T> t) {
//        Sret sr = new Sret();
//        try {
//            Gson gson = new Gson();
//            List list = gson.fromJson(json, List.class);
//            // 先取第一个元素
//            Map mp = (Map) list.get(0);
//            String status = (String) mp.get("status");
//            String message = (String) mp.get("message");
//            if (status.equals("ok")) {
//                sr.setOk(message);
//            } else if (status.equals("fail")) {
//                sr.setFail(message);
//            } else if (status.equals("error")) {
//                sr.setError(message);
//            } else {
//                throw new Exception("");
//            }
//
//            if (!sr.isOk()) {
//                return sr;
//            }
//            int len = list.size();
//            List ret = new LinkedList();
//            for (int i = 1; i < len; i++) {
//                Object o = list.get(i);
//                if (o instanceof Map) {
//                    Map map = (Map) o;
//                    ret.add(new Gson().fromJson(new Gson().toJson(map), t));
//                } else if (o instanceof List) {
//                    // 这是针对返回的是数组的情况，标记一下创建泛型数组的方法
//                    List arr = (List) o;
//
//                    T[] tmp = (T[]) Array.newInstance(t, arr.size());
//                    for (int k = 0; k < tmp.length; k++) {
//                        tmp[k] = new Gson().fromJson(new Gson().toJson(arr.get(k)), t);
//                    }
//                    sr.setData(tmp);
//                    return sr;
//                } else {
//                    // 针对返回一般对象的情况
//                    if (t == Integer.class) {
//                        sr.setData((int) ((Double) o).doubleValue());
//                    } else {
//                        sr.setData(o);
//                    }
//                    return sr;
//                }
//            }
//            sr.setData(ret);
//        } catch (Throwable e) {
//            sr = new Sret();
//            sr.setError("系统错误");
//        }
//        return sr;
//    }
// 指定只返回一个元素，应付某些特殊情况
//    protected <T> Sret getSretSingle(String json, Class<T> t) {
//        Sret sr = new Sret();
//        try {
//            Gson gson = new Gson();
//            List list = gson.fromJson(json, List.class
//            );
//            // 先取第一个元素
//            Map mp = (Map) list.get(0);
//            String status = (String) mp.get("status");
//            String message = (String) mp.get("message");
//            if (status.equals("ok")) {
//                sr.setOk(message);
//            } else if (status.equals("fail")) {
//                sr.setFail(message);
//            } else if (status.equals("error")) {
//                sr.setError(message);
//            } else {
//                throw new Exception("");
//            }
//            if (list.size() == 1) {
//                return sr;  // 有些情况下可能没有东西返回，但并非是错误
//            }
//            Object o = list.get(1);
//            if (o instanceof Map) {
//                Map map = (Map) o;
//                sr.setData(new Gson().fromJson(new Gson().toJson(map), t));
//            } else if (o instanceof List) {
//                // 这是针对返回的是数组的情况
//                List arr = (List) o;
//                T[] tmp = (T[]) Array.newInstance(t, arr.size());
//                for (int k = 0; k < tmp.length; k++) {
//                    tmp[k] = new Gson().fromJson(new Gson().toJson(arr.get(k)), t);
//                }
//                sr.setData(tmp);
//            } else {
//                // 针对返回一般对象的情况
//                if (t == Integer.class) {
//                    sr.setData((int) ((Double) o).doubleValue());
//                } else {
//                    sr.setData(o);
//                }
//            }
//            return sr;
//        } catch (Throwable e) {
//            sr = new Sret();
//            sr.setError("系统错误");
//        }
//        return sr;
//    }
    protected <T> Sret getSretList(String json, Class<T> cls) {
        Sret sr = new Sret();
        try {
            List list = new Gson().fromJson(json, List.class);
            // 先取头部
            Map mp = (Map) list.get(0);
            String status = (String) mp.get("status");
            String message = (String) mp.get("message");
            if (status.equals("ok")) {
                sr.setOk(message);
            } else if (status.equals("fail")) {
                sr.setFail(message);
            } else if (status.equals("error")) {
                sr.setError(message);
            } else {
                throw new Exception("");
            }

            if (!sr.isOk()) {
                return sr;
            }

            // 剩下的取列表
            if (list.size() == 1) {
                sr.setData(new LinkedList());
                return sr;
            }
            List li1 = new Gson().fromJson(new Gson().toJson(list.get(1)), List.class);
            List ret = new LinkedList();
            Iterator ite = li1.iterator();
            while (ite.hasNext()) {
                String tmp = new Gson().toJson(ite.next());
                ret.add(new Gson().fromJson(tmp, cls));
            }
            sr.setData(ret);
        } catch (Exception ex) {
            sr.setError("json解析错误");
            sr.setData(null);
        }
        return sr;
    }

    protected <T> Sret getSretObject(String json, Class<T> cls) {
        Sret sr = new Sret();
        try {
            List list = new Gson().fromJson(json, List.class);
            // 先取头部
            Map mp = (Map) list.get(0);
            String status = (String) mp.get("status");
            String message = (String) mp.get("message");
            if (status.equals("ok")) {
                sr.setOk(message);
            } else if (status.equals("fail")) {
                sr.setFail(message);
            } else if (status.equals("error")) {
                sr.setError(message);
            } else {
                throw new Exception("");
            }

            if (!sr.isOk()) {
                return sr;
            }

            // 剩下的取元素
            if (list.size() == 1) {
                sr.setData(null);
                return sr;
            }

            sr.setData(new Gson().fromJson(new Gson().toJson(list.get(1)), cls));
        } catch (Exception ex) {
            sr.setError("json解析错误");
            sr.setData(null);
        }
        return sr;
    }

    protected Sret getSretOnly(String json) {
        Sret sr = new Sret();
        try {
            List list = new Gson().fromJson(json, List.class);
            // 先取头部
            Map mp = (Map) list.get(0);
            String status = (String) mp.get("status");
            String message = (String) mp.get("message");
            if (status.equals("ok")) {
                sr.setOk(message);
            } else if (status.equals("fail")) {
                sr.setFail(message);
            } else if (status.equals("error")) {
                sr.setError(message);
            } else {
                throw new Exception("");
            }
        } catch (Exception ex) {
            sr.setError("json解析错误");
            sr.setData(null);
        }
        return sr;
    }
}
