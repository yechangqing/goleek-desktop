package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.AccountAddBean;
import org.yecq.goleek.desktop.bean.param.AccountModifyBean;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.yecq.goleek.desktop.bean.result.AccountStockInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Notify;
import org.yecq.baseframework.plain.service.Sret;

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
        String json = HttpUtil.post(getUrlString() + "get_list_futures.php", null);
        Sret sr = getSretList(json, AccountFuturesInfoBean.class);
        return sr;
    }

    public Sret getAccountListFuturesUsed() {
        String json = HttpUtil.post(getUrlString() + "get_list_futures_used.php", null);
        Sret sr = getSretList(json, AccountFuturesInfoBean.class);
        return sr;
    }

    @Notify({"account"})
    public Sret add(AccountAddBean bean) {
        String json = HttpUtil.post(getUrlString() + "add.php", bean);
        Sret sr = getSretObject(json, String.class);
        return sr;
    }

    @Notify({"account"})
    public Sret remove(String id) {
        String json = HttpUtil.post(getUrlString() + "remove.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"account"})
    public Sret modify(String id, AccountModifyBean bean) {
        String json = HttpUtil.post(getUrlString() + "modify.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"account"})
    public Sret use(String id) {
        String json = HttpUtil.post(getUrlString() + "use.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"account"})
    public Sret unUse(String id) {
        String json = HttpUtil.post(getUrlString() + "un_use.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    public Sret getAccountListStock() {
        String json = HttpUtil.post(getUrlString() + "get_list_stock.php", null);
        Sret sr = getSretList(json, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getAccountListStockUsed() {
        String json = HttpUtil.post(getUrlString() + "get_list_stock_used.php", null);
        Sret sr = getSretList(json, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getMoneyFutures() {
        String json = HttpUtil.post(getUrlString() + "get_money_futures.php", null);
        Sret sr = getSretObject(json, Double.class);
        return sr;
    }

    public Sret getMoneyStock() {
        String json = HttpUtil.post(getUrlString() + "get_money_stock.php", null);
        Sret sr = getSretObject(json, Double.class);
        return sr;
    }
}
