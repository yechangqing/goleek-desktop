package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.FuturesAddBean;
import org.yecq.goleek.desktop.bean.param.FuturesCloneBean;
import org.yecq.goleek.desktop.bean.param.FuturesModifyBean;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Notify;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
@Component
public class FuturesAgent extends AgentBase {

    //复写路径
    @Override
    public String getUrlString() {
        return super.getUrlString() + "futures/";
    }

    public Sret getExchangeNames() {
        String json = HttpUtil.post(getUrlString() + "get_exchange_names.php", null);
        Sret sr = getSretObject(json, String[].class);
        return sr;
    }

    public Sret getListAll() {
        String json = HttpUtil.post(getUrlString() + "get_list_all.php", null);
        Sret sr = getSretList(json, FuturesInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        String json = HttpUtil.post(getUrlString() + "get_list_interested.php", null);
        Sret sr = getSretList(json, FuturesInfoBean.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret add(FuturesAddBean bean) {
        String json = HttpUtil.post(getUrlString() + "add.php", bean);
        Sret sr = getSretObject(json, String.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret remove(String id) {
        String json = HttpUtil.post(getUrlString() + "remove.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"futures"})
    public Sret cloneItself(String id, FuturesCloneBean bean) {
        String json = HttpUtil.post(getUrlString() + "clone.php", id, bean);
        Sret sr = getSretObject(json, String.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret modify(String id, FuturesModifyBean bean) {
        String json = HttpUtil.post(getUrlString() + "modify.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"futures"})
    public Sret interest(String id) {
        String json = HttpUtil.post(getUrlString() + "interest.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"futures"})
    public Sret unInterest(String id) {
        String json = HttpUtil.post(getUrlString() + "un_interest.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"futures"})
    public Sret interestAll() {
        String json = HttpUtil.post(getUrlString() + "interest_all.php", null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"futures"})
    public Sret unInterestAll() {
        String json = HttpUtil.post(getUrlString() + "un_interest_all.php", null);
        Sret sr = getSretOnly(json);
        return sr;
    }
}
