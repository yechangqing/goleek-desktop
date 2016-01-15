package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectStockResultStd {

    private Integer lot;

    ObjectStockResultStd() {
        this.lot = null;
    }

    boolean isValid() {
        return this.lot != null;
    }

    void setInvalid() {
        this.lot = null;
    }

    Integer getLot() {
        return this.lot;
    }

    void setLot(Integer lot) {
        this.lot = lot;
    }
}
