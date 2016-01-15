package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class StockAddBean {

    private String code;
    private String name;
    private String exchange;

    public StockAddBean() {
    }

    public StockAddBean(String code, String name, String exchange) {
        this.code = code;
        this.name = name;
        this.exchange = exchange;
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
}
