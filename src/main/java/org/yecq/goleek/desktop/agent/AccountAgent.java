package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import java.util.HashMap;
import java.util.Map;
import org.yecq.goleek.desktop.bean.param.AccountAddBean;
import org.yecq.goleek.desktop.bean.param.AccountModifyBean;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.goleek.desktop.bean.result.AccountStockInfoBean;

/**
 *
 * @author yecq
 */
@Component
public class AccountAgent extends AgentBase {

    public Sret getAccountListFutures() {
        Map map = new HashMap();
        map.put("type", "futures");
        Sret sr = rest.getList4Object(getUrlString() + "/accounts", map, AccountFuturesInfoBean.class);
        return sr;
    }

    public Sret getAccountListFuturesUsed() {
        Map map = new HashMap();
        map.put("type", "futures");
        Sret sr = rest.getList4Object(getUrlString() + "/accounts_used", map, AccountFuturesInfoBean.class);
        return sr;
    }

    @Notify({"account"})
    public Sret add(AccountAddBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.post(getUrlString() + "/accounts", map);
        return sr;
    }

    @Notify({"account"})
    public Sret remove(String id) {
        Sret sr = rest.delete(getUrlString() + "/accounts/{id}", id);
        return sr;
    }

    @Notify({"account"})
    public Sret modify(String id, AccountModifyBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/accounts/{id}", map, id);
        return sr;
    }

    @Notify({"account"})
    public Sret use(String id) {
        AccountModifyBean bean = new AccountModifyBean();
        bean.setUsed("y");
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/accounts/{id}", map, id);
        return sr;
    }

    @Notify({"account"})
    public Sret unUse(String id) {
        AccountModifyBean bean = new AccountModifyBean();
        bean.setUsed("n");
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/accounts/{id}", map, id);
        return sr;
    }

    public Sret getAccountListStock() {
        Map map = new HashMap();
        map.put("type", "stock");
        Sret sr = rest.getList4Object(getUrlString() + "/accounts", map, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getAccountListStockUsed() {
        Map map = new HashMap();
        map.put("type", "stock");
        Sret sr = rest.getList4Object(getUrlString() + "/accounts_used", map, AccountStockInfoBean.class);
        return sr;
    }

    public Sret getMoneyFutures() {
        Sret sr = rest.get4Object(getUrlString() + "/moneys_futures", Double.class);
        return sr;
    }

    public Sret getMoneyStock() {
        Sret sr = rest.get4Object(getUrlString() + "/moneys_stock", Double.class);
        return sr;
    }
}
