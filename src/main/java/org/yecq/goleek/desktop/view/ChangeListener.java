package org.yecq.goleek.desktop.view;

/**
 * 监听通知接口
 *
 * @author yecq
 */
public interface ChangeListener {

    /**
     * 返回自己的名字
     *
     * @return
     */
    String getName();

    /**
     * 哪些对象变化了要通知我
     *
     * @return
     */
    String[] getNotifiedNames();

    /**
     * 回调方法
     */
    void doChangeAction();
}
