package org.yecq.goleek.desktop.cache;

import org.yecq.goleek.desktop.agent.StockAgent;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.CoreChangeListener;
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
public class StockCache extends CacheBase implements CoreChangeListener {

    @Autowired
    private StockAgent agent;

    private List<StockInfoBean> all;
    private List<StockInfoBean> interest;
    private String[] exchange;

    public List<StockInfoBean> getAll() {
        return this.all;
    }

    public List<StockInfoBean> getInterest() {
        return this.interest;
    }

    public String[] getExchangeNames() {
        return this.exchange;
    }

    public String getExchangeNameByCode(String code) {
        if (code.startsWith("600") || code.startsWith("601")) {
            return "上海证券";
        }
        if (code.startsWith("000") || code.startsWith("002") || code.startsWith("300")) {
            return "深圳证券";
        }
        return null;
    }

    public StockInfoBean getInfoBeanByName(String name) {
        Iterator<StockInfoBean> ite = this.interest.iterator();
        while (ite.hasNext()) {
            StockInfoBean bean = ite.next();
            if (bean.getName().equals(name.trim())) {
                return bean;
            }
        }
        return null;
    }

    public StockInfoBean getInfoBeanByCode(String code) {
        Iterator<StockInfoBean> ite = this.interest.iterator();
        while (ite.hasNext()) {
            StockInfoBean bean = ite.next();
            if (bean.getCode().equals(code.trim())) {
                return bean;
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
        doLoadExchangeNames();
    }

    private void doLoadAll() {
        Sret sr = agent.getListAll();
        if (!sr.isOk()) {
            this.all = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.all = (List) sr.getData();
    }

    private void doLoadInterested() {
        Sret sr = agent.getListInterested();
        if (!sr.isOk()) {
            this.interest = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.interest = (List) sr.getData();
    }

    private void doLoadExchangeNames() {
        Sret sr = agent.getExchangeNames();
        if (!sr.isOk()) {
            this.exchange = new String[]{};
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.exchange = (String[]) sr.getData();
    }

    @Override
    public String[] getNotifyNames() {
        return new String[]{"stock"};
    }
}
