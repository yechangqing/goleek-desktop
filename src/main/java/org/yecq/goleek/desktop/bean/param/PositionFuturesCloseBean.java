package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class PositionFuturesCloseBean {

    private int lot;
    private double price;
    private String date;

    public PositionFuturesCloseBean() {
    }

    public PositionFuturesCloseBean(int lot, double price, String date) {
        this.lot = lot;
        this.price = price;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
