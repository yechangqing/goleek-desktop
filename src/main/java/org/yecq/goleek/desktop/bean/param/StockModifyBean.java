package org.yecq.goleek.desktop.bean.param;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yecq
 */
public class StockModifyBean {

    private String code;
    private String name;
    private String exchange;
    private String interest;
    private Set<String> v;

    public StockModifyBean() {
        this.v = new HashSet();
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

}
