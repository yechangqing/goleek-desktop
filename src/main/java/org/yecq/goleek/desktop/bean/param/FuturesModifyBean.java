package org.yecq.goleek.desktop.bean.param;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yecq
 */
public class FuturesModifyBean {

    private String code;
    private String name;
    private double margin;
    private int unit;
    private double min;
    private String exchange;
    private String interest;

    private Set<String> v;

    public FuturesModifyBean() {
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

    public boolean isName() {
        return this.v.contains("name");
    }

    public String getName() {
        if (!isName()) {
            throw new IllegalArgumentException("未设置name");
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.v.add("name");
    }

    public boolean isMargin() {
        return this.v.contains("margin");
    }

    public double getMargin() {
        if (!isMargin()) {
            throw new IllegalArgumentException("未设置margin");
        }
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
        this.v.add("margin");
    }

    public boolean isUnit() {
        return this.v.contains("unit");
    }

    public int getUnit() {
        if (!isUnit()) {
            throw new IllegalArgumentException("未设置unit");
        }
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
        this.v.add("unit");
    }

    public boolean isMin() {
        return this.v.contains("min");
    }

    public double getMin() {
        if (!isMin()) {
            throw new IllegalArgumentException("未设置min");
        }
        return min;
    }

    public void setMin(double min) {
        this.min = min;
        this.v.add("min");
    }

    public boolean isExchange() {
        return this.v.contains("exchange");
    }

    public String getExchange() {
        if (!isExchange()) {
            throw new IllegalArgumentException("未设置exchange");
        }
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
        this.v.add("exchange");
    }

    public boolean isInterest() {
        return this.v.contains("interest");
    }

    public String getInterest() {
        if (!isInterest()) {
            throw new IllegalArgumentException("未设置interest");
        }
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
        this.v.add("interest");
    }
}
