package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.FuturesAddBean;
import org.yecq.goleek.desktop.bean.param.FuturesCloneBean;
import org.yecq.goleek.desktop.bean.param.FuturesInterestBean;
import org.yecq.goleek.desktop.bean.param.FuturesModifyBean;
import org.yecq.goleek.desktop.bean.param.FuturesRemoveBean;
import org.yecq.goleek.desktop.bean.param.FuturesUninterestBean;
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
        String json = HttpUtil.post(getUrlString() + "get_exchange_names.go", null);
        Sret sr = getSret(json, String.class);
        return sr;
    }

    public Sret getListAll() {
        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
        Sret sr = getSret(json, FuturesInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        String json = HttpUtil.post(getUrlString() + "get_list_interested.go", null);
        Sret sr = getSret(json, FuturesInfoBean.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret add(FuturesAddBean bean) {
        String json = HttpUtil.post(getUrlString() + "add.go", bean);
        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret remove(FuturesRemoveBean bean) {
        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret cloneItself(FuturesCloneBean bean) {
        String json = HttpUtil.post(getUrlString() + "clone.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret modify(FuturesModifyBean bean) {
        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret interest(FuturesInterestBean bean) {
        String json = HttpUtil.post(getUrlString() + "interest.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret unInterest(FuturesUninterestBean bean) {
        String json = HttpUtil.post(getUrlString() + "un_interest.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"futures"})
    public Sret interestAll() {
        String json = HttpUtil.post(getUrlString() + "interest_all.go", null);
        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"futures"})
    public Sret unInterestAll() {
        String json = HttpUtil.post(getUrlString() + "un_interest_all.go", null);
        Sret sr = getSret(json, null);
        return sr;
    }
}
