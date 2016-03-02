package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import java.util.HashMap;
import java.util.Map;
import org.yecq.goleek.desktop.bean.param.FuturesAddBean;
import org.yecq.goleek.desktop.bean.param.FuturesCloneBean;
import org.yecq.goleek.desktop.bean.param.FuturesModifyBean;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class FuturesAgent extends AgentBase {

    //复写路径
//    @Override
//    public String getUrlString() {
//        return super.getUrlString() + "futures/";
//    }
    public Sret getExchangeNames() {
        Sret sr = rest.get4Object(getUrlString() + "/futures_exchange_names", String[].class);
//        String json = HttpUtil.post(getUrlString() + "get_exchange_names.go", null);
//        Sret sr = getSret(json, String.class);
        return sr;
    }

    public Sret getListAll() {
        Sret sr = rest.getList4Object(getUrlString() + "/futures", FuturesInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, FuturesInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        Sret sr = rest.getList4Object(getUrlString() + "/futures_interested", FuturesInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_list_interested.go", null);
//        Sret sr = getSret(json, FuturesInfoBean.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret add(FuturesAddBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.post(getUrlString() + "/futures", map);
//        String json = HttpUtil.post(getUrlString() + "add.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret remove(String id) {
        Sret sr = rest.delete(getUrlString() + "/futures/{id}", id);
//        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret cloneItself(String id, FuturesCloneBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.post(getUrlString() + "/futures/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "clone.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret modify(String id, FuturesModifyBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/futures/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret interest(String id) {
        FuturesModifyBean bean = new FuturesModifyBean();
        bean.setInterest("y");
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/futures/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret unInterest(String id) {
        FuturesModifyBean bean = new FuturesModifyBean();
        bean.setInterest("n");
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/futures/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "un_interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"futures"})
    public Sret interestAll() {
        Map map = new HashMap();
        map.put("action", "select");
        Sret sr = rest.put(getUrlString() + "/futures_interest", map);
//        String json = HttpUtil.post(getUrlString() + "interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"futures"})
    public Sret unInterestAll() {
        Map map = new HashMap();
        map.put("action", "unselect");
        Sret sr = rest.put(getUrlString() + "/futures_interest", map);
//        String json = HttpUtil.post(getUrlString() + "un_interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }
}
