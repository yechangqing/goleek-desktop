package org.yecq.goleek.desktop.view;

import javax.swing.JDialog;

/**
 *
 * @author yecq
 */
class PositionStockOpenFixedDialog extends JDialog {

//    private Map obj;
//    private ObjectStockComputeParam param;
//
//    PositionStockOpenFixedDialog(ObjectStockComputeParam param) {
//        super(MainFrame.getInstance());
//        this.obj = new HashMap();
//        this.obj.put("root", this);
//        this.param = param;
//
//        initView();
//        initData();
//        initListener();
//    }
//
//    private void initView() {
//        this.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 0;
//        c.insets = new Insets(10, 10, 10, 10);
//        this.add(getCenterPanel(), c);
//        this.setResizable(false);
//        this.setModal(true);
//        this.setTitle("股票买入-" + this.param.getName());
//        this.pack();
//        Point point = MainFrame.getInstance().getLocation();
//        Dimension dim = MainFrame.getInstance().getSize();
//        Dimension dd = this.getSize();
//        int x = (dim.width - dd.width) / 2 + point.x;
//        int y = (dim.height - dd.height) / 4 + point.y;
//        this.setLocation(x, y);
//    }
//
//    private JPanel getCenterPanel() {
//        JPanel p = new JPanel();
//        p.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 0;
//        c.anchor = GridBagConstraints.WEST;
//        c.insets = new Insets(0, 0, 0, 0);
//        p.add(new JLabel("名称"), c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 0, 0, 0);
//        p.add(new JLabel("代号"), c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 0, 0, 0);
//        p.add(new JLabel("数量"), c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 0, 0, 0);
//        p.add(new JLabel("买入价"), c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 0, 0, 0);
//        p.add(new JLabel("止损价"), c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 0, 0, 0);
//        p.add(new JLabel("时间"), c);
//
//        c.gridy++;
//        c.gridwidth = 2;
//        c.anchor = GridBagConstraints.CENTER;
//        c.insets = new Insets(10, 0, 0, 0);
//        JButton confirm = new JButton("确定");
//        confirm.setPreferredSize(new Dimension(65, 22));
//        p.add(confirm, c);
//
//        c.gridy = 0;
//        c.gridx++;
//        c.weightx = 1;
//        c.gridwidth = 1;
//        c.insets = new Insets(5, 15, 0, 0);
//        c.anchor = GridBagConstraints.WEST;
//        JTextField name = new JTextField();
//        Dimension dd = new Dimension(90, 22);
//        name.setPreferredSize(dd);
//        name.setEditable(false);
//        this.obj.put("name", name);
//        p.add(name, c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 15, 0, 0);
//        JTextField code = new JTextField();
//        code.setPreferredSize(dd);
//        code.setEditable(false);
//        this.obj.put("code", code);
//        p.add(code, c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 15, 0, 0);
//        JTextField lot = new JTextField();
//        lot.setPreferredSize(new Dimension(60, 22));
//        this.obj.put("lot", lot);
//        p.add(lot, c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 15, 0, 0);
//        JTextField open_price = new JTextField();
//        open_price.setPreferredSize(dd);
//        this.obj.put("open_price", open_price);
//        p.add(open_price, c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 15, 0, 0);
//        JTextField loss_price = new JTextField();
//        loss_price.setPreferredSize(new Dimension(80, 22));
//        this.obj.put("loss_price", loss_price);
//        p.add(loss_price, c);
//
//        c.gridy++;
//        c.insets = new Insets(5, 15, 0, 0);
//        DateChooser date = new DateChooser();
//        date.setPreferredSize(dd);
//        p.add(date, c);
//
//        return p;
//    }
//
//    private void initData() {
//        JTextField name = (JTextField) obj.get("name");
//        name.setText(param.getName());
//
//        JTextField code = (JTextField) obj.get("code");
//        code.setText(this.param.getCode() + "");
//
//        JTextField lot = (JTextField) obj.get("lot");
//        lot.setText(this.param.getLot() + "");
//
//        JTextField open_price = (JTextField) obj.get("open_price");
//        open_price.setText(this.param.getOpen_price() + "");
//
//        JTextField loss_price = (JTextField) obj.get("loss_price");
//        loss_price.setText(this.param.getLoss_price() + "");
//    }
//
//    private void initListener() {
//
//    }
}
