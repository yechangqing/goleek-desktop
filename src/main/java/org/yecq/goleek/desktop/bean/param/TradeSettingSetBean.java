package org.yecq.goleek.desktop.bean.param;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yecq
 */
public class TradeSettingSetBean {

    private double open_percent;
    private double loss_percent;
    private Set<String> v;

    public TradeSettingSetBean() {
        this.v = new HashSet();
    }

    public TradeSettingSetBean(double open_percent, double loss_percent) {
        this.v = new HashSet();
        setOpen_percent(open_percent);
        setLoss_percent(loss_percent);
    }

    public boolean isOpen_percent() {
        return this.v.contains("open_percent");
    }

    public double getOpen_percent() {
        if (!isOpen_percent()) {
            throw new IllegalArgumentException("未设置open_percent");
        }
        return open_percent;
    }

    public void setOpen_percent(double open_percent) {
        this.open_percent = open_percent;
        this.v.add("open_percent");
    }

    public boolean isLoss_percent() {
        return this.v.contains("loss_percent");
    }

    public double getLoss_percent() {
        if (!isLoss_percent()) {
            throw new IllegalArgumentException("未设置loss_percent");
        }
        return loss_percent;
    }

    public void setLoss_percent(double loss_percent) {
        this.loss_percent = loss_percent;
        this.v.add("loss_percent");
    }
}
