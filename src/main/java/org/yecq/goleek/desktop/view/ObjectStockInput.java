package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectStockInput {

    private Double open_price;
    private Double loss_price;

    ObjectStockInput() {
        this.open_price = null;
        this.loss_price = null;
    }

    boolean isValid() {
        return this.open_price != null && this.loss_price != null;
    }

    void setInvalid() {
        this.open_price = null;
        this.loss_price = null;
    }

    public Double getOpen_price() {
        return open_price;
    }

    public void setOpen_price(Double open_price) {
        this.open_price = open_price;
    }

    public Double getLoss_price() {
        return loss_price;
    }

    public void setLoss_price(Double loss_price) {
        this.loss_price = loss_price;
    }
}
