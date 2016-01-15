package org.yecq.goleek.desktop.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yecq.goleek.desktop.agent.AccountAgent;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.CoreChangeListener;
import org.yecq.goleek.desktop.view.Vutil;

/**
 *
 * @author yecq
 */
@Component
public class AccountFuturesCache extends CacheBase implements CoreChangeListener {

    @Autowired
    private AccountAgent agent;

    private List<AccountFuturesInfoBean> all;
    private List<AccountFuturesInfoBean> used;
    private double money;

    public List<AccountFuturesInfoBean> getAll() {
        return this.all;
    }

    public List<AccountFuturesInfoBean> getUsed() {
        return this.used;
    }

    public double getMoney() {
        return this.money;
    }

    @Override
    protected void doOtherInit() {
        this.all = new LinkedList();
        this.used = new LinkedList();
        this.money = 0;
    }

    @Override
    protected void loadData() {
        doLoadAll();
        doLoadUsed();
        doLoadMoney();
    }

    private void doLoadAll() {
        Sret sr = this.agent.getAccountListFutures();
        if (!sr.isOk()) {
            this.all = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.all = (List) sr.getData();
    }

    private void doLoadUsed() {
        Sret sr = this.agent.getAccountListFuturesUsed();
        if (!sr.isOk()) {
            this.used = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.used = (List) sr.getData();
    }

    private void doLoadMoney() {
        Sret sr = this.agent.getMoneyFutures();
        if (!sr.isOk()) {
            this.money = 0;
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.money = (Double) sr.getData();
    }

    @Override
    public String[] getNotifyNames() {
        return new String[]{"account"};
    }

}
