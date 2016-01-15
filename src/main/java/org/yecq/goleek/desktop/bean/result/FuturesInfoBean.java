package org.yecq.goleek.desktop.bean.result;

import java.util.Objects;

/**
 *
 * @author yecq
 */
public class FuturesInfoBean {

    private String id;
    private String code;
    private String name;
    private double margin;
    private int unit;
    private double min;
    private String exchange;
    private String interest;

    public FuturesInfoBean() {
    }

    public FuturesInfoBean(String id, String code, String name, double margin, int unit, double min, String exchange, String interest) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.margin = margin;
        this.unit = unit;
        this.min = min;
        this.exchange = exchange;
        this.interest = interest;
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

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FuturesInfoBean other = (FuturesInfoBean) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    
}
