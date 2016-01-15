package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.FuturesAgent;
import org.yecq.goleek.desktop.bean.param.FuturesAddBean;
import org.yecq.goleek.desktop.cache.FuturesCache;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;
import org.yecq.goleek.desktop.service.core.Util;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author yecq
 */
class FuturesAddDialog extends JDialog {

    private Map obj;

    FuturesAddDialog() {
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
        this.setTitle("期货合约增加");
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
        c.anchor = GridBagConstraints.WEST;
        p.add(new JLabel("代号"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("名称"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("保证金"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("单位"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("变化"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("交易所"), c);

        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(65, 22));
        this.obj.put("confirm", confirm);
        p.add(confirm, c);

        c.gridy = 0;
        c.gridx++;
        c.weightx = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 25, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        JTextField code = new JTextField();
        Dimension d = new Dimension(90, 22);
        code.setPreferredSize(d);
        this.obj.put("code", code);
        p.add(code, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JTextField name = new JTextField();
        name.setPreferredSize(d);
        this.obj.put("name", name);
        p.add(name, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JTextField margin = new JTextField();
        margin.setPreferredSize(d);
        this.obj.put("margin", margin);
        p.add(margin, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JTextField unit = new JTextField();
        unit.setPreferredSize(d);
        this.obj.put("unit", unit);
        p.add(unit, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JTextField min = new JTextField();
        min.setPreferredSize(d);
        this.obj.put("min", min);
        p.add(min, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JComboBox exchange = new JComboBox();
        exchange.setPreferredSize(d);
        this.obj.put("exchange", exchange);
        p.add(exchange, c);

        return p;
    }

    private void initData() {
        ComboBoxModel model = new DefaultComboBoxModel(Root.getInstance().getBean(FuturesCache.class).getExchangeNames());
        ((JComboBox) obj.get("exchange")).setModel(model);
    }

    private void initListener() {
        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String t = ((JTextField) obj.get("code")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("代号为空");
                    return;
                }
                String code = t;

                t = ((JTextField) obj.get("name")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("名称为空");
                    return;
                }
                String name = t;

                Double margin = null;
                t = ((JTextField) obj.get("margin")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("保证金为空");
                    return;
                }
                margin = Util.getDouble(t);
                if (margin == null || (margin >= 1 || margin <= 0)) {
                    Vutil.showErrorMsg("保证金格式错误");
                    return;
                }

                Integer unit = null;
                t = ((JTextField) obj.get("unit")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("单位为空");
                    return;
                }
                unit = Util.getInt(t);
                if (unit == null || unit <= 0) {
                    Vutil.showErrorMsg("单位格式错误");
                    return;
                }

                Double min = null;
                t = ((JTextField) obj.get("min")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("变化为空");
                    return;
                }
                min = Util.getDouble(t);
                if (min == null || min <= 0) {
                    Vutil.showErrorMsg("变化格式错误");
                    return;
                }

                Object tt = ((JComboBox) obj.get("exchange")).getSelectedItem();
                if (tt == null) {
                    Vutil.showErrorMsg("交易所为空");
                    return;
                }
                String exchange = (String) tt;

                if (JOptionPane.showConfirmDialog(null, "是否增加合约？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Sret sr = Root.getInstance().getBean(FuturesAgent.class).add(new FuturesAddBean(code, name, margin, unit, min, exchange));
                    if (!sr.isOk()) {
                        Vutil.showErrorMsg(sr.getMessage());
                    } else {
                        ((JDialog) obj.get("root")).dispose();
                    }
                }
            }
        });
    }
}
