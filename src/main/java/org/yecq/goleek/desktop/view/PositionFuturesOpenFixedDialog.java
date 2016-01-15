package org.yecq.goleek.desktop.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author yecq
 */
class PositionFuturesOpenFixedDialog extends JDialog {

    private Map obj;
    private ObjectFuturesInput param;

    PositionFuturesOpenFixedDialog(ObjectFuturesInput param) {
        super(MainFrame.getInstance());
        this.obj = new HashMap();
        this.obj.put("root", this);
        this.param = param;

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
//        this.setTitle("期货开仓-" + this.param.getContract());
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
        p.add(new JLabel("时间"), c);

        c.gridy++;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 0, 0);
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(65, 22));
        p.add(confirm, c);

        c.gridy = 0;
        c.gridx++;
        c.gridwidth = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 15, 0, 0);
        JTextField contract = new JTextField();
        Dimension dd = new Dimension(90, 22);
        contract.setPreferredSize(dd);
        contract.setEditable(false);
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
        buy.setEnabled(false);
        this.obj.put("buy", buy);
        tmp.add(buy, g);
        g.gridx++;
        g.weightx = 1;
        g.insets = new Insets(0, 8, 0, 0);
        JRadioButton sell = new JRadioButton("空");
        sell.setEnabled(false);
        this.obj.put("sell", sell);
        tmp.add(sell, g);
        ButtonGroup bg = new ButtonGroup();
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
        JTextField loss_price = new JTextField();
        loss_price.setPreferredSize(new Dimension(80, 22));
        this.obj.put("loss_price", loss_price);
        p.add(loss_price, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        DateChooser date = new DateChooser();
        date.setPreferredSize(dd);
        p.add(date, c);

        return p;
    }

    private void initData() {
        JTextField con = (JTextField) obj.get("contract");
//        con.setText(this.param.getContract().getCode());

        if (param.getDirect().equals("多")) {
            ((JRadioButton) obj.get("buy")).setSelected(true);
        } else {
            ((JRadioButton) obj.get("sell")).setSelected(true);
        }

        JTextField lot = (JTextField) obj.get("lot");
//        lot.setText(this.param.getLot() + "");

        JTextField open_price = (JTextField) obj.get("open_price");
        open_price.setText(this.param.getOpen_price() + "");

        JTextField loss_price = (JTextField) obj.get("loss_price");
        loss_price.setText(this.param.getLoss_price() + "");
    }

    private void initListener() {

    }
}
