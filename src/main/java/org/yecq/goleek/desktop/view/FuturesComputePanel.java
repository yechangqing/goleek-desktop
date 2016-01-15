package org.yecq.goleek.desktop.view;

import java.awt.Color;
import org.yecq.goleek.desktop.view.surface.Dimension;
import org.yecq.goleek.desktop.view.surface.FlowLayout;
import org.yecq.goleek.desktop.view.surface.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import org.yecq.goleek.desktop.view.surface.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;

/**
 *
 * @author yecq
 */
class FuturesComputePanel extends JPanel {

    private static FuturesComputePanel single = null;   // swing中一个组件不能同时存在两个容器中
    private Map obj;
    private FuturesComputePanelModel model;

    static FuturesComputePanel getInstance() {
        if (single == null) {
            single = new FuturesComputePanel();
        }
        return single;
    }

    private FuturesComputePanel() {
        this.obj = new HashMap();
        this.obj.put("root", this);

        initView();
        initData();
        initListener();
        this.model = new FuturesComputePanelModel(this.obj);
    }

    private void initView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(2, 10, 0, 10);
        JLabel t = new JLabel("开仓计算");
        this.add(t, c);

        c.gridy++;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 0);
        this.add(new JSeparator(JSeparator.HORIZONTAL), c);

        c.gridy++;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 10, 10, 10);
        this.add(getComputePanelActually(), c);
        this.setBorder(BorderFactory.createEtchedBorder());

    }

    private JPanel getComputePanelActually() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.insets = new Insets(5, 0, 0, 0);
        JLabel title = new JLabel();
        title.setPreferredSize(new Dimension(120, 40));
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        title.setFont(font);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.obj.put("contract_note", title);
        p.add(title, c);

        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridy++;
        c.insets = new Insets(0, 0, 0, 0);
        p.add(new JLabel("合约"), c);

        c.gridy++;
        p.add(new JLabel("方向"), c);

        c.gridy++;
        p.add(new JLabel("开仓价格"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("止损价"), c);

        c.gridy++;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 5, 0);
        JSeparator sp = new JSeparator();
        p.add(sp, c);

        c.gridy++;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("手数"), c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.gridx++;
        c.insets = new Insets(0, 10, 0, 0);
        JComboBox con = new JComboBox();
        con.setPreferredSize(new Dimension(90, 22));
        this.obj.put("contract", con);
        p.add(con, c);

        c.gridy++;
        c.insets = new Insets(0, 5, 0, 0);
        JPanel tmp = new JPanel();
        FlowLayout fl = new FlowLayout();
        fl.setHgap(9);
        tmp.setLayout(fl);
        JRadioButton buy = new JRadioButton("多");
        buy.setActionCommand("多");
        this.obj.put("buy", buy);
        JRadioButton sell = new JRadioButton("空");
        sell.setActionCommand("空");
        this.obj.put("sell", sell);
        ButtonGroup bg = new ButtonGroup();
        this.obj.put("direct", bg);
        bg.add(buy);
        bg.add(sell);
        tmp.add(buy);
        tmp.add(sell);
        p.add(tmp, c);

        c.gridy++;
        c.insets = new Insets(0, 10, 0, 0);
        JTextField open = new JTextField();
        open.setPreferredSize(new Dimension(90, 22));
        this.obj.put("open_price", open);
        p.add(open, c);

        c.gridy++;
        c.insets = new Insets(5, 10, 0, 0);
        tmp = new JPanel();
        tmp.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        d.anchor = GridBagConstraints.WEST;
        d.gridx = 0;
        d.gridy = 0;
        JTextField loss = new JTextField();
        loss.setPreferredSize(new Dimension(70, 22));
        this.obj.put("loss_price", loss);
        JLabel std = new JLabel();
        std.setPreferredSize(new Dimension(40, 22));
        std.setHorizontalAlignment(JLabel.CENTER);
        std.setVerticalAlignment(JLabel.CENTER);
        std.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        this.obj.put("std_lot", std);
        tmp.add(loss, d);
        d.gridx++;
        d.insets = new Insets(0, 2, 0, 0);
        tmp.add(std, d);
        p.add(tmp, c);

        c.gridy += 2;
        c.insets = new Insets(5, 10, 0, 0);
        JTextField lot = new JTextField();
        lot.setPreferredSize(new Dimension(90, 22));
        this.obj.put("lot", lot);
        p.add(lot, c);

        c.gridx--;
        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(3, 5, 0, 0);
        tmp = new JPanel();
        tmp.setLayout(new GridBagLayout());
        GridBagConstraints e = new GridBagConstraints();
        e.gridx = 0;
        e.anchor = GridBagConstraints.WEST;
        JLabel pp1 = new JLabel();
        pp1.setPreferredSize(new Dimension(90, 40));
        pp1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        font = new Font(Font.MONOSPACED, Font.BOLD, 18);
        pp1.setFont(font);
        pp1.setHorizontalAlignment(JLabel.CENTER);
        this.obj.put("position_percent", pp1);
        tmp.add(pp1, e);

        e.gridx++;
        e.weightx = 1;
        e.insets = new Insets(0, 3, 0, 0);
        JLabel lp1 = new JLabel();
        lp1.setPreferredSize(new Dimension(90, 40));
        lp1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        font = new Font(Font.MONOSPACED, Font.BOLD, 18);
        lp1.setFont(font);
        lp1.setHorizontalAlignment(JLabel.CENTER);
        this.obj.put("loss_percent", lp1);
        tmp.add(lp1, e);
        p.add(tmp, c);

        c.gridy++;
        c.weighty = 1;
        tmp = new JPanel();
        tmp.setPreferredSize(new Dimension(0, 0));
        p.add(tmp, c);

        this.obj.put("main", p);

        return p;
    }

    private void initData() {
    }

    // 得到当前界面合约的信息，如没有则返回null
    Map<String, Object> getCurrentCompute() {
        String tmp = ((JLabel) obj.get("std_lot")).getText().trim();
        if (tmp.equals("")) {
            return null;
        }

        Map<String, Object> map = new HashMap();
        FuturesInfoBean contract = (FuturesInfoBean) ((JComboBox) obj.get("contract")).getSelectedItem();
        map.put("contract", contract);

        ButtonGroup bg = (ButtonGroup) obj.get("direct");
        map.put("direct", ((ButtonModel) bg.getSelection()).getActionCommand());

        tmp = ((JTextField) obj.get("loss_price")).getText().trim();
        map.put("loss_price", tmp);

        return map;
    }

    private void initListener() {
        final JComboBox jcb = (JComboBox) obj.get("contract");
        jcb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 先清除input
                ButtonGroup bg = (ButtonGroup) obj.get("direct");
                bg.clearSelection();
                JTextField open_price = (JTextField) obj.get("open_price");
                JTextField loss_price = (JTextField) obj.get("loss_price");
                open_price.setText("");
                loss_price.setText("");
                model.setInputInvalid();
                model.fireInputNotify();
                // 载入已有的配置
                String contract = ((FuturesInfoBean) jcb.getSelectedItem()).getCode();
                Map<String, String> map = model.remember.get(contract);
                if (map != null) {
                    String direct = map.get("direct");
                    String op = map.get("open_price");
                    String lp = map.get("loss_price");
                    Enumeration<AbstractButton> enu = bg.getElements();
                    while (enu.hasMoreElements()) {
                        AbstractButton bu = enu.nextElement();
                        if (bu.getActionCommand().equals(direct)) {
                            bu.setSelected(true);
                            model.setInput(direct, Double.parseDouble(op), Double.parseDouble(lp));
                            break;
                        }
                    }
                    open_price.setText(op);
                    loss_price.setText(lp);
                }
                model.fireContractNotify();
                model.fireDirectNotify();
                model.fireInputNotify();
            }
        });

        JRadioButton buy = (JRadioButton) obj.get("buy");
        buy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.fireInputNotify();
                model.fireDirectNotify();
            }
        });

        JRadioButton sell = (JRadioButton) obj.get("sell");
        sell.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.fireInputNotify();
                model.fireDirectNotify();
            }
        });

        JPanel p = (JPanel) obj.get("main");
        p.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 3) {
                    // test
//                    ObjectFuturesComputeParam param = new ObjectFuturesComputeParam();
//                    param.setContract("rb1601");
//                    param.setDirect("多");
//                    param.setOpen_price(3000.);
//                    param.setLot(2);
//                    param.setLoss_price(2900.);
//                    new PositionFuturesOpenFixedDialog(param).setVisible(true);
                }
            }
        });

        JTextField op = (JTextField) obj.get("open_price");
        op.addKeyListener(new TextFieldKeyAdapter());
        op.addFocusListener(new TextFieldFocusListener(op, "open_price"));

        JTextField lp = (JTextField) obj.get("loss_price");
        lp.addKeyListener(new TextFieldKeyAdapter());
        lp.addFocusListener(new TextFieldFocusListener(lp, "loss_price"));

        JTextField lo = (JTextField) obj.get("lot");
        lo.addKeyListener(new TextFieldKeyAdapter());
        lo.addFocusListener(new TextFieldFocusListener(lo, "lot"));
    }

    // 定义几个监听类
    private class TextFieldFocusListener implements FocusListener {

        private JTextField jtf;
        private String old_value;
        private String new_value;
        private String type;

        TextFieldFocusListener(JTextField jtf, String type) {
            this.jtf = jtf;
            this.type = type;
        }

        @Override
        public void focusGained(FocusEvent e) {
            this.old_value = this.jtf.getText().trim();
        }

        @Override
        public void focusLost(FocusEvent e) {
            this.new_value = this.jtf.getText().trim();
            if (!this.old_value.equals(this.new_value)) {
                if (this.type.equals("open_price")) {
                    model.fireInputNotify();
                } else if (type.equals("loss_price")) {
                    model.fireInputNotify();
                } else if (type.equals("lot")) {
                    model.fireInputDefinedNotify();
                } else {
                    throw new IllegalArgumentException("还未做");
                }
            }
        }
    }

    private class TextFieldKeyAdapter extends KeyAdapter {

        // 按下回车即完成输入
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //让其失去焦点
                JPanel p = (JPanel) obj.get("root");
                p.requestFocusInWindow();//以便触发失去焦点的事件                  
            }
        }
    }
}
