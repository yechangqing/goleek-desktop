package org.yecq.goleek.desktop.view;

/**
 * 保存交易设置的对象
 *
 * @author yecq
 */
class ObjectTradeSetting {

    private Double open_percent;
    private Double loss_percent;

    ObjectTradeSetting() {
        this.open_percent = null;
        this.loss_percent = null;
    }

    boolean isValid() {
        return this.open_percent != null && this.loss_percent != null;
    }

    void setInvalid() {
        this.open_percent = null;
        this.loss_percent = null;
    }

    public Double getOpen_percent() {
        return open_percent;
    }

    public void setOpen_percent(Double position_percent) {
        this.open_percent = position_percent;
    }

    public Double getLoss_percent() {
        return loss_percent;
    }

    public void setLoss_percent(Double loss_percent) {
        this.loss_percent = loss_percent;
    }

}
