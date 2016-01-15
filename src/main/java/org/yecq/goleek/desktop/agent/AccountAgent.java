package org.yecq.goleek.desktop.agent;

import org.springframework.stereotype.Component;
import org.yecq.goleek.desktop.aspect.Notify;
import org.yecq.goleek.desktop.bean.param.AccountAddBean;
import org.yecq.goleek.desktop.bean.param.AccountModifyBean;
import org.yecq.goleek.desktop.bean.param.AccountRemoveBean;
import org.yecq.goleek.desktop.bean.param.AccountUnuseBean;
import org.yecq.goleek.desktop.bean.param.AccountUseBean;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.yecq.goleek.desktop.bean.result.AccountStockInfoBean;
import org.yecq.goleek.desktop.service.Sret;

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
        String json = HttpUtil.post(getUrlString() + "get_list_futures.go", null);
        Sret sr = getSret(json, AccountFuturesInfoBean.class);
        return sr;
    }

    public Sret getAccountListFuturesUsed() {
        String json = HttpUtil.post(getUrlString() + "get_list_futures_used.go", null);
        Sret sr = getSret(json, AccountFuturesInfoBean.class);
        return sr;
    }

    @Notify({"account"})
    public Sret add(AccountAddBean bean) {
        String json = HttpUtil.post(getUrlString() + "add.go", bean);
        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"account"})
    public Sret remove(AccountRemoveBean bean) {
        String json = HttpUtil.post(getUrlString() + "remove.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"account"})
    public Sret modify(AccountModifyBean bean) {
        String json = HttpUtil.post(getUrlString() + "modify.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"account"})
    public Sret use(AccountUseBean bean) {
        String json = HttpUtil.post(getUrlString() + "use.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"account"})
    public Sret unUse(AccountUnuseBean bean) {
        String json = HttpUtil.post(getUrlString() + "un_use.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getAccountListStock() {
        String json = HttpUtil.post(getUrlString() + "get_list_stock.go", null);
        Sret sr = getSret(json, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getAccountListStockUsed() {
        String json = HttpUtil.post(getUrlString() + "get_list_stock_used.go", null);
        Sret sr = getSret(json, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getMoneyFutures() {
        String json = HttpUtil.post(getUrlString() + "get_money_futures.go", null);
        Sret sr = getSret(json, Double.class);
        return sr;
    }

    public Sret getMoneyStock() {
        String json = HttpUtil.post(getUrlString() + "get_money_stock.go", null);
        Sret sr = getSret(json, Double.class);
        return sr;
    }
}
