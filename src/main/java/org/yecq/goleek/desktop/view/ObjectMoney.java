package org.yecq.goleek.desktop.view;

import org.yecq.baseframework.plain.core.Root;
import org.yecq.goleek.desktop.cache.AccountFuturesCache;
import org.yecq.goleek.desktop.cache.AccountStockCache;
import org.yecq.goleek.desktop.cache.CacheListener;

/**
 * 保存交易账户的对象
 *
 * @author yecq
 */
class ObjectMoney implements CacheListener {

    private Double all;
    private Double futures;
    private Double stock;

    ObjectMoney() {
        this.all = null;
        this.futures = null;
        this.stock = null;
        Root.getInstance().getBean(AccountFuturesCache.class).addCacheListener(this);
        Root.getInstance().getBean(AccountStockCache.class).addCacheListener(this);
    }

    boolean isValid() {
        return this.all != null && this.futures != null && this.stock != null;
    }

    void setInvalid() {
        this.all = null;
        this.futures = null;
        this.stock = null;
    }

    void setAllInvalid() {
        this.all = null;
    }

    void setFuturesInvalid() {
        this.futures = null;
    }

    void setStockInvalid() {
        this.stock = null;
    }

    public Double getAll() {
        return all;
    }

    public void setAll(Double all) {
        this.all = all;
    }

    public Double getFutures() {
        return futures;
    }

    public void setFutures(Double futures) {
        this.futures = futures;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    @Override
    public void flush() {
        this.futures = Root.getInstance().getBean(AccountFuturesCache.class).getMoney();
        this.stock = Root.getInstance().getBean(AccountStockCache.class).getMoney();
        this.all = this.futures + this.stock;
    }
}
