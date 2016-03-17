package org.yecq.goleek.desktop.bean.param;

import java.util.HashSet;
import java.util.Set;

/**
 * 修改账户
 *
 * @author yecq
 */
public class AccountModifyBean {

    private String code;
    private String company;
    private double money;
    private String used;

    final private Set<String> v;

    public AccountModifyBean() {
        this.v = new HashSet();
    }

    public boolean isCode() {
        return this.v.contains("code");
    }

    public String getCode() {
        if (!isCode()) {
            throw new IllegalArgumentException("未设置code");
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.v.add("code");
    }

    public boolean isCompany() {
        return this.v.contains("code");
    }

    public String getCompany() {
        if (!isCompany()) {
            throw new IllegalArgumentException("未设置company");
        }
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
        this.v.add("company");
    }

    public boolean isMoney() {
        return this.v.contains("money");
    }

    public double getMoney() {
        if (!isMoney()) {
            throw new IllegalArgumentException("未设置money");
        }
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
        this.v.add("money");
    }

    public boolean isUsed() {
        return this.v.contains("used");
    }

    public String getUsed() {
        if (!isUsed()) {
            throw new IllegalArgumentException("未设置used");
        }
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
        this.v.add("used");
    }

}
