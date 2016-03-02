package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import java.util.HashMap;
import java.util.Map;
import org.yecq.goleek.desktop.bean.param.PositionFuturesActionsBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesEditBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class PositionFuturesAgent extends AgentBase {

    //复写路径
//    @Override
//    public String getUrlString() {
//        return super.getUrlString() + "position_futures/";
//    }
    public Sret getListAll() {
        Sret sr = rest.getList4Object(getUrlString() + "/position_futures", PositionFuturesInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, PositionFuturesInfoBean.class);
        return sr;
    }

    @Notify({"position_futures"})
    public Sret editQuit(String id, PositionFuturesEditBean bean) {
        Map map = new HashMap();
        map.put("type", "edit");
        map.put("json", new Gson().toJson(bean));

        Sret sr = rest.put(getUrlString() + "/position_futures/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "edit_quit.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret open(PositionFuturesOpenBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.post(getUrlString() + "/position_futures", map);
//        String json = HttpUtil.post(getUrlString() + "open.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret close(String id, PositionFuturesCloseBean bean) {
        Map map = new HashMap();
        map.put("type", "close");
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/position_futures/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "close.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret delete(String id) {
        Sret sr = rest.delete(getUrlString() + "/position_futures/{id}", id);
//        String json = HttpUtil.post(getUrlString() + "delete.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getActions(PositionFuturesActionsBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.get4Object(getUrlString() + "/position_futures_actions", map, String[].class);
//        String json = HttpUtil.post(getUrlString() + "get_actions.go", bean);
//        Sret sr = getSret(json, String.class);
        return sr;
    }

}
