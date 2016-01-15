package org.yecq.goleek.desktop.bean.param;

import java.util.HashSet;
import java.util.Set;

/**
 * 修改账户
 *
 * @author yecq
 */
public class AccountModifyBean {

    private String id;
    private String code;
    private String company;
    private double money;
    private String used;

    final private Set<String> value;

    public AccountModifyBean() {
        this.value = new HashSet();
    }

    public AccountModifyBean(String id) {
        this.id = id;
        this.value = new HashSet();
    }

    public String getId() {
        return id;
    }

    public boolean isCode() {
        return this.value.contains("code");
    }

    public String getCode() {
        if (!isCode()) {
            throw new IllegalArgumentException("未设置code");
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.value.add("code");
    }

    public boolean isCompany() {
        return this.value.contains("code");
    }

    public String getCompany() {
        if (!isCompany()) {
            throw new IllegalArgumentException("未设置company");
        }
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
        this.value.add("company");
    }

    public boolean isMoney() {
        return this.value.contains("money");
    }

    public double getMoney() {
        if (!isMoney()) {
            throw new IllegalArgumentException("未设置money");
        }
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
        this.value.add("money");
    }

    public boolean isUsed() {
        return this.value.contains("used");
    }

    public String getUsed() {
        if (!isUsed()) {
            throw new IllegalArgumentException("未设置used");
        }
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
        this.value.add("used");
    }

}
