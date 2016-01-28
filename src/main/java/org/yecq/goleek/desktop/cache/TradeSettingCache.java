package org.yecq.goleek.desktop.cache;

import org.yecq.goleek.desktop.agent.TradeSettingAgent;
import org.yecq.goleek.desktop.bean.result.TradeSettingInfoBean;
import org.yecq.goleek.desktop.view.Vutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.CoreChangeListener;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
@Component
public class TradeSettingCache extends CacheBase implements CoreChangeListener {

    @Autowired
    private TradeSettingAgent agent;

    private TradeSettingInfoBean data;

    public double getOpenPercent() {
        return this.data.getOpen_percent();
    }

    public double getLossPercent() {
        return this.data.getLoss_percent();
    }

    public TradeSettingInfoBean getSetting() {
        return this.data;
    }

    public void setTemp(double open_percent, double loss_percent) {
        this.data.setOpen_percent(open_percent);
        this.data.setLoss_percent(loss_percent);
        fireFlush();
    }

    @Override
    protected void doOtherInit() {
        this.data = new TradeSettingInfoBean();
    }

    @Override
    protected void loadData() {
        doLoad();
    }

    private void doLoad() {
        Sret sr = this.agent.getDefault();
        if (!sr.isOk()) {
            this.data.setId(null);
            this.data.setLoss_percent(0);
            this.data.setOpen_percent(0);
            Vutil.showErrorMsg(sr.getMessage());
            return;
        }
        this.data = (TradeSettingInfoBean) sr.getData();
    }

    @Override
    public String[] getNotifyNames() {
        return new String[]{"setting"};
    }

}
