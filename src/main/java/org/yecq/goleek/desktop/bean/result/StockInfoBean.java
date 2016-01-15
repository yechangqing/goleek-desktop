package org.yecq.goleek.desktop.bean.result;

import java.util.Objects;

/**
 *
 * @author yecq
 */
public class StockInfoBean {

    private String id;
    private String code;
    private String name;
    private String exchange;
    private String interest;

    public StockInfoBean() {
    }

    public StockInfoBean(String id, String code, String name, String exchange, String interest) {
        this.id = id;
        this.code = code;
        this.name = name;
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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final StockInfoBean other = (StockInfoBean) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
