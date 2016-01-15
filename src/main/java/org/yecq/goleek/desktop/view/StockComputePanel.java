package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author yecq
 */
class StockComputePanel extends JPanel {

    private static StockComputePanel single = null;
    private Map obj;
    private StockComputePanelModel model;

    static StockComputePanel getInstance() {
        if (single == null) {
            single = new StockComputePanel();
        }
        return single;
    }

    private StockComputePanel() {
        this.obj = new HashMap();
        this.obj.put("root", this);

        initView();
        initData();
        initListener();
        this.model = new StockComputePanelModel(this.obj);
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
        this.obj.put("stock_note", title);
        p.add(title, c);

        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridy++;
        c.insets = new Insets(0, 0, 0, 0);
        p.add(new JLabel("股票"), c);

        c.gridy++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 5, 0);
        p.add(new JSeparator(JSeparator.HORIZONTAL), c);

        c.gridy++;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("买入价格"), c);

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
        this.obj.put("stock", con);
        p.add(con, c);

        c.gridy += 2;
        c.insets = new Insets(5, 10, 0, 0);
        JTextField open = new JTextField();
        open.setPreferredSize(new Dimension(90, 22));
        this.obj.put("open_price", open);
        p.add(open, c);

        c.gridy++;
        c.insets = new Insets(5, 10, 0, 0);
        JPanel tmp = new JPanel();
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

    Map<String, Object> getCurrentCompute() {
        String tmp = ((JLabel) obj.get("std_lot")).getText().trim();
        if (tmp.equals("")) {
            return null;
        }

        Map<String, Object> map = new HashMap();
        StockInfoBean stock = (StockInfoBean) ((JComboBox) obj.get("stock")).getSelectedItem();
        map.put("stock", stock);

        tmp = ((JTextField) obj.get("loss_price")).getText().trim();
        map.put("loss_price", tmp);

        return map;
    }

    private void initListener() {
        final JComboBox jcb = (JComboBox) obj.get("stock");
        jcb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 清除inputJTextField open_price = (JTextField) obj.get("open_price");
                JTextField open_price = (JTextField) obj.get("open_price");
                JTextField loss_price = (JTextField) obj.get("loss_price");
                open_price.setText("");
                loss_price.setText("");
                // 载入已有配置
                String stock = ((StockInfoBean) jcb.getSelectedItem()).getCode();
                Map<String, String> map = model.remember.get(stock);
                if (map != null) {
                    String op = map.get("open_price");
                    String lp = map.get("loss_price");
                    open_price.setText(op);
                    loss_price.setText(lp);
                }
                model.fireStockNotify();
                model.fireInputNotify();
            }
        });

        JTextField op = (JTextField) obj.get("open_price");
        op.addFocusListener(new TextFieldFocusListener(op, "open_price"));
        op.addKeyListener(new TextFieldKeyAdapter());

        JTextField lp = (JTextField) obj.get("loss_price");
        lp.addFocusListener(new TextFieldFocusListener(lp, "loss_price"));
        lp.addKeyListener(new TextFieldKeyAdapter());

        JTextField lot = (JTextField) obj.get("lot");
        lot.addFocusListener(new TextFieldFocusListener(lot, "lot"));
        lot.addKeyListener(new TextFieldKeyAdapter());
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
