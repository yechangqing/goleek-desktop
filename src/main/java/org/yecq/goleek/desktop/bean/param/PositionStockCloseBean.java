package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class PositionStockCloseBean {

    private String id;
    private int lot;
    private double price;
    private String date;

    public PositionStockCloseBean() {
    }

    public PositionStockCloseBean(String id, int lot, double price, String date) {
        this.id = id;
        this.lot = lot;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
