package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectFuturesInputDefined {

    private Integer lot;

    ObjectFuturesInputDefined() {
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
