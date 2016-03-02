package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import java.util.HashMap;
import java.util.Map;
import org.yecq.goleek.desktop.bean.param.PositionStockCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionStockEditBean;
import org.yecq.goleek.desktop.bean.param.PositionStockOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionStockInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class PositionStockAgent extends AgentBase {

    //复写路径
//    @Override
//    public String getUrlString() {
//        return super.getUrlString() + "position_stock/";
//    }
    public Sret getListAll() {
        Sret sr = rest.getList4Object(getUrlString() + "/position_stocks", PositionStockInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, PositionStockInfoBean.class);
        return sr;
    }

    @Notify({"position_stock"})
    public Sret editQuit(String id, PositionStockEditBean bean) {
        Map map = new HashMap();
        map.put("type", "edit");
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/position_stocks/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "edit_quit.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret open(PositionStockOpenBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.post(getUrlString() + "/position_stocks", map);
//        String json = HttpUtil.post(getUrlString() + "open.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret close(String id, PositionStockCloseBean bean) {
        Map map = new HashMap();
        map.put("type", "close");
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/position_stocks/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "close.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret delete(String id) {
        Sret sr = rest.delete(getUrlString() + "/position_stocks/{id}", id);
//        String json = HttpUtil.post(getUrlString() + "delete.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getActions() {
        Sret sr = rest.get4Object(getUrlString() + "/position_stocks_actions", String[].class);
//        String json = HttpUtil.post(getUrlString() + "get_actions.go", null);
//        Sret sr = getSret(json, String.class);
        return sr;
    }
}
