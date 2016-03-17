package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class PositionFuturesEditBean {

    private String action;
    private double price;

    public PositionFuturesEditBean() {
    }

    public PositionFuturesEditBean(String action, double price) {
        this.action = action;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
