package org.yecq.goleek.desktop.view.surface;

/**
 *
 * @author yecq
 */
public class JTable extends javax.swing.JTable {

    public JTable() {
        super();
    }

    @Override
    public void setRowHeight(int rowHeight) {
        super.setRowHeight((rowHeight - 1) * 2);
    }

}
