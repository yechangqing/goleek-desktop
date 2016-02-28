package org.yecq.goleek.desktop.cache;

import com.jhhc.baseframework.client.listener.CoreChangeListener;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.agent.FuturesAgent;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.yecq.goleek.desktop.view.Vutil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class FuturesCache extends CacheBase implements CoreChangeListener {

    @Autowired
    private FuturesAgent agent;

    private List<FuturesInfoBean> all;
    private List<FuturesInfoBean> interest;
    private String[] exchange;

    public List<FuturesInfoBean> getAll() {
        return this.all;
    }

    public List<FuturesInfoBean> getInterest() {
        return this.interest;
    }

    public String[] getExchangeNames() {
        return this.exchange;
    }

    public FuturesInfoBean getInfoBean(String code) {
        Iterator<FuturesInfoBean> ite = this.all.iterator();
        while (ite.hasNext()) {
            FuturesInfoBean c = ite.next();
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }

    @Override
    protected void doOtherInit() {
        this.all = new LinkedList();
        this.interest = new LinkedList();
    }

    @Override
    protected void loadData() {
        doLoadAll();
        doLoadInterested();
        doLoadExchange();
    }

    private void doLoadAll() {
        Sret sr = this.agent.getListAll();
        if (!sr.isOk()) {
            this.all = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.all = (List) sr.getData();
    }

    private void doLoadInterested() {
        Sret sr = this.agent.getListInterested();
        if (!sr.isOk()) {
            this.interest = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.interest = (List) sr.getData();
    }

    private void doLoadExchange() {
        Sret sr = this.agent.getExchangeNames();
        if (!sr.isOk()) {
            this.exchange = new String[]{};
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.exchange = (String[]) sr.getData();
    }

    @Override
    public String[] getNotifyNames() {
        return new String[]{"futures"};
    }

}
