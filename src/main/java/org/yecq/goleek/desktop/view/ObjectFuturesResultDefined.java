package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectFuturesResultDefined {

    private Double position_percent;
    private Double loss_percent;

    ObjectFuturesResultDefined() {
        this.position_percent = null;
        this.loss_percent = null;
    }

    boolean isValid() {
        return this.position_percent != null && this.loss_percent != null;
    }

    void setInvalid() {
        this.position_percent = null;
        this.loss_percent = null;
    }

    Double getPosition_percent() {
        return position_percent;
    }

    void setPosition_percent(Double position_percent) {
        this.position_percent = position_percent;
    }

    Double getLoss_percent() {
        return loss_percent;
    }

    void setLoss_percent(Double loss_percent) {
        this.loss_percent = loss_percent;
    }

}
