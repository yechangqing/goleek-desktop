package org.yecq.goleek.desktop.bean.param;

/**
 *
 * @author yecq
 */
public class FuturesCloneBean {
    private String id;
    private String newCode;

    public FuturesCloneBean() {
    }

    public FuturesCloneBean(String id, String newCode) {
        this.id = id;
        this.newCode = newCode;
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
