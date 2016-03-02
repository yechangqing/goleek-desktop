package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.FuturesAgent;
import org.yecq.goleek.desktop.bean.param.FuturesCloneBean;
import org.yecq.goleek.desktop.bean.param.FuturesRemoveBean;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.yecq.baseframework.plain.core.Root;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
class FuturesTable extends JTable {

    private FuturesTableModel model;

    FuturesTable() {
        this.model = new FuturesTableModel();
        this.setModel(this.model);

        initView();
        initListener();
    }

    private void initView() {
        // 加上自选的复选框
        TableColumn inte = this.getColumnModel().getColumn(5);
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
                final FuturesInfoBean bean = model.getRowBean(row);
                if (e.isMetaDown()) {
                    JPopupMenu p = new JPopupMenu();
                    JMenuItem clone = new JMenuItem("克隆");
                    clone.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String code = JOptionPane.showInputDialog(MainFrame.getInstance(), "输入新合约代号", bean.getCode());
                            if (code != null && JOptionPane.showConfirmDialog(null, "是否克隆为新合约" + code, "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                Sret sr = Root.getInstance().getBean(FuturesAgent.class).cloneItself(new FuturesCloneBean(bean.getId(), code));
                                if (!sr.isOk()) {
                                    Vutil.showErrorMsg(sr.getMessage());
                                }
                            }
                        }
                    });
                    JMenuItem edit = new JMenuItem("修改");
                    edit.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new FururesModifyDialog(bean).setVisible(true);
                        }
                    });
                    JMenuItem del = new JMenuItem("删除");
                    del.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否删除该合约？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                Sret sr = Root.getInstance().getBean(FuturesAgent.class).remove(new FuturesRemoveBean(bean.getId()));
                                if (!sr.isOk()) {
                                    Vutil.showErrorMsg(sr.getMessage());
                                }
                            }
                        }
                    });
                    p.add(clone);
                    p.add(edit);
                    p.add(del);
                    p.show(e.getComponent(), e.getX(), e.getY());
                } else if (e.getClickCount() == 2) {
                    new FururesModifyDialog(bean).setVisible(true);
                }
            }
        });
    }

    void adjustWidth() {
        this.setRowHeight(24);
        // 设定列宽度
        double width = this.getSize().width;
        int w0 = (int) (width * 1 / 46);
        int w1 = (int) (width * 6 / 46);
        int w2 = (int) (width * 6 / 46);
        int w3 = (int) (width * 7 / 46);
        int w4 = (int) (width * 6 / 46);
        int w5 = (int) (width * 3 / 46);

        TableColumnModel m = getColumnModel();

        m.getColumn(0).setPreferredWidth(w0);
        m.getColumn(1).setPreferredWidth(w1);
        m.getColumn(2).setPreferredWidth(w2);
        m.getColumn(3).setPreferredWidth(w3);
        m.getColumn(4).setPreferredWidth(w4);
        m.getColumn(5).setPreferredWidth(w5);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        // 若原来的值没有变，则不变
        Object old = this.getValueAt(row, column);
        if (column == 5) {
            boolean b = old.equals("y");
            if (b && JOptionPane.showConfirmDialog(null, "是否取消关注？", "确认修改", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.model.setValueAt("n", row, column);
            }
            if (!b && JOptionPane.showConfirmDialog(null, "是否关注？", "确认修改", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.model.setValueAt("y", row, column);
            }
        } else if (column == 3) {
            Double newv = Double.parseDouble(value + "");
            Double oldv = Double.parseDouble(old + "");
            if (!newv.equals(oldv) && JOptionPane.showConfirmDialog(null, "是否修改值？", "确认修改", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.model.setValueAt(value, row, column);
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
            if (column == 5) {
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
