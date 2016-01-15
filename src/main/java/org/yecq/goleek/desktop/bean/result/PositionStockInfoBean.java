package org.yecq.goleek.desktop.bean.result;

/**
 *
 * @author yecq
 */
public class PositionStockInfoBean {

    private String id;
    private String code;
    private String name;
    private int lot;
    private double open_price;
    private String action;
    private double quit_price;
    private String account;
    private String open_date;
    private String is_ready_close;  // y或者n

    public PositionStockInfoBean() {
    }

    public PositionStockInfoBean(String id, String code, String name, int lot, double open_price, String action, double quit_price, String account, String open_date, String is_ready_close) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.lot = lot;
        this.open_price = open_price;
        this.action = action;
        this.quit_price = quit_price;
        this.account = account;
        this.open_date = open_date;
        this.is_ready_close = is_ready_close;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getQuit_price() {
        return quit_price;
    }

    public void setQuit_price(double quit_price) {
        this.quit_price = quit_price;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public String getIs_ready_close() {
        return is_ready_close;
    }

    public void setIs_ready_close(String is_ready_close) {
        this.is_ready_close = is_ready_close;
    }

}
