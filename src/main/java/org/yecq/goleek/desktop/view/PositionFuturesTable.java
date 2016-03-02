package org.yecq.goleek.desktop.view;

import com.jhhc.baseframework.client.Root;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.agent.PositionFuturesAgent;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author yecq
 */
class PositionFuturesTable extends JTable {

    private PositionFuturesTableModel model;

    PositionFuturesTable() {
        this.model = new PositionFuturesTableModel();
        this.setModel(this.model);

        initView();
        initListener();
    }

    private void initView() {
        // 设定表头居中，在某些LookAndFeel里是默认居中的，但有些不是
        DefaultTableCellRenderer renderh = (DefaultTableCellRenderer) super.getTableHeader().getDefaultRenderer();
        renderh.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        // 设定第一列文字居中与设定退出颜色
        this.getColumnModel().getColumn(0).setCellRenderer(new CellColorRenderer());
    }

    void adjustWidth() {
        this.setRowHeight(24);
        // 设定列宽度
        double width = this.getSize().width;
        int w0 = (int) (width * 2 / 46);
        int w1 = (int) (width * 5 / 46);
        int w2 = (int) (width * 3 / 46);
        int w3 = (int) (width * 3 / 46);
        int w4 = (int) (width * 5 / 46);
        int w5 = (int) (width * 12 / 46);
        int w6 = (int) (width * 7 / 46);

        TableColumnModel m = getColumnModel();

        m.getColumn(0).setPreferredWidth(w0);
        m.getColumn(1).setPreferredWidth(w1);
        m.getColumn(2).setPreferredWidth(w2);
        m.getColumn(3).setPreferredWidth(w3);
        m.getColumn(4).setPreferredWidth(w4);
        m.getColumn(5).setPreferredWidth(w5);
        m.getColumn(6).setPreferredWidth(w6);
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        int row = getSelectedRow();
        if (row < 0) {
            return "";
        }
        final PositionFuturesInfoBean bean = model.getRowBean(row);
        return "开仓日期 " + bean.getOpen_date();
    }

    private void initListener() {
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                int col = getSelectedColumn();
                if (row == -1 || col == -1) {
                    return;
                }
                final PositionFuturesInfoBean bean = model.getRowBean(row);
                if (e.isMetaDown()) {
                    JPopupMenu p = new JPopupMenu();
                    JMenuItem close = new JMenuItem("平仓");
                    close.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new PositionFuturesCloseDialog(bean).setVisible(true);
                        }
                    });
                    p.add(close);

                    JMenuItem del = new JMenuItem("删除");
                    del.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否删除期货持仓？", "？", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION) {
                                Sret sr = Root.getInstance().getBean(PositionFuturesAgent.class).delete(bean.getId());
                                if (!sr.isOk()) {
                                    Vutil.showErrorMsg(sr.getMessage());
                                }
                            }
                        }
                    });
                    p.add(del);
                    p.show(e.getComponent(), e.getX(), e.getY());
                } else if (e.getClickCount() == 2 && col == 5) {
                    new PositionFuturesQuitDialog(bean).setVisible(true);
                }
            }
        });
    }

    private class CellColorRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component obj = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            if (isSelected) {
                obj.setBackground(new Color(200, 200, 218));
            } else {
                obj.setBackground(null);
            }
            PositionFuturesInfoBean pos = model.getRowBean(row);
            if (column == 0) {
                String is = pos.getIs_ready_close();
                if (is.equals("y")) {
                    obj.setBackground(new Color(254, 142, 133));
                } else {
                    obj.setForeground(null);
                }
            }
            return obj;
        }
    }
}
