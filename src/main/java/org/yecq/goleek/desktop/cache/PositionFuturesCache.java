package org.yecq.goleek.desktop.cache;

import org.yecq.goleek.desktop.view.Vutil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yecq.goleek.desktop.agent.PositionFuturesAgent;
import org.yecq.goleek.desktop.bean.param.PositionFuturesActionsBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.CoreChangeListener;

/**
 *
 * @author yecq
 */
@Component
public class PositionFuturesCache extends CacheBase implements CoreChangeListener {

    @Autowired
    private PositionFuturesAgent agent;

    private List<PositionFuturesInfoBean> data;
    private String[] actions_buy;
    private String[] actions_sell;

    public List<PositionFuturesInfoBean> getListAll() {
        return this.data;
    }

    public String[] getActions(String direct) {
        if (direct.equals("多")) {
            return this.actions_buy;
        } else if (direct.equals("空")) {
            return this.actions_sell;
        }
        return new String[]{};
    }

    @Override
    protected void doOtherInit() {
        this.data = new LinkedList();
    }

    @Override
    protected void loadData() {
        doLoadPosition();
        doLoadActions();
    }

    private void doLoadPosition() {
        Sret sr = agent.getListAll();
        if (!sr.isOk()) {
            this.data = new ArrayList(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.data = (List) sr.getData();
    }

    private void doLoadActions() {
        Sret sr = agent.getActions(new PositionFuturesActionsBean("多"));
        if (!sr.isOk()) {
            this.actions_buy = new String[]{};
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.actions_buy = (String[]) sr.getData();

        sr = agent.getActions(new PositionFuturesActionsBean("空"));
        if (!sr.isOk()) {
            this.actions_sell = new String[]{};
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.actions_sell = (String[]) sr.getData();
    }

    @Override
    public String[] getNotifyNames() {
        return new String[]{"position_detail_futures", "position_futures", "detail_futures"};
    }

}
