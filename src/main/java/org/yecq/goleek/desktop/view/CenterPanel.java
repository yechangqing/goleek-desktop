package org.yecq.goleek.desktop.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 中央主信息显示区
 *
 * @author yecq
 */
class CenterPanel extends JPanel {

    private Map obj;

    CenterPanel() {
        this.obj = new HashMap();
        this.obj.put("root", this);
        initView();
        initListener();
    }

    private void initView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        this.add(new CommonPanel(), c);

        c.gridy++;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        JTabbedPane jtp = new JTabbedPane(JTabbedPane.LEFT);
        FuturesPanel fp = new FuturesPanel();
        this.obj.put("futures_panel", fp);
        jtp.add("期货", fp);
        StockPanel sp = new StockPanel();
        this.obj.put("stock_panel", sp);
        jtp.add("股票", sp);
        EtfPanel ep = new EtfPanel();
        this.obj.put("etf_panel", ep);
        jtp.add("ETF", ep);
        jtp.setEnabledAt(2, false);
        this.add(jtp, c);

        c.gridy++;
        c.weighty = 1;
//        this.add(getFillPanel(), c);
    }

    private JPanel getFillPanel() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(0, 0));
        return p;
    }

    private void initListener() {

    }

    void adjustSize() {
        FuturesPanel fp = (FuturesPanel) obj.get("futures_panel");
        fp.adjustSize();

        StockPanel sp = (StockPanel) obj.get("stock_panel");
        sp.adjustSize();
    }

    void reload() {
        FuturesPanel fp = (FuturesPanel) obj.get("futures_panel");
        fp.reload();

        StockPanel sp = (StockPanel) obj.get("stock_panel");
        sp.reload();
    }
}
