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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author yecq
 */
class AccountStockDialog extends JDialog {

    private Map obj;

    AccountStockDialog() {
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
        this.setTitle("股票账户");
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
        AccountStockTable table = new AccountStockTable();
        this.obj.put("table", table);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(350, 150));
        p.add(jsp, c);
        return p;
    }

    private void initData() {

    }

    private void initListener() {
        JButton add = (JButton) obj.get("add");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountStockAddDialog().setVisible(true);
            }
        });
    }

    private void adjustSize() {
        AccountStockTable table = (AccountStockTable) obj.get("table");
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
