package org.yecq.goleek.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.yecq.goleek.desktop.agent.PositionFuturesAgent;
import org.yecq.goleek.desktop.bean.param.PositionFuturesOpenBean;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.yecq.goleek.desktop.cache.AccountFuturesCache;
import org.yecq.goleek.desktop.cache.FuturesCache;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;
import org.yecq.goleek.desktop.service.core.Util;
import org.yecq.goleek.desktop.view.surface.Dimension;
import org.yecq.goleek.desktop.view.surface.Insets;

/**
 *
 * @author yecq
 */
class PositionFuturesOpenDialog extends JDialog {

    private Map obj;

    PositionFuturesOpenDialog() {
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
        this.setTitle("期货开仓");
        this.pack();
        Point point = MainFrame.getInstance().getLocation();
        java.awt.Dimension dim = MainFrame.getInstance().getSize();
        java.awt.Dimension dd = this.getSize();
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
        c.insets = new Insets(0, 0, 0, 0);
        p.add(new JLabel("品种"), c);

        c.gridy++;
        c.insets = new Insets(3, 0, 0, 0);
        p.add(new JLabel("方向"), c);

        c.gridy++;
        c.insets = new Insets(4, 0, 0, 0);
        p.add(new JLabel("数量"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("开仓价"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("止损价"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("日期"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("账号"), c);

        c.gridy++;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 0, 0);
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(65, 22));
        this.obj.put("confirm", confirm);
        p.add(confirm, c);

        c.gridy = 0;
        c.gridx++;
        c.gridwidth = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 15, 0, 0);
        JComboBox contract = new JComboBox();
        Dimension dd = new Dimension(90, 22);
        contract.setPreferredSize(dd);
        this.obj.put("contract", contract);
        p.add(contract, c);

        c.gridy++;
        c.insets = new Insets(3, 15, 0, 0);
        JPanel tmp = new JPanel();
        tmp.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.anchor = GridBagConstraints.WEST;
        JRadioButton buy = new JRadioButton("多");
        buy.setActionCommand("多");
        tmp.add(buy, g);
        g.gridx++;
        g.weightx = 1;
        g.insets = new Insets(0, 8, 0, 0);
        JRadioButton sell = new JRadioButton("空");
        sell.setActionCommand("空");
        tmp.add(sell, g);
        ButtonGroup bg = new ButtonGroup();
        this.obj.put("direct", bg);
        bg.add(buy);
        bg.add(sell);
        p.add(tmp, c);

        c.gridy++;
        c.insets = new Insets(4, 15, 0, 0);
        JTextField lot = new JTextField();
        lot.setPreferredSize(new Dimension(60, 22));
        this.obj.put("lot", lot);
        p.add(lot, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JTextField open_price = new JTextField();
        open_price.setPreferredSize(dd);
        this.obj.put("open_price", open_price);
        p.add(open_price, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JTextField quit_price = new JTextField();
        quit_price.setPreferredSize(new Dimension(80, 22));
        this.obj.put("quit_price", quit_price);
        p.add(quit_price, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        DateChooser date = new DateChooser();
        date.setPreferredSize(dd);
        this.obj.put("date", date);
        p.add(date, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JComboBox account = new JComboBox();
        account.setPreferredSize(dd);
        this.obj.put("account", account);
        p.add(account, c);

        return p;
    }

    private void initData() {
        JComboBox con = (JComboBox) obj.get("contract");
        List<FuturesInfoBean> list = Root.getInstance().getBean(FuturesCache.class).getInterest();
        FuturesInfoBean[] ss = new FuturesInfoBean[list.size()];
        list.toArray(ss);
        ComboBoxModel model = new DefaultComboBoxModel(ss);
        con.setModel(model);

        JComboBox acc = (JComboBox) obj.get("account");
        List<AccountFuturesInfoBean> list1 = Root.getInstance().getBean(AccountFuturesCache.class).getUsed();
        AccountFuturesInfoBean[] accs = new AccountFuturesInfoBean[list1.size()];
        list1.toArray(accs);
        ComboBoxModel m = new DefaultComboBoxModel(accs);
        acc.setModel(m);

        // 装在已经选择的合约信息
        Map<String, Object> map = FuturesComputePanel.getInstance().getCurrentCompute();
        if (map != null) {
            con.setSelectedItem(map.get("contract"));
            ButtonGroup bg = (ButtonGroup) obj.get("direct");
            Enumeration<AbstractButton> enu = bg.getElements();
            while (enu.hasMoreElements()) {
                AbstractButton bu = enu.nextElement();
                if (bu.getActionCommand().equals(map.get("direct"))) {
                    bu.setSelected(true);
                    break;
                }
            }
            JTextField loss_price = (JTextField) obj.get("quit_price");
            loss_price.setText((String) map.get("loss_price"));
        }
    }

    private void initListener() {
        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = ((JComboBox) obj.get("contract")).getSelectedItem();
                if (o == null) {
                    Vutil.showErrorMsg("未选择合约");
                    return;
                }
                String contract = ((FuturesInfoBean) o).getCode();
                if (contract.equals("")) {
                    Vutil.showErrorMsg("合约为空");
                    return;
                }

                ButtonGroup bg = (ButtonGroup) obj.get("direct");
                o = bg.getSelection();
                if (o == null) {
                    Vutil.showErrorMsg("未选择方向");
                    return;
                }
                String direct = ((ButtonModel) o).getActionCommand();

                String t = ((JTextField) obj.get("lot")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("手数为空");
                    return;
                }
                Integer lot = Util.getInt(t);
                if (lot == null || lot <= 0) {
                    Vutil.showErrorMsg("错误的手数");
                    return;
                }
                if (lot > 20) {
                    Vutil.showPlateMsg("你特么的搞错手数了吧");
                    return;
                }

                t = ((JTextField) obj.get("open_price")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("开仓价为空");
                    return;
                }
                Double open_price = Util.getDouble(t);
                if (open_price == null || open_price <= 0) {
                    Vutil.showErrorMsg("错误的开仓价");
                    return;
                }

                t = ((JTextField) obj.get("quit_price")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("错误的止损价");
                    return;
                }
                Double quit_price = Util.getDouble(t);
                if (quit_price == null || quit_price <= 0) {
                    Vutil.showErrorMsg("错误的止损价");
                    return;
                }

                String date = ((DateChooser) obj.get("date")).getDateString();
                if (date.equals("")) {
                    Vutil.showErrorMsg("日期为空");
                    return;
                }

                o = ((JComboBox) obj.get("account")).getSelectedItem();
                if (o == null) {
                    Vutil.showErrorMsg("账号为空");
                    return;
                }
                String account = ((AccountFuturesInfoBean) o).getCode();
                if (account.equals("")) {
                    Vutil.showErrorMsg("账号为空");
                    return;
                }

                if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否开仓？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    PositionFuturesOpenBean open = new PositionFuturesOpenBean(contract, direct, lot, open_price, date, quit_price, account);
                    Sret sr = Root.getInstance().getBean(PositionFuturesAgent.class).open(open);
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
