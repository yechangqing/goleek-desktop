package org.yecq.goleek.desktop.bean.result;

/**
 *
 * @author yecq
 */
public class AccountStockInfoBean {

    private String id;
    private String code;
    private String company;
    private double money;
    private String used;

    public AccountStockInfoBean(String id, String code, String company, double money, String used) {
        this.id = id;
        this.code = code;
        this.company = company;
        this.money = money;
        this.used = used;
    }

    public AccountStockInfoBean() {
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
