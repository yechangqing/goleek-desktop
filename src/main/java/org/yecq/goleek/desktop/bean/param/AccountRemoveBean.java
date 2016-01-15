package org.yecq.goleek.desktop.bean.param;

/**
 * 删除账户
 *
 * @author yecq
 */
public class AccountRemoveBean {

    private String id;

    public AccountRemoveBean() {
    }

    public AccountRemoveBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
