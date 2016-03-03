package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.StockAddBean;
import org.yecq.goleek.desktop.bean.param.StockInterestBean;
import org.yecq.goleek.desktop.bean.param.StockModifyBean;
import org.yecq.goleek.desktop.bean.param.StockRemoveBean;
import org.yecq.goleek.desktop.bean.param.StockUninterestBean;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Notify;
import org.yecq.baseframework.plain.service.Sret;

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
        String json = HttpUtil.post(getUrlString() + "get_exchange_names.php", null);
        Sret sr = getSret(json, String.class);
        return sr;
    }

    public Sret getListAll() {
        String json = HttpUtil.post(getUrlString() + "get_list_all.php", null);
        Sret sr = getSret(json, StockInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        String json = HttpUtil.post(getUrlString() + "get_list_interested.php", null);
        Sret sr = getSret(json, StockInfoBean.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret add(StockAddBean bean) {
        String json = HttpUtil.post(getUrlString() + "add.php", bean);
        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret remove(StockRemoveBean bean) {
        String json = HttpUtil.post(getUrlString() + "remove.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret modify(StockModifyBean bean) {
        String json = HttpUtil.post(getUrlString() + "modify.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret interest(StockInterestBean bean) {
        String json = HttpUtil.post(getUrlString() + "interest.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"stock"})
    public Sret unInterest(StockUninterestBean bean) {
        String json = HttpUtil.post(getUrlString() + "un_interest.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"stock"})
    public Sret interestAll() {
        String json = HttpUtil.post(getUrlString() + "interest_all.php", null);
        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"stock"})
    public Sret unInterestAll() {
        String json = HttpUtil.post(getUrlString() + "un_interest_all.php", null);
        Sret sr = getSret(json, null);
        return sr;
    }
}
