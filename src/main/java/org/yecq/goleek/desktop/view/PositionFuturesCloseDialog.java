package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.PositionFuturesAgent;
import org.yecq.goleek.desktop.bean.param.PositionFuturesCloseBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.yecq.goleek.desktop.service.core.Util;
import java.awt.Dimension;
import java.awt.Font;
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
class PositionFuturesCloseDialog extends JDialog {

    private Map obj;
    private PositionFuturesInfoBean pos;

    PositionFuturesCloseDialog(PositionFuturesInfoBean pos) {
        super(MainFrame.getInstance());
        this.obj = new HashMap();
        this.obj.put("root", this);
        this.pos = pos;

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
        this.setTitle("期货平仓 - " + this.pos.getContract());
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
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel code = new JLabel();
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 21);
        this.obj.put("note", code);
        code.setFont(font);
        code.setHorizontalAlignment(JLabel.CENTER);
        code.setPreferredSize(new Dimension(100, 30));
        p.add(code, c);

        c.gridy++;
        c.weightx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 0, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        p.add(new JLabel("数量"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("平仓价"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("日期"), c);

        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(65, 22));
        this.obj.put("confirm", confirm);
        p.add(confirm, c);

        c.gridy = 1;
        c.gridx++;
        c.gridwidth = 1;
        c.weightx = 1;
        c.insets = new Insets(5, 15, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        JTextField lot = new JTextField();
        lot.setPreferredSize(new Dimension(60, 22));
        this.obj.put("lot", lot);
        p.add(lot, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JTextField price = new JTextField();
        price.setPreferredSize(new Dimension(90, 22));
        this.obj.put("price", price);
        p.add(price, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        DateChooser date = new DateChooser();
        date.setPreferredSize(new Dimension(90, 22));
        this.obj.put("date", date);
        p.add(date, c);

        return p;
    }

    private void initData() {
        JLabel note = (JLabel) obj.get("note");
        note.setText(pos.getContract());
        note.setForeground(pos.getDirect().equals("多") ? Constants.BUY_COLOR : Constants.SELL_COLOR);

        JTextField lot = (JTextField) obj.get("lot");
        lot.setText(pos.getLot() + "");
    }

    private void initListener() {
        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String t = ((JTextField) obj.get("lot")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("手数为空");
                    return;
                }
                Integer lot = Util.getInt(t);
                if (lot == null || lot <= 0 || lot > pos.getLot()) {
                    Vutil.showErrorMsg("错误的手数");
                    return;
                }

                t = ((JTextField) obj.get("price")).getText().trim();
                if (t.equals("")) {
                    Vutil.showErrorMsg("平仓价为空");
                    return;
                }
                Double price = Util.getDouble(t);
                if (price == null || price <= 0) {
                    Vutil.showErrorMsg("平仓价为空");
                    return;
                }

                t = ((DateChooser) obj.get("date")).getDateString();
                if (t.equals("")) {
                    Vutil.showErrorMsg("日期为空");
                    return;
                }
                String date = t.trim();

                if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否平仓？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    PositionFuturesCloseBean bean = new PositionFuturesCloseBean(lot, price, date);
                    Sret sr = Root.getInstance().getBean(PositionFuturesAgent.class).close(pos.getId(), bean);
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
