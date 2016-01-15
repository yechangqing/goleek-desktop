package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectStockInputDefined {

    private Integer lot;

    ObjectStockInputDefined() {
        this.lot = null;
    }

    boolean isValid() {
        return this.lot != null;
    }

    void setInvalid() {
        this.lot = null;
    }

    Integer getLot() {
        return lot;
    }

    void setLot(Integer lot) {
        this.lot = lot;
    }
}
