package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class PositionStockCloseBean {

    private int lot;
    private double price;
    private String date;

    public PositionStockCloseBean() {
    }

    public PositionStockCloseBean(int lot, double price, String date) {
        this.lot = lot;
        this.price = price;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
