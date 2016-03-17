package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.PositionStockAgent;
import org.yecq.goleek.desktop.bean.result.PositionStockInfoBean;
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
import org.yecq.baseframework.plain.core.Root;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
class PositionStockTable extends JTable {

    private PositionStockTableModel model;

    PositionStockTable() {
        this.model = new PositionStockTableModel();
        this.setModel(this.model);

        initView();
        initListener();
    }

    private void initView() {
        // 设定表头居中，在某些LookAndFeel里是默认居中的，但有些不是
        DefaultTableCellRenderer renderh = (DefaultTableCellRenderer) super.getTableHeader().getDefaultRenderer();
        renderh.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        // 设定第一列文字居中与设定退出颜色
//        this.getColumnModel().getColumn(0).setCellRenderer(new CellColorRenderer());
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
                final PositionStockInfoBean bean = model.getRowBean(row);
                if (e.isMetaDown()) {

                    JPopupMenu p = new JPopupMenu();
                    JMenuItem close = new JMenuItem("卖出");
                    close.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new PositionStockCloseDialog(bean).setVisible(true);
                        }
                    });
                    p.add(close);

                    JMenuItem del = new JMenuItem("删除");
                    del.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否删除股票持仓？", "？", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION) {
                                Sret sr = Root.getInstance().getBean(PositionStockAgent.class).delete(bean.getId());
                                if (!sr.isOk()) {
                                    Vutil.showErrorMsg(sr.getMessage());
                                }
                            }
                        }
                    });
                    p.add(del);
                    p.show(e.getComponent(), e.getX(), e.getY());
                } else if (e.getClickCount() == 2 && col == 5) {
                    new PositionStockQuitDialog(bean).setVisible(true);
                }
            }
        });
    }

    void adjustWitdh() {
        this.setRowHeight(24);
        // 设定列宽度
        double width = this.getSize().width;
        int w0 = (int) (width * 2 / 46);
        int w1 = (int) (width * 4 / 46);
        int w2 = (int) (width * 5 / 46);
        int w3 = (int) (width * 3 / 46);
        int w4 = (int) (width * 5 / 46);
        int w5 = (int) (width * 10 / 46);
        int w6 = (int) (width * 5 / 46);

        TableColumnModel m = getColumnModel();

        m.getColumn(0).setPreferredWidth(w0);
        m.getColumn(1).setPreferredWidth(w1);
        m.getColumn(2).setPreferredWidth(w2);
        m.getColumn(3).setPreferredWidth(w3);
        m.getColumn(4).setPreferredWidth(w4);
        m.getColumn(5).setPreferredWidth(w5);
        m.getColumn(6).setPreferredWidth(w6);
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
            PositionStockInfoBean pos = model.getRowBean(row);
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
