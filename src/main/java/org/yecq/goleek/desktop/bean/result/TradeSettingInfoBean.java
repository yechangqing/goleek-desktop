package org.yecq.goleek.desktop.bean.result;

/**
 *
 * @author yecq
 */
public class TradeSettingInfoBean {

    private String id;
    private double open_percent;
    private double loss_percent;

    public TradeSettingInfoBean() {
    }

    public TradeSettingInfoBean(String id, double open_percent, double loss_percent) {
        this.id = id;
        this.open_percent = open_percent;
        this.loss_percent = loss_percent;
    }

    public double getLoss_percent() {
        return loss_percent;
    }

    public void setLoss_percent(double loss_percent) {
        this.loss_percent = loss_percent;
    }

    public double getOpen_percent() {
        return open_percent;
    }

    public void setOpen_percent(double open_percent) {
        this.open_percent = open_percent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
