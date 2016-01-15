package org.yecq.goleek.desktop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import org.yecq.goleek.desktop.view.surface.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.yecq.goleek.desktop.agent.StockAgent;
import org.yecq.goleek.desktop.bean.param.StockRemoveBean;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;

/**
 *
 * @author yecq
 */
class StockTable extends JTable {

    private StockTableModel model;

    StockTable() {
        this.model = new StockTableModel();
        this.setModel(this.model);

        initView();
        initListener();
    }

    private void initView() {
        // 加上自选的复选框
        TableColumn inte = this.getColumnModel().getColumn(4);
        inte.setCellEditor(new DefaultCellEditor(new JCheckBox()));     // 以JCheckBox为编辑窗口
        inte.setCellRenderer(new InterestCellRenderer());

        // 设定表头居中，在某些LookAndFeel里是默认居中的，但有些不是
        DefaultTableCellRenderer renderh = (DefaultTableCellRenderer) super.getTableHeader().getDefaultRenderer();
        renderh.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        // 设定第一列文字居中
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(render);
    }

    private void initListener() {
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                int col = getSelectedColumn();
                final StockInfoBean bean = model.getRowBean(row);

                if (e.isMetaDown()) {
                    JPopupMenu p = new JPopupMenu();
                    JMenuItem del = new JMenuItem("删除");
                    del.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否删除该股票？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                Sret sr = Root.getInstance().getBean(StockAgent.class).remove(new StockRemoveBean(bean.getId()));
                                if (!sr.isOk()) {
                                    Vutil.showErrorMsg(sr.getMessage());
                                }
                            }
                        }
                    });
                    p.add(del);
                    p.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    void adjustWidth() {
        this.setRowHeight(24);
        // 设定列宽度
        double width = this.getSize().width;
        int w0 = (int) (width * 1 / 46);
        int w1 = (int) (width * 8 / 46);
        int w2 = (int) (width * 8 / 46);
        int w3 = (int) (width * 6 / 46);
        int w4 = (int) (width * 2 / 46);

        TableColumnModel m = getColumnModel();

        m.getColumn(0).setPreferredWidth(w0);
        m.getColumn(1).setPreferredWidth(w1);
        m.getColumn(2).setPreferredWidth(w2);
        m.getColumn(3).setPreferredWidth(w3);
        m.getColumn(4).setPreferredWidth(w4);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        // 若原来的值没有变，则不变
        Object old = this.getValueAt(row, column);
        if (column == 4) {
            boolean b = old.equals("y");
            if (b && JOptionPane.showConfirmDialog(null, "是否取消关注？", "确认修改", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.model.setValueAt("n", row, column);
            }
            if (!b && JOptionPane.showConfirmDialog(null, "是否关注？", "确认修改", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.model.setValueAt("y", row, column);
            }
        } else if (!value.equals(old) && JOptionPane.showConfirmDialog(null, "是否修改值？", "确认修改", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.model.setValueAt(value, row, column);
        }

    }

    // 感兴趣的合约复选框渲染
    private class InterestCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 4) {
                JCheckBox jcb = new JCheckBox();
                jcb.setSelected(value.equals("y"));
                jcb.setBackground(jcb.isSelected() ? Color.lightGray : null);
                return jcb;
            } else {
                String v = (String) getValueAt(row, 5);
                if (v.equals("y")) {
                    comp.setBackground(Color.lightGray);
                } else {
                    comp.setBackground(null);
                }
            }
            return comp;
        }

    }
}
