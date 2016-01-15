package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class PositionFuturesEditBean {
    private String id;
    private String action;
    private double price;

    public PositionFuturesEditBean() {
    }

    public PositionFuturesEditBean(String id, String action, double price) {
        this.id = id;
        this.action = action;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
