package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class AccountAddBean {

    private String code;
    private String company;
    private double money;
    private String type;

    public AccountAddBean() {
    }

    public AccountAddBean(String code, String company, double money, String type) {
        this.code = code;
        this.company = company;
        this.money = money;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

}
