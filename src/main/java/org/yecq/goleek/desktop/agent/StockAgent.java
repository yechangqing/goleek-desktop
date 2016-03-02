package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import java.util.HashMap;
import java.util.Map;
import org.yecq.goleek.desktop.bean.param.StockAddBean;
import org.yecq.goleek.desktop.bean.param.StockModifyBean;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class StockAgent extends AgentBase {

    //复写路径
//    @Override
//    public String getUrlString() {
//        return super.getUrlString() + "stock/";
//    }
    public Sret getExchangeNames() {
        Sret sr = rest.get4Object(getUrlString() + "/stock_exchange_names", String[].class);
//        String json = HttpUtil.post(getUrlString() + "get_exchange_names.go", null);
//        Sret sr = getSret(json, String.class);
        return sr;
    }

    public Sret getListAll() {
        Sret sr = rest.getList4Object(getUrlString() + "/stocks", StockInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, StockInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        Sret sr = rest.getList4Object(getUrlString() + "/stocks_interested", StockInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_list_interested.go", null);
//        Sret sr = getSret(json, StockInfoBean.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret add(StockAddBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.post(getUrlString() + "/stocks", map);
//        String json = HttpUtil.post(getUrlString() + "add.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret remove(String id) {
        Sret sr = rest.delete(getUrlString() + "/stocks/{id}", id);
//        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret modify(String id, StockModifyBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/stocks/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret interest(String id) {
        StockModifyBean bean = new StockModifyBean();
        bean.setInterest("y");
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/stocks/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret unInterest(String id) {
        StockModifyBean bean = new StockModifyBean();
        bean.setInterest("n");
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/stocks/{id}", map, id);
//        String json = HttpUtil.post(getUrlString() + "un_interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"stock"})
    public Sret interestAll() {
        Map map = new HashMap();
        map.put("action", "select");
        Sret sr = rest.put(getUrlString() + "/stocks_interest", map);
//        String json = HttpUtil.post(getUrlString() + "interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"stock"})
    public Sret unInterestAll() {
        Map map = new HashMap();
        map.put("action", "unselect");
        Sret sr = rest.put(getUrlString() + "/stocks_interest", map);
//        String json = HttpUtil.post(getUrlString() + "un_interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }
}
