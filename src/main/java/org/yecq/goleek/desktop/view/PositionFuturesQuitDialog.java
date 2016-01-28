package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.PositionFuturesAgent;
import org.yecq.goleek.desktop.bean.param.PositionFuturesEditBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.yecq.goleek.desktop.cache.PositionFuturesCache;
import org.yecq.goleek.desktop.service.core.Util;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.yecq.baseframework.plain.core.Root;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
class PositionFuturesQuitDialog extends JDialog {

    private Map obj;
    private PositionFuturesInfoBean pos;

    PositionFuturesQuitDialog(PositionFuturesInfoBean pos) {
        super(MainFrame.getInstance());
        this.obj = new HashMap();
        this.pos = pos;
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
        this.setTitle(this.pos.getContract() + "-退出");
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
        p.add(new JLabel("价格"), c);

        c.gridx++;
        c.weightx = 1;
        c.insets = new Insets(0, 15, 0, 0);
        JTextField price = new JTextField();
        price.setPreferredSize(new Dimension(90, 22));
        this.obj.put("price", price);
        p.add(price, c);

        // 获取离场动作
        String[] actions = Root.getInstance().getBean(PositionFuturesCache.class).getActions(pos.getDirect());
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(5, 0, 0, 0);
        JPanel p1 = new JPanel();
        FlowLayout fl = new FlowLayout();
        fl.setHgap(15);
        p1.setLayout(fl);
        JRadioButton bu1 = new JRadioButton(actions[0]);
        bu1.setActionCommand(actions[0]);
        this.obj.put("action1", bu1);
        JRadioButton bu2 = new JRadioButton(actions[1]);
        bu2.setActionCommand(actions[1]);
        this.obj.put("action2", bu2);
        p1.add(bu1);
        p1.add(bu2);
        ButtonGroup bg = new ButtonGroup();
        bg.add(bu1);
        bg.add(bu2);
        this.obj.put("group", bg);
        p.add(p1, c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(65, 22));
        this.obj.put("confirm", confirm);
        p.add(confirm, c);

        return p;
    }

    private void initData() {
        JTextField price = (JTextField) obj.get("price");
        price.setText(this.pos.getQuit_price() + "");

        String action = this.pos.getAction();
        ButtonGroup bg = (ButtonGroup) obj.get("group");
        Enumeration<AbstractButton> enu = bg.getElements();
        while (enu.hasMoreElements()) {
            AbstractButton bu = enu.nextElement();
            if (bu.getActionCommand().equals(action)) {
                bu.setSelected(true);
                break;
            }
        }
        if (bg.getSelection() == null) {
            Vutil.showErrorMsg("退出动作出错");
        }
    }

    private void initListener() {
        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String t = ((JTextField) obj.get("price")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("价格为空");
                    return;
                }
                Double price = Util.getDouble(t);
                if (price == null || price <= 0) {
                    Vutil.showErrorMsg("价格错误");
                    return;
                }

                ButtonGroup bg = (ButtonGroup) obj.get("group");
                String action = bg.getSelection().getActionCommand();

                PositionFuturesEditBean bean = new PositionFuturesEditBean(pos.getId(), action, price);
                Sret sr = Root.getInstance().getBean(PositionFuturesAgent.class).editQuit(bean);
                if (!sr.isOk()) {
                    Vutil.showErrorMsg(sr.getMessage());
                } else {
                    ((JDialog) obj.get("root")).dispose();
                }
            }
        });
    }
}
