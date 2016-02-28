package org.yecq.goleek.desktop.view;

import com.jhhc.baseframework.client.Root;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.yecq.goleek.desktop.cache.AccountStockCache;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.StockCache;
import org.yecq.goleek.desktop.cache.TradeSettingCache;
import org.yecq.goleek.desktop.service.core.Compute;
import org.yecq.goleek.desktop.service.core.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author yecq
 */
class StockComputePanelModel implements CacheListener {

    private Map obj;
    private Map view;
    private ObjectStock stock;      // stock
    private ObjectTradeSetting setting;     // setting
    private ObjectAccountStock account;     // account
    private ObjectStockResultStd result_std; // result_std;
    private ObjectStockInput input;         // input
    private ObjectStockInputDefined input_defined;  // input_defined
    private ObjectStockResultDefined result_defined;       // result_input;
    private ChangeService cs;

    Map<String, Map<String, String>> remember;

    StockComputePanelModel(Map view) {
        this.obj = new HashMap();
        this.view = view;
        this.obj.put("root", this);

        this.stock = new ObjectStock();
        this.setting = new ObjectTradeSetting();
        this.account = new ObjectAccountStock();
        this.result_std = new ObjectStockResultStd();
        this.input = new ObjectStockInput();
        this.input_defined = new ObjectStockInputDefined();
        this.result_defined = new ObjectStockResultDefined();
        this.cs = new ChangeService() {

            @Override
            public void doChangeAction() {
            }
        };
        this.remember = new HashMap();

        initComponent();
        initData();

        // 注册
        Root.getInstance().getBean(AccountStockCache.class).addCacheListener(this);
        Root.getInstance().getBean(StockCache.class).addCacheListener(this);
        Root.getInstance().getBean(TradeSettingCache.class).addCacheListener(this);

        firstFire();
    }

    private void initComponent() {
        JLabel la = (JLabel) view.get("stock_note");
        ChangeBase la1 = new ChangeBase("view_stock_note", new String[]{"stock"}, la, this.cs) {

            @Override
            public void doChangeAction() {
                JLabel la = (JLabel) getUserObject();
                la.setText(stock.isValid() ? stock.getName() + "(" + stock.getCode() + ")" : "未选择股票");
            }
        };

        ChangeBase rst_std = new ChangeBase("result_std", new String[]{/*"stock",*/"setting", "input", "account"}, this.result_std, this.cs) {

            @Override
            public void doChangeAction() {
                ObjectStockResultStd std = (ObjectStockResultStd) getUserObject();
                if (/*!stock.isValid() ||*/!setting.isValid() || !input.isValid() || !account.isValid()) {
                    std.setInvalid();
                    cs.fireChanged(new String[]{"result_std"});
                    return;
                }
                if (input.getLoss_price() >= input.getOpen_price()) {
                    Vutil.showErrorMsg("止损价应小于开仓价");
                    std.setInvalid();
                    input.setInvalid();
                    cs.fireChanged(new String[]{"input", "result_std"});
                } else {
                    double open_price = input.getOpen_price();
                    double margin = stock.getMargin();
                    int unit = stock.getUnit();
                    double position_percent = setting.getOpen_percent();
                    double money = account.getMoney();
                    double loss_price = input.getLoss_price();
                    double loss_percent = setting.getLoss_percent();
                    try {
                        int lot = Compute.getOpenLotByPercentOfPositionAndLoss(open_price, margin, unit, position_percent, money, loss_price, loss_percent);
                        std.setLot(lot);
                    } catch (IllegalArgumentException e) {
                        std.setInvalid();
                        Vutil.showErrorMsg(e.getMessage());
                    }
                    cs.fireChanged(new String[]{"result_std"});
                }
            }
        };

        JLabel lb = (JLabel) view.get("std_lot");
        ChangeBase lb1 = new ChangeBase("view_std_lot", new String[]{"result_std"}, lb, this.cs) {

            @Override
            public void doChangeAction() {
                JLabel la = (JLabel) getUserObject();
                la.setText(result_std.isValid() ? result_std.getLot() + "" : "");
            }
        };

        ChangeBase rst_def = new ChangeBase("result_defined", new String[]{/*"stock",*/"setting", "input", "input_defined", "account"}, this.result_defined, this.cs) {

            @Override
            public void doChangeAction() {
                ObjectStockResultDefined rst = (ObjectStockResultDefined) getUserObject();
                if (/*!stock.isValid() || */!setting.isValid() || !input.isValid() || !input_defined.isValid() || !account.isValid()) {
                    rst.setInvalid();
                    cs.fireChanged(new String[]{"result_defined"});
                    return;
                }
                int lot = input_defined.getLot();
                double open_price = input.getOpen_price();
                double margin = stock.getMargin();
                int unit = stock.getUnit();
                double money = account.getMoney();
                try {
                    double pp = Compute.getPositionMoneyPercent(lot, open_price, margin, unit, money);
                    rst.setPosition_percent(pp);
                } catch (IllegalArgumentException e) {
                    rst.setInvalid();
                    Vutil.showErrorMsg(e.getMessage());
                }
                double close_price = input.getLoss_price();
                try {
                    double lp = Math.abs(Compute.getLossPercentOfAccount4Stock(lot, open_price, close_price, unit, money));
                    rst.setLoss_percent(lp);
                } catch (IllegalArgumentException e) {
                    rst.setInvalid();
                    Vutil.showErrorMsg(e.getMessage());
                }
                cs.fireChanged(new String[]{"result_defined"});
            }
        };

        JLabel lc = (JLabel) view.get("position_percent");
        ChangeBase lc1 = new ChangeBase("position_percent", new String[]{"result_defined"}, lc, this.cs) {

            @Override
            public void doChangeAction() {
                JLabel la = (JLabel) getUserObject();
                if (!result_defined.isValid()) {
                    la.setText("");
                } else {
                    double pp = result_defined.getPosition_percent();
                    la.setText("仓：" + Util.getPercentString(pp, 0));
                    la.setForeground(pp >= setting.getOpen_percent() ? Constants.WARN_COLOR : null);
                }
            }
        };

        JLabel ld = (JLabel) view.get("loss_percent");
        ChangeBase ld1 = new ChangeBase("loss_percent", new String[]{"result_defined"}, ld, this.cs) {

            @Override
            public void doChangeAction() {
                JLabel la = (JLabel) getUserObject();
                if (!result_defined.isValid()) {
                    la.setText("");
                } else {
                    double lp = result_defined.getLoss_percent();
                    la.setText("止：" + Util.getPercentString(lp, 1));
                    la.setForeground(lp >= setting.getLoss_percent() ? Constants.WARN_COLOR : null);
                }
            }
        };

        ChangeBase rem = new ChangeBase("rem", new String[]{"result_std"}, this.remember, this.cs) {

            @Override
            public void doChangeAction() {
                if (stock.isValid() && input.isValid() && result_std.isValid()) {
                    String con = ((StockInfoBean) ((JComboBox) view.get("stock")).getSelectedItem()).getCode();
                    String open_price = ((JTextField) view.get("open_price")).getText();
                    String loss_price = ((JTextField) view.get("loss_price")).getText();
                    Map<String, String> map = new HashMap();
                    map.put("open_price", open_price);
                    map.put("loss_price", loss_price);
                    remember.put(con, map);
                }
            }
        };
    }

