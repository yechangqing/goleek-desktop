package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectFuturesInput {

    private String direct;
    private Double open_price;
    private Double loss_price;

    ObjectFuturesInput() {
        this.direct = null;
        this.open_price = null;
        this.loss_price = null;
    }

    boolean isValid() {
        return this.direct != null && this.open_price != null && this.loss_price != null && this.loss_price != null;
    }

    void setInvalid() {
        this.direct = null;
        this.open_price = null;
        this.loss_price = null;
    }

    String getDirect() {
        return direct;
    }

    void setDirect(String direct) {
        this.direct = direct;
    }

    Double getOpen_price() {
        return open_price;
    }

    void setOpen_price(Double open_price) {
        this.open_price = open_price;
    }

    Double getLoss_price() {
        return loss_price;
    }

    void setLoss_price(Double loss_price) {
        this.loss_price = loss_price;
    }

}
