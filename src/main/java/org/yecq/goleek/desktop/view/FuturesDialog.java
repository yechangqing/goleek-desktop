package org.yecq.goleek.desktop.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.yecq.baseframework.plain.core.Root;
import org.yecq.baseframework.plain.service.Sret;
import org.yecq.goleek.desktop.agent.FuturesAgent;

/**
 *
 * @author yecq
 */
class FuturesDialog extends JDialog {

    private Map obj;

    FuturesDialog() {
        super(MainFrame.getInstance());
        this.obj = new HashMap();
        this.obj.put("root", this);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        this.add(getCenterPanel(), c);

        this.setResizable(false);
        this.setModal(true);
        this.setTitle("期货品种");
        this.pack();
        Point point = MainFrame.getInstance().getLocation();
        Dimension dim = MainFrame.getInstance().getSize();
        Dimension dd = this.getSize();
        int x = (dim.width - dd.width) / 2 + point.x;
        int y = (dim.height - dd.height) / 4 + point.y;
        this.setLocation(x, y);
    }

    private JPanel getCenterPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        JButton add = new JButton("增加");
        this.obj.put("add", add);
        add.setPreferredSize(new Dimension(70, 22));
        p.add(add, c);

        c.gridy++;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 0, 0, 0);
        FuturesTable table = new FuturesTable();
        this.obj.put("table", table);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(370, 400));
        p.add(jsp, c);

        c.gridy++;
        c.weighty = 0;
        c.insets = new Insets(6, 0, 0, 0);
        JPanel tmpp = new JPanel();
        tmpp.setLayout(new GridBagLayout());
        GridBagConstraints tc = new GridBagConstraints();
        tc.gridx = 0;
        tc.gridy = 0;
        tc.weightx = 1;
        tc.anchor = GridBagConstraints.EAST;
        JButton all1 = new JButton("全部选");
        all1.setPreferredSize(new Dimension(90, 22));
        this.obj.put("select_all", all1);
        tmpp.add(all1, tc);
        tc.gridx++;
        tc.weightx = 0;
        tc.insets = new Insets(0, 3, 0, 0);
        JButton can1 = new JButton("全取消");
        can1.setPreferredSize(new Dimension(90, 22));
        this.obj.put("cancel_all", can1);
        tmpp.add(can1, tc);
        p.add(tmpp, c);

        return p;
    }

    private void initData() {

    }

    private void initListener() {
        JButton add = (JButton) obj.get("add");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FuturesAddDialog().setVisible(true);
            }
        });

        JButton all = (JButton) obj.get("select_all");
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "是否选择所有合约？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Sret sr = Root.getInstance().getBean(FuturesAgent.class).interestAll();
                    if (!sr.isOk()) {
                        Vutil.showErrorMsg(sr.getMessage());
                    }
                }
            }
        });

        JButton can = (JButton) obj.get("cancel_all");
        can.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "是否取消所有合约？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Sret sr = Root.getInstance().getBean(FuturesAgent.class).unInterestAll();
                    if (!sr.isOk()) {
                        Vutil.showErrorMsg(sr.getMessage());
                    }
                }
            }
        });
    }

    private void adjustSize() {
        FuturesTable table = (FuturesTable) obj.get("table");
        table.adjustWidth();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            adjustSize();
        }
        super.setVisible(aFlag);
    }
}
