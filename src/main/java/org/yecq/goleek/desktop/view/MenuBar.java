package org.yecq.goleek.desktop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import org.yecq.goleek.desktop.service.core.Root;

/**
 *
 * @author yecq
 */
class MenuBar extends JMenuBar {

    private Map obj;

    MenuBar() {
        this.obj = new HashMap();
        this.obj.put("root", this);
        initView();
        initListener();
    }

    private void initView() {
        JMenu view = new JMenu("查看(V)");
        view.setMnemonic('v');
        JRadioButtonMenuItem all = new JRadioButtonMenuItem("完整");
        all.setActionCommand("normal");
        this.obj.put("normal", all);
        JRadioButtonMenuItem simple = new JRadioButtonMenuItem("简易");
        simple.setActionCommand("simple");
        this.obj.put("simple", simple);
        ButtonGroup bg = new ButtonGroup();
        this.obj.put("gp", bg);
        bg.add(all);
        bg.add(simple);
        view.add(all);
        view.add(simple);
        String mode = Root.getInstance().getBean("mode", Mode.class).getMode();
        if (mode.equals("normal")) {
            all.setSelected(true);
            all.setEnabled(false);
            simple.setEnabled(true);
        } else if (mode.equals("simple")) {
            simple.setSelected(true);
            all.setEnabled(true);
            simple.setEnabled(false);
        } else {
            Vutil.showErrorMsg("窗口模式设置不正确");
            return;
        }

        JMenu data = new JMenu("数据(D)");
        data.setMnemonic('d');

        JMenu contract = new JMenu("品种(C)");
        contract.setMnemonic('c');
        JMenuItem futures = new JMenuItem("期货");
        this.obj.put("futures", futures);
        JMenuItem stock = new JMenuItem("股票");
        this.obj.put("stock", stock);
        JMenuItem etf = new JMenuItem("ETF");
        etf.setEnabled(false);
        this.obj.put("etf", etf);
        contract.add(futures);
        contract.add(stock);
        contract.add(etf);

        JMenu account = new JMenu("账户(A)");
        account.setMnemonic('a');
        JMenuItem fa = new JMenuItem("期货账户");
        this.obj.put("futures_account", fa);
        JMenuItem sa = new JMenuItem("股票账户");
        this.obj.put("stock_account", sa);
        account.add(fa);
        account.add(sa);

        JMenuItem setting = new JMenuItem("交易参数");
        this.obj.put("setting", setting);

        data.add(contract);
        data.add(account);
        data.add(setting);

        JMenu about = new JMenu("关于(B)");
        JMenuItem version = new JMenuItem("版本");
        version.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "版本 v1.0.surface", "版本", JOptionPane.PLAIN_MESSAGE);
            }
        });
        about.add(version);

        this.add(view);
        this.add(data);
        this.add(about);
    }

    private void initListener() {
        final JRadioButtonMenuItem all = (JRadioButtonMenuItem) obj.get("normal");
        final JRadioButtonMenuItem simple = (JRadioButtonMenuItem) obj.get("simple");
        all.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                all.setEnabled(false);
                simple.setEnabled(true);
                MainFrame.getInstance().switchNormal();
            }
        });

        simple.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                simple.setEnabled(false);
                all.setEnabled(true);
                MainFrame.getInstance().switchSimple();
            }
        });

        JMenuItem futures = (JMenuItem) obj.get("futures");
        futures.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FuturesDialog().setVisible(true);
            }
        });

        JMenuItem stock = (JMenuItem) obj.get("stock");
        stock.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new StockDialog().setVisible(true);
            }
        });

        JMenuItem fa = (JMenuItem) obj.get("futures_account");
        fa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountFuturesDialog().setVisible(true);
            }
        });

        JMenuItem sa = (JMenuItem) obj.get("stock_account");
        sa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountStockDialog().setVisible(true);
            }
        });

        JMenuItem set = (JMenuItem) obj.get("setting");
        set.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new TradeSettingDialog().setVisible(true);
            }
        });
    }
}