    private void initData() {

    }

    private void firstFire() {
        fireStockList();
        fireStockNotify();
        fireSettingNotify();
        fireAccountNotify();
    }

    private void fireStockList() {
        StockCache sc = Root.getInstance().getBean(StockCache.class);
        List<StockInfoBean> list = sc.getInterest();
        StockInfoBean[] names = new StockInfoBean[list.size()];
        list.toArray(names);
        ComboBoxModel model = new DefaultComboBoxModel(names);
        JComboBox st = (JComboBox) view.get("stock");
        Object o = st.getSelectedItem();
        st.setModel(model);
        if (names.length == 0) {
            stock.setInvalid();
        } else if (o != null) {
            st.setSelectedItem(o);
        }
        fireStockNotify();
    }

    private void fireSettingNotify() {
        TradeSettingCache set = Root.getInstance().getBean(TradeSettingCache.class);
        this.setting.setOpen_percent(set.getOpenPercent());
        this.setting.setLoss_percent(set.getLossPercent());
        this.cs.fireChanged(new String[]{"setting"});
    }

    private void fireAccountNotify() {
        AccountStockCache ca = Root.getInstance().getBean(AccountStockCache.class);
        this.account.setMoney(ca.getMoney());
        this.cs.fireChanged(new String[]{"account"});
    }

    void fireStockNotify() {
        JComboBox jcb = (JComboBox) view.get("stock");
        Object o = jcb.getSelectedItem();
        if (o == null) {
            this.stock.setInvalid();
        } else {
            StockInfoBean bean = (StockInfoBean) o;
            this.stock.setCode(bean.getCode());
            this.stock.setId(bean.getId());
            this.stock.setName(bean.getName());
        }
        // 通知刷新
        this.cs.fireChanged(new String[]{"stock"});
    }

    void fireInputNotify() {
        String op = ((JTextField) view.get("open_price")).getText().trim();
        if (op.equals("")) {
            this.input.setOpen_price(null);
        } else {
            Double n = Util.getDouble(op);
            if (n == null || n <= 0) {
                this.input.setOpen_price(null);
                Vutil.showErrorMsg("错误的买入价");
            } else {
                this.input.setOpen_price(n);
            }
        }

        String lp = ((JTextField) view.get("loss_price")).getText().trim();
        if (lp.equals("")) {
            this.input.setLoss_price(null);
        } else {
            Double n = Util.getDouble(lp);
            if (n == null || n <= 0) {
                this.input.setLoss_price(null);
                Vutil.showErrorMsg("错误的止损价");
            } else {
                this.input.setLoss_price(n);
            }
        }

        this.cs.fireChanged(new String[]{"input"});
    }

    void fireInputDefinedNotify() {
        String lo = ((JTextField) view.get("lot")).getText().trim();
        if (lo.equals("")) {
            this.input_defined.setLot(null);
        } else {
            Integer n = Util.getInt(lo);
            if (n == null || n <= 0) {
                this.input_defined.setInvalid();
                Vutil.showErrorMsg("错误的手数");
            } else {
                this.input_defined.setLot(n);
            }
        }
        this.cs.fireChanged(new String[]{"input_defined"});
    }

    @Override
    public void flush() {
        fireAccountNotify();
        fireStockList();
        fireSettingNotify();
    }

}
