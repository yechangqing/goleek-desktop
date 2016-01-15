package org.yecq.goleek.desktop.cache;

import org.yecq.goleek.desktop.agent.PositionStockAgent;
import org.yecq.goleek.desktop.bean.result.PositionStockInfoBean;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.CoreChangeListener;
import org.yecq.goleek.desktop.view.Vutil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class PositionStockCache extends CacheBase implements CoreChangeListener {

    @Autowired
    private PositionStockAgent agent;

    private List<PositionStockInfoBean> all;
    private String[] actions;

    public List<PositionStockInfoBean> getListAll() {
        return this.all;
    }

    public String[] getActions() {
        return this.actions;
    }

    @Override
    protected void doOtherInit() {
        this.all = new LinkedList();
    }

    @Override
    protected void loadData() {
        doLoadPosition();
        doLoadActions();
    }

    private void doLoadPosition() {
        Sret sr = agent.getgetListAll();
        if (!sr.isOk()) {
            this.all = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.all = (List) sr.getData();
    }

    private void doLoadActions() {
        Sret sr = agent.getActions();
        if (!sr.isOk()) {
            this.actions = new String[]{};
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.actions = (String[]) sr.getData();
    }

    @Override
    public String[] getNotifyNames() {
        return new String[]{"position_stock", "detail_stock", "position_detail_stock"};
    }

}
