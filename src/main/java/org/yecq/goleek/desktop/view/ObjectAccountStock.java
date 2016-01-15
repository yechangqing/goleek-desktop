package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectAccountStock {

    private Double money;

    public ObjectAccountStock() {
        this.money = null;
    }

    boolean isValid() {
        return this.money != null;
    }

    void setInvalid() {
        this.money = null;
    }

    Double getMoney() {
        return money;
    }

    void setMoney(Double money) {
        this.money = money;
    }
}
