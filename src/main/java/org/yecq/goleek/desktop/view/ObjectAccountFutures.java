package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectAccountFutures {

    private Double money;

    public ObjectAccountFutures() {
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
