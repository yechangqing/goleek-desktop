package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.StockAgent;
import org.yecq.goleek.desktop.bean.param.StockAddBean;
import org.yecq.goleek.desktop.cache.StockCache;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
class StockAddDialog extends JDialog {

    private Map obj;

    StockAddDialog() {
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
        this.setTitle("股票增加");
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
        c.gridwidth = 1;
        c.gridx++;
        c.weightx = 1;
        c.insets = new Insets(0, 25, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        JTextField code = new JTextField();
        Dimension dd = new Dimension(90, 22);
        code.setPreferredSize(dd);
        this.obj.put("code", code);
        p.add(code, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JTextField name = new JTextField();
        name.setPreferredSize(dd);
        this.obj.put("name", name);
        p.add(name, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JComboBox exchange = new JComboBox();
        exchange.setPreferredSize(dd);
        exchange.setEditable(false);
        this.obj.put("exchange", exchange);
        p.add(exchange, c);

        return p;
    }

    private void initData() {
        String[] n = Root.getInstance().getBean(StockCache.class).getExchangeNames();
        String[] names = new String[n.length + 1];
        names[0] = "";
        for (int i = 0; i < n.length; i++) {
            names[i + 1] = n[i];
        }
        ComboBoxModel model = new DefaultComboBoxModel(names);
        JComboBox ex = (JComboBox) obj.get("exchange");
        ex.setModel(model);
        ex.setEnabled(false);
    }

    private void initListener() {
        JTextField f = (JTextField) obj.get("code");
        f.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                // 根据股票代号选择交易所
                JComboBox ex = (JComboBox) obj.get("exchange");
                JTextField s = (JTextField) e.getComponent();
                String code = s.getText().trim();
                if (code.equals("")) {
                    return;
                }
                String name = Root.getInstance().getBean(StockCache.class).getExchangeNameByCode(code.trim());
                if (name == null) {
                    Vutil.showErrorMsg("证券代码错误");
                    ex.setSelectedIndex(0);
                    return;
                }
                ex.setSelectedItem(name);
            }
        });

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

                // 根据代号确定交易所
                String exchange = Root.getInstance().getBean(StockCache.class).getExchangeNameByCode(code);
                if (exchange == null) {
                    Vutil.showErrorMsg("证券代码错误");
                    return;
                }

                if (JOptionPane.showConfirmDialog(null, "是否添加股票？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    StockAddBean add = new StockAddBean(code, name, exchange);
                    Sret sr = Root.getInstance().getBean(StockAgent.class).add(add);
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
