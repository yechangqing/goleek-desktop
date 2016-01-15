package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.TradeSettingAgent;
import org.yecq.goleek.desktop.bean.param.TradeSettingSaveBean;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.TradeSettingCache;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;
import org.yecq.goleek.desktop.service.core.Util;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

/**
 *
 * @author yecq
 */
class TradeSettingDialog extends JDialog implements CacheListener {

    private Map obj;

    TradeSettingDialog() {
        super(MainFrame.getInstance());
        this.obj = new HashMap();
        this.obj.put("root", this);
        Root.getInstance().getBean(TradeSettingCache.class).addCacheListener(this);

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
        this.setTitle("交易参数设置");
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
        p.add(new JLabel("开仓比例"), c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        p.add(new JLabel("止损比例"), c);

        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        JPanel tmp = new JPanel();
        FlowLayout fl = new FlowLayout();
        fl.setHgap(14);
        tmp.setLayout(fl);
        JButton confirm = new JButton("确定");
        confirm.setPreferredSize(new Dimension(65, 22));
        this.obj.put("confirm", confirm);
        JButton save = new JButton("保存");
        save.setPreferredSize(new Dimension(65, 22));
        this.obj.put("save", save);
        tmp.add(confirm);
        tmp.add(save);
        p.add(tmp, c);

        c.gridy = 0;
        c.gridx++;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = 1;
        c.insets = new Insets(0, 15, 0, 0);
        JTextField op = new JTextField();
        Dimension d = new Dimension(100, 22);
        op.setPreferredSize(d);
        this.obj.put("open_percent", op);
        p.add(op, c);

        c.gridy++;
        c.insets = new Insets(5, 15, 0, 0);
        JTextField lp = new JTextField();
        lp.setPreferredSize(d);
        this.obj.put("loss_percent", lp);
        p.add(lp, c);

        return p;
    }

    private void initData() {
        TradeSettingCache cache = Root.getInstance().getBean(TradeSettingCache.class);
        JTextField op = (JTextField) obj.get("open_percent");
        op.setText(cache.getOpenPercent() + "");

        JTextField lp = (JTextField) obj.get("loss_percent");
        lp.setText(cache.getLossPercent() + "");
    }

    private void initListener() {
        JButton confirm = (JButton) obj.get("confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setSetting();
                Vutil.showPlateMsg("设置成功");
            }
        });

        JButton save = (JButton) obj.get("save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "是否保存该设置？", "？", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    TradeSettingSaveBean bean = setSetting();
                    if (bean != null) {
                        Sret sr = Root.getInstance().getBean(TradeSettingAgent.class).saveDefault(bean);
                        if (sr.isOk()) {
                            Vutil.showPlateMsg(sr.getMessage());
                        } else {
                            Vutil.showErrorMsg(sr.getMessage());
                        }
                    }
                }
            }
        });
    }

    private TradeSettingSaveBean setSetting() {

        String op = ((JTextField) obj.get("open_percent")).getText().trim();
        if (op.equals("")) {
            Vutil.showErrorMsg("开仓比例为空");
            return null;
        }

        Double open_percent = Util.getDouble(op);
        if (open_percent == null || (open_percent >= 1 || open_percent <= 0)) {
            Vutil.showErrorMsg("错误的开仓比例");
            return null;
        }

        op = ((JTextField) obj.get("loss_percent")).getText().trim();
        if (op.equals("")) {
            Vutil.showErrorMsg("止损比例为空");
            return null;
        }

        Double loss_percent = Util.getDouble(op);
        if (loss_percent == null || (loss_percent >= 1 || loss_percent <= 0)) {
            Vutil.showErrorMsg("错误的止损比例");
            return null;
        }
        Root.getInstance().getBean(TradeSettingCache.class).setTemp(open_percent, loss_percent);
        return new TradeSettingSaveBean("1", open_percent, loss_percent);
    }

    @Override
    public void flush() {
        initData();
    }
}
