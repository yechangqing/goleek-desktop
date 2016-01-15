package org.yecq.goleek.desktop.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 * 期货数据显示区
 *
 * @author yecq
 */
class FuturesPanel extends JPanel {

    private Map obj;

    FuturesPanel() {
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
        c.weightx = 0;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(5, 20, 5, 5);
        this.add(FuturesComputePanel.getInstance(), c);

        c.gridx++;
        c.weightx = 1;
        c.insets = new Insets(0, 10, 0, 10);
        this.add(getFillPanel(), c);

        c.gridx++;
        c.weightx = 0;
        c.insets = new Insets(5, 5, 5, 20);
        c.anchor = GridBagConstraints.NORTHEAST;
        this.add(getPositionPanel(), c);

        this.setPreferredSize(new Dimension(Constants.CENTER_PANEL_WIDTH, Constants.CENTER_PANEL_HEIGHT));
    }

    private void initData() {

    }

    void reload() {
        this.removeAll();
        this.obj.clear();
        this.obj.put("root", this);
        initView();
        initListener();
    }

    private void initListener() {
        JButton open = (JButton) obj.get("open");
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PositionFuturesOpenDialog().setVisible(true);
            }
        });
    }

    private JPanel getFillPanel() {
        JPanel p = new JPanel();
        return p;
    }

    private JPanel getPositionPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(2, 10, 0, 10);
        JLabel t = new JLabel("期货持仓");
        p.add(t, c);

        c.gridy++;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 10, 0);
        p.add(new JSeparator(JSeparator.HORIZONTAL), c);

        c.gridy++;
        c.insets = new Insets(0, 0, 0, 10);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        JButton open = new JButton("开仓");
        open.setPreferredSize(new Dimension(70, 22));
        this.obj.put("open", open);
        p.add(open, c);

        c.gridy++;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 10, 10, 10);
        PositionFuturesTable table = new PositionFuturesTable();
        this.obj.put("table", table);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(500, 200));
        p.add(jsp, c);

        p.setBorder(BorderFactory.createEtchedBorder());
        return p;
    }

    void adjustSize() {
        PositionFuturesTable table = (PositionFuturesTable) obj.get("table");
        table.adjustWidth();
    }
}
