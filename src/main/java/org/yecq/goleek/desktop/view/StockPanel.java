package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.view.surface.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import org.yecq.goleek.desktop.view.surface.Insets;
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
 * 股票信息显示区
 *
 * @author yecq
 */
class StockPanel extends JPanel {

    private Map obj;

    StockPanel() {
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
        c.insets = new Insets(5, 20, 5, 5);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.VERTICAL;
        this.add(StockComputePanel.getInstance(), c);

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
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(2, 10, 0, 10);
        p.add(new JLabel("股票持仓"), c);

        c.gridy++;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 10, 0);
        p.add(new JSeparator(JSeparator.HORIZONTAL), c);

        c.gridy++;
        c.insets = new Insets(0, 0, 0, 10);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        JButton open = new JButton("买入");
        open.setPreferredSize(new Dimension(70, 22));
        this.obj.put("open", open);
        p.add(open, c);

        c.gridy++;
        c.weighty = 1;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        PositionStockTable table = new PositionStockTable();
        this.obj.put("table", table);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(500, 200));
        p.add(jsp, c);

        p.setBorder(BorderFactory.createEtchedBorder());
        return p;
    }

    private void initListener() {
        JButton open = (JButton) obj.get("open");
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PositionStockOpenDialog().setVisible(true);
            }
        });

//        JPanel p = (JPanel) obj.get("main");
//        p.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 3) {
//                    // test
//                    ObjectStockComputeParam param = new ObjectStockComputeParam();
//                    param.setName("广晟有色");
//                    param.setCode("600399");
//                    param.setOpen_price(60.);
//                    param.setLot(2);
//                    param.setLoss_price(56.);
//                    new PositionStockOpenFixedDialog(param).setVisible(true);
//                }
//            }
//        });
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

    void adjustSize() {
        PositionStockTable table = (PositionStockTable) obj.get("table");
        table.adjustWitdh();
    }
}
