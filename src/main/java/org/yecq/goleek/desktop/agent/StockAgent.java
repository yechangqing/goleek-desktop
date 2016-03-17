package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.StockAddBean;
import org.yecq.goleek.desktop.bean.param.StockModifyBean;
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
        Sret sr = getSretObject(json, String[].class);
        return sr;
    }

    public Sret getListAll() {
        String json = HttpUtil.post(getUrlString() + "get_list_all.php", null);
        Sret sr = getSretList(json, StockInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        String json = HttpUtil.post(getUrlString() + "get_list_interested.php", null);
        Sret sr = getSretList(json, StockInfoBean.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret add(StockAddBean bean) {
        String json = HttpUtil.post(getUrlString() + "add.php", bean);
        Sret sr = getSretObject(json, String.class);
        return sr;
    }

    @Notify({"stock"})
    public Sret remove(String id) {
        String json = HttpUtil.post(getUrlString() + "remove.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"stock"})
    public Sret modify(String id, StockModifyBean bean) {
        String json = HttpUtil.post(getUrlString() + "modify.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"stock"})
    public Sret interest(String id) {
        String json = HttpUtil.post(getUrlString() + "interest.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"stock"})
    public Sret unInterest(String id) {
        String json = HttpUtil.post(getUrlString() + "un_interest.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"stock"})
    public Sret interestAll() {
        String json = HttpUtil.post(getUrlString() + "interest_all.php", null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"stock"})
    public Sret unInterestAll() {
        String json = HttpUtil.post(getUrlString() + "un_interest_all.php", null);
        Sret sr = getSretOnly(json);
        return sr;
    }
}
