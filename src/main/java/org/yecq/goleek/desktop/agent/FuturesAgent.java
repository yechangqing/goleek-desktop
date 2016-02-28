package org.yecq.goleek.desktop.agent;

import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.bean.param.FuturesAddBean;
import org.yecq.goleek.desktop.bean.param.FuturesCloneBean;
import org.yecq.goleek.desktop.bean.param.FuturesInterestBean;
import org.yecq.goleek.desktop.bean.param.FuturesModifyBean;
import org.yecq.goleek.desktop.bean.param.FuturesRemoveBean;
import org.yecq.goleek.desktop.bean.param.FuturesUninterestBean;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.springframework.stereotype.Component;

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
        Sret sr = rest.get4Object(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "get_exchange_names.go", null);
//        Sret sr = getSret(json, String.class);
        return sr;
    }

    public Sret getListAll() {
        Sret sr = rest.get4Object(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, FuturesInfoBean.class);
        return sr;
    }

    public Sret getListInterested() {
        Sret sr = rest.get4Object(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_interested.go", null);
//        Sret sr = getSret(json, FuturesInfoBean.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret add(FuturesAddBean bean) {
        Sret sr = rest.post(null, null);
//        String json = HttpUtil.post(getUrlString() + "add.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"futures"})
    public Sret remove(FuturesRemoveBean bean) {
        Sret sr = rest.delete(null, null);
//        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret cloneItself(FuturesCloneBean bean) {
        Sret sr = rest.post(null, null);
//        String json = HttpUtil.post(getUrlString() + "clone.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret modify(FuturesModifyBean bean) {
        Sret sr = rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret interest(FuturesInterestBean bean) {
        Sret sr = rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"futures"})
    public Sret unInterest(FuturesUninterestBean bean) {
        Sret sr = rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "un_interest.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，感兴趣所有合约
    @Notify({"futures"})
    public Sret interestAll() {
        Sret sr = rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }

    // v1.1增加，取消感兴趣所有合约
    @Notify({"futures"})
    public Sret unInterestAll() {
        Sret sr = rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "un_interest_all.go", null);
//        Sret sr = getSret(json, null);
        return sr;
    }
}
