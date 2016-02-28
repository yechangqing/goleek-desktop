package org.yecq.goleek.desktop.agent;

import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.bean.param.StockAddBean;
import org.yecq.goleek.desktop.bean.param.StockInterestBean;
import org.yecq.goleek.desktop.bean.param.StockModifyBean;
import org.yecq.goleek.desktop.bean.param.StockRemoveBean;
import org.yecq.goleek.desktop.bean.param.StockUninterestBean;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class StockAgent extends AgentBase {

    //复写路径
    @Override
    public String getUrlString() {
        return super.getUrlString() + "stock/";
    }

    public Sret getExchangeNames() {
        Sret sr=rest.getList4Object(null, null);
//        String json = HttpUtil.post(getUrlString() + "get_exchange_names.go", null);
//        Sret sr = getSret(json, String.class);
        return sr;
    }

    public Sret getListAll() {
        Sret sr=rest.getList4Object(null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, StockInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        Sret sr=rest.getList4Object(null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_interested.go", null);
//        Sret sr = getSret(json, StockInfoBean.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret add(StockAddBean bean) {
        Sret sr=rest.post(null, null);
//        String json = HttpUtil.post(getUrlString() + "add.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret remove(StockRemoveBean bean) {
        Sret sr=rest.delete(null, null);
//        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret modify(StockModifyBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret interest(StockInterestBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret unInterest(StockUninterestBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "un_interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"stock"})
    public Sret interestAll() {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"stock"})
    public Sret unInterestAll() {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "un_interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }
}
