package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.bean.result.TradeSettingInfoBean;
import org.yecq.goleek.desktop.cache.AccountFuturesCache;
import org.yecq.goleek.desktop.cache.AccountStockCache;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.TradeSettingCache;
import org.yecq.goleek.desktop.service.core.Root;
import org.yecq.goleek.desktop.service.core.Util;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 顶部公共信息显示区
 *
 * @author yecq
 */
class CommonPanel extends JPanel {

    private Map obj;
    private SettingObject setting;
    private MoneyObject money;

    CommonPanel() {
        this.obj = new HashMap();
        this.obj.put("root", this);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 40, 5, 5);
        this.add(getSettingPanel(), c);

        c.gridx++;
        c.weightx = 1;
        this.add(getFillPanel(), c);

        c.gridx++;
        c.weightx = 0;
        c.insets = new Insets(5, 5, 5, 0);
        c.anchor = GridBagConstraints.NORTHEAST;
        this.add(getAllAccountPanel(), c);

        c.gridx++;
        c.insets = new Insets(5, 30, 5, 0);
        this.add(getFuturesAccountPanel(), c);

        c.gridx++;
        c.insets = new Insets(5, 30, 5, 40);
        this.add(getStockAccountPanel(), c);
    }

    private JPanel getSettingPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 13, 0, 0);
        JLabel t = new JLabel("交易设置");
        p.add(t, c);

        c.gridy++;
        c.insets = new Insets(2, 0, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = 1;
        p.add(new JLabel("开仓比例："), c);

        c.gridy++;
        c.insets = new Insets(2, 0, 0, 0);
        p.add(new JLabel("止损比例："), c);;

        c.gridy = 0;
        c.gridy++;
        c.gridx++;
        c.weightx = 1;
        c.insets = new Insets(2, 5, 0, 0);
        JLabel op = new JLabel();
        op.setPreferredSize(new Dimension(90, 22));
        this.obj.put("open_percent", op);
        p.add(op, c);

        c.gridy++;
        c.insets = new Insets(2, 5, 0, 0);
        JLabel lp = new JLabel();
        lp.setPreferredSize(new Dimension(90, 22));
        this.obj.put("loss_position", lp);
        p.add(lp, c);
        return p;
    }

    private JPanel getFillPanel() {
        JPanel p = new JPanel();
        return p;
    }

    private JPanel getAllAccountPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        JLabel t = new JLabel("账户总览");
        p.add(t, c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        JLabel money = new JLabel();
        money.setHorizontalAlignment(JLabel.CENTER);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        money.setFont(font);
        this.obj.put("money_all", money);
        p.add(money, c);
        return p;
    }

    private JPanel getFuturesAccountPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        JLabel t = new JLabel("期货账户");
        p.add(t, c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        JLabel money = new JLabel();
        money.setHorizontalAlignment(JLabel.CENTER);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        money.setFont(font);
        this.obj.put("money_futures", money);
        p.add(money, c);
        return p;
    }

    private JPanel getStockAccountPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        JLabel t = new JLabel("股票账户");
        p.add(t, c);

        c.gridy++;
        c.insets = new Insets(5, 0, 0, 0);
        JLabel money = new JLabel();
        money.setHorizontalAlignment(JLabel.CENTER);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        money.setFont(font);
        this.obj.put("money_stock", money);
        p.add(money, c);
        return p;
    }

    private void initData() {
        this.setting = new SettingObject();
        this.money = new MoneyObject();
    }

    private void initListener() {

    }

    private class SettingObject implements CacheListener {

        private double open_percent;
        private double loss_percent;

        SettingObject() {
            Root.getInstance().getBean(TradeSettingCache.class).addCacheListener(this);
            flush();
        }

        @Override
        public void flush() {
            TradeSettingInfoBean bean = Root.getInstance().getBean(TradeSettingCache.class).getSetting();
            this.open_percent = bean.getOpen_percent();
            this.loss_percent = bean.getLoss_percent();
            // 刷新界面
            ((JLabel) obj.get("open_percent")).setText(Util.getPercentString(this.open_percent, 0));
            ((JLabel) obj.get("loss_position")).setText(Util.getPercentString(this.loss_percent, 1));
        }

    }

    private class MoneyObject implements CacheListener {

        private double futures;
        private double stock;

        MoneyObject() {
            Root.getInstance().getBean(AccountFuturesCache.class).addCacheListener(this);
            Root.getInstance().getBean(AccountStockCache.class).addCacheListener(this);
            flush();
        }

        @Override
        public void flush() {
            this.futures = Root.getInstance().getBean(AccountFuturesCache.class).getMoney();
            this.stock = Root.getInstance().getBean(AccountStockCache.class).getMoney();
            // 刷新界面
            ((JLabel) obj.get("money_all")).setText(Util.getFormattedNumber(this.futures + this.stock, 0) + "");
            ((JLabel) obj.get("money_futures")).setText(Util.getFormattedNumber(this.futures, 0) + "");
            ((JLabel) obj.get("money_stock")).setText(Util.getFormattedNumber(this.stock, 0) + "");
        }

    }
}
