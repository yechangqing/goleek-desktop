package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class PositionStockOpenBean {

    private String code;
    private int lot;
    private double open_price;
    private String open_date;
    private double quit_price;
    private String account;

    public PositionStockOpenBean() {
    }

    public PositionStockOpenBean(String code, int lot, double open_price, String open_date, double quit_price, String account) {
        this.code = code;
        this.lot = lot;
        this.open_price = open_price;
        this.open_date = open_date;
        this.quit_price = quit_price;
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public double getOpen_price() {
        return open_price;
    }

    public void setOpen_price(double open_price) {
        this.open_price = open_price;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public double getQuit_price() {
        return quit_price;
    }

    public void setQuit_price(double quit_price) {
        this.quit_price = quit_price;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
