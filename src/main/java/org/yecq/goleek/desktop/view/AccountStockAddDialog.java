package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.AccountAgent;
import org.yecq.goleek.desktop.bean.param.AccountAddBean;
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
import javax.swing.JButton;
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
class AccountStockAddDialog extends JDialog {

    private Map obj;

    AccountStockAddDialog() {
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
        this.setTitle("股票账户增加");
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
        p.add(new JLabel("账号"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("公司"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("权益"), c);

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
        JTextField company = new JTextField();
        company.setPreferredSize(d);
        this.obj.put("company", company);
        p.add(company, c);

        c.gridy++;
        c.insets = new Insets(5, 25, 0, 0);
        JTextField money = new JTextField();
        money.setPreferredSize(d);
        this.obj.put("money", money);
        p.add(money, c);

        return p;
    }

    private void initData() {

    }

    private void initListener() {
        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog me = me();
                String code = ((JTextField) obj.get("code")).getText().trim();
                if (code.equals("")) {
                    Vutil.showErrorMsg("账号为空");
                    return;
                }

                String company = ((JTextField) obj.get("company")).getText().trim();
                if (company.equals("")) {
                    Vutil.showErrorMsg("公司为空");
                    return;
                }

                double money = 0.;
                String m = ((JTextField) obj.get("money")).getText().trim();
                if (!m.equals("")) {
                    Double tmp = Util.getDouble(m);
                    if (tmp == null || tmp < 0) {
                        Vutil.showErrorMsg("权益应为>=0的数");
                        return;
                    }
                    money = tmp;
                }

                if (JOptionPane.showConfirmDialog(me(), "是否增加账户？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    AccountAddBean bean = new AccountAddBean(code, company, money, "股票");
                    Sret sr = Root.getInstance().getBean(AccountAgent.class).add(bean);
                    if (!sr.isOk()) {
                        Vutil.showErrorMsg(sr.getMessage());
                        return;
                    }
                    me().dispose();
                }
            }
        });
    }

    private JDialog me() {
        return (JDialog) obj.get("root");
    }
}
