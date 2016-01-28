package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.PositionStockAgent;
import org.yecq.goleek.desktop.bean.param.PositionStockOpenBean;
import org.yecq.goleek.desktop.bean.result.AccountStockInfoBean;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.yecq.goleek.desktop.cache.AccountStockCache;
import org.yecq.goleek.desktop.cache.StockCache;
import org.yecq.goleek.desktop.service.core.Util;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
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
import org.yecq.baseframework.plain.core.Root;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
class PositionStockOpenDialog extends JDialog {

    private Map obj;

    PositionStockOpenDialog() {
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
        this.setTitle("股票买入");
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
        c.insets = new Insets(0, 0, 0, 0);
        p.add(new JLabel("名称"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("代号"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("数量"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("买入价"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("止损价"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("日期"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("账户"), c);

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
        c.weightx = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 15, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        JComboBox name = new JComboBox();
        Dimension dd = new Dimension(90, 22);
        name.setPreferredSize(dd);
        this.obj.put("name", name);
        p.add(name, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JTextField code = new JTextField();
        code.setPreferredSize(dd);
        code.setEditable(false);
        this.obj.put("code", code);
        p.add(code, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
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
        JTextField loss_price = new JTextField();
        loss_price.setPreferredSize(new Dimension(80, 22));
        this.obj.put("loss_price", loss_price);
        p.add(loss_price, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        DateChooser date = new DateChooser();
        date.setPreferredSize(dd);
        this.obj.put("date", date);
        p.add(date, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JComboBox acc = new JComboBox();
        acc.setPreferredSize(dd);
        this.obj.put("account", acc);
        p.add(acc, c);

        return p;
    }

    private void initData() {
        JComboBox con = (JComboBox) obj.get("name");
        List<StockInfoBean> list = Root.getInstance().getBean(StockCache.class).getInterest();
        StockInfoBean[] ss = new StockInfoBean[list.size()];
        list.toArray(ss);
        ComboBoxModel model = new DefaultComboBoxModel(ss);
        con.setModel(model);

        Object o = con.getSelectedItem();
        if (o != null) {
            JTextField code = (JTextField) obj.get("code");
            String c = ((StockInfoBean) o).getCode();
            code.setText(c);
        }

        JComboBox acc = (JComboBox) obj.get("account");
        List<AccountStockInfoBean> list1 = Root.getInstance().getBean(AccountStockCache.class).getUsed();
        AccountStockInfoBean[] accs = new AccountStockInfoBean[list1.size()];
        list1.toArray(accs);
        model = new DefaultComboBoxModel(accs);
        acc.setModel(model);

        // 载入当前的计算结果
        Map<String, Object> map = StockComputePanel.getInstance().getCurrentCompute();
        if (map != null) {
            con.setSelectedItem(map.get("stock"));
            JTextField loss_price = (JTextField) obj.get("loss_price");
            loss_price.setText((String) map.get("loss_price"));
        }
    }

    private void initListener() {
        final JComboBox name = (JComboBox) obj.get("name");
        name.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = name.getSelectedItem();
                JTextField code = (JTextField) obj.get("code");
                if (o == null) {
                    code.setText("");
                } else {
                    code.setText(((StockInfoBean) o).getCode());
                }
            }
        });

        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = ((JComboBox) obj.get("name")).getSelectedItem();
                if (o == null) {
                    Vutil.showErrorMsg("名称为空");
                    return;
                }
                String code = ((StockInfoBean) o).getCode();

                String t = ((JTextField) obj.get("lot")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("数量为空");
                    return;
                }
                Integer lot = Util.getInt(t);
                if (lot == null || lot <= 0) {
                    Vutil.showErrorMsg("错误的数量");
                    return;
                }
                if (lot > 50) {
                    Vutil.showPlateMsg("你特么地搞错数量了吧");
                    return;
                }

                t = ((JTextField) obj.get("open_price")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("买入价为空");
                    return;
                }
                Double open_price = Util.getDouble(t);
                if (open_price == null || open_price <= 0) {
                    Vutil.showErrorMsg("错误的买入价");
                    return;
                }

                t = ((JTextField) obj.get("loss_price")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("止损价为空");
                    return;
                }
                Double loss_price = Util.getDouble(t);
                if (loss_price == null || loss_price <= 0) {
                    Vutil.showErrorMsg("错误的止损价");
                    return;
                }

                t = ((DateChooser) obj.get("date")).getDateString().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("日期为空");
                    return;
                }
                String open_date = t;

                o = ((JComboBox) obj.get("account")).getSelectedItem();
                if (o == null) {
                    Vutil.showErrorMsg("账号为空");
                    return;
                }
                String account = ((AccountStockInfoBean) o).getCode();

                if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否买入？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    PositionStockOpenBean open = new PositionStockOpenBean();
                    open.setCode(code);
                    open.setLot(lot);
                    open.setOpen_price(open_price);
                    open.setOpen_date(open_date);
                    open.setQuit_price(loss_price);
                    open.setAccount(account);
                    Sret sr = Root.getInstance().getBean(PositionStockAgent.class).open(open);
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
