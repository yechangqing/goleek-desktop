package org.yecq.goleek.desktop.agent;

import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.RestUtil;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.bean.param.AccountAddBean;
import org.yecq.goleek.desktop.bean.param.AccountModifyBean;
import org.yecq.goleek.desktop.bean.param.AccountRemoveBean;
import org.yecq.goleek.desktop.bean.param.AccountUnuseBean;
import org.yecq.goleek.desktop.bean.param.AccountUseBean;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.yecq.goleek.desktop.bean.result.AccountStockInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class AccountAgent extends AgentBase {

    //复写路径
    @Override
    public String getUrlString() {
        return super.getUrlString() + "account/";
    }

    public Sret getAccountListFutures() {
        Sret sr = rest.getList4Object(null, null);
//        String json = RestUtil.(getUrlString() + "get_list_futures.go", null);
//        Sret sr = getSret(json, AccountFuturesInfoBean.class);
        return sr;
    }

    public Sret getAccountListFuturesUsed() {
        Sret sr=rest.getList4Object(null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_futures_used.go", null);
//        Sret sr = getSret(json, AccountFuturesInfoBean.class);
        return sr;
    }

    @Notify({"account"})
    public Sret add(AccountAddBean bean) {
        Sret sr=rest.post(null, null);
//        String json = HttpUtil.post(getUrlString() + "add.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"account"})
    public Sret remove(AccountRemoveBean bean) {
        Sret sr=rest.delete(null, null);
//        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"account"})
    public Sret modify(AccountModifyBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"account"})
    public Sret use(AccountUseBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "use.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"account"})
    public Sret unUse(AccountUnuseBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "un_use.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getAccountListStock() {
        Sret sr=rest.getList4Object(null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_stock.go", null);
//        Sret sr = getSret(json, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getAccountListStockUsed() {
        Sret sr=rest.getList4Object(null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_stock_used.go", null);
//        Sret sr = getSret(json, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getMoneyFutures() {
        Sret sr=rest.get4Object(null,null,null);
//        String json = HttpUtil.post(getUrlString() + "get_money_futures.go", null);
//        Sret sr = getSret(json, Double.class);
        return sr;
    }

    public Sret getMoneyStock() {
        Sret sr=rest.get4Object(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "get_money_stock.go", null);
//        Sret sr = getSret(json, Double.class);
        return sr;
    }
}
