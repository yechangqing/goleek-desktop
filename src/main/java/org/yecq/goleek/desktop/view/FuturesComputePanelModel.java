package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.yecq.goleek.desktop.cache.AccountFuturesCache;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.FuturesCache;
import org.yecq.goleek.desktop.cache.TradeSettingCache;
import org.yecq.goleek.desktop.service.core.Compute;
import org.yecq.goleek.desktop.service.core.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.yecq.baseframework.plain.core.Root;

/**
 *
 * @author yecq
 */
class FuturesComputePanelModel implements CacheListener {

    private Map obj;
    private Map view;
    private ObjectFuturesContract contract;   // contract
    private ObjectTradeSetting setting;   //setting
    private ObjectFuturesInput input;   // input
    private ObjectFuturesInputDefined input_defined;    // input_defined;
    private ObjectAccountFutures account;       // account
    private ObjectFuturesResultStd result_std;  // result_std;
    private ObjectFuturesResultDefined result_defined;  // result_defined
    private ChangeService cs;

    Map<String, Map<String, String>> remember;

    FuturesComputePanelModel(Map view) {
        this.obj = new HashMap();
        this.obj.put("root", this);
        this.view = view;

        this.contract = new ObjectFuturesContract();
        this.setting = new ObjectTradeSetting();
        this.input = new ObjectFuturesInput();
        this.input_defined = new ObjectFuturesInputDefined();
        this.account = new ObjectAccountFutures();
        this.result_std = new ObjectFuturesResultStd();
        this.result_defined = new ObjectFuturesResultDefined();
        this.cs = new ChangeService() {
            @Override
            public void doChangeAction() {
            }
        };
        this.remember = new HashMap();

        initComponent();
        initData();
        // 注册
        Root.getInstance().getBean(AccountFuturesCache.class).addCacheListener(this);
        Root.getInstance().getBean(FuturesCache.class).addCacheListener(this);
        Root.getInstance().getBean(TradeSettingCache.class).addCacheListener(this);

        firstFire();
    }

    private void initComponent() {
        JLabel note = (JLabel) view.get("contract_note");
        ChangeBase note1 = new ChangeBase("view_contract_note", new String[]{"contract", "direct"}, note, this.cs) {

            @Override
            public void doChangeAction() {
                JLabel la = (JLabel) getUserObject();
                if (contract.isValid()) {
                    String direct = input.getDirect();
                    if (direct == null) {
                        la.setForeground(null);
                    } else if (direct.equals("多")) {
                        la.setForeground(Constants.BUY_COLOR);
                    } else if (direct.equals("空")) {
                        la.setForeground(Constants.SELL_COLOR);
                    }
                    la.setText(contract.getContract() + "(" + contract.getName() + ")");
                } else {
                    la.setText("未选择合约");
                    la.setForeground(null);
                }
            }
        };

        ChangeBase rst_std = new ChangeBase("result_std", new String[]{"contract", "setting", "input", "account"}, this.result_std, this.cs) {

            @Override
            public void doChangeAction() {
                ObjectFuturesResultStd std = (ObjectFuturesResultStd) getUserObject();
                if (!contract.isValid() || !setting.isValid() || !input.isValid() || !account.isValid()) {
                    std.setInvalid();
                    cs.fireChanged(new String[]{"result_std"});
                    return;
                } else if (input.getDirect().equals("多") && input.getLoss_price() >= input.getOpen_price()) {
                    Vutil.showErrorMsg("止损价应小于开仓价");
                    input.setInvalid();
                    std.setInvalid();
                    cs.fireChanged(new String[]{"input", "result_std"});
                } else if (input.getDirect().equals("空") && input.getLoss_price() <= input.getOpen_price()) {
                    Vutil.showErrorMsg("止损价应大于开仓价");
                    input.setInvalid();
                    std.setInvalid();
                    cs.fireChanged(new String[]{"input", "result_std"});
                } else {
                    double open_price = input.getOpen_price();
                    double margin = contract.getMargin();
                    int unit = contract.getUnit();
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

        JLabel std_lot1 = (JLabel) view.get("std_lot");
        ChangeBase std_lot11 = new ChangeBase("view_std_lot", new String[]{"result_std"}, std_lot1, this.cs) {

            @Override
            public void doChangeAction() {
                JLabel jtf = (JLabel) getUserObject();
                if (!result_std.isValid()) {
                    jtf.setText("");
                } else {
                    jtf.setText(result_std.getLot() + "");
                }
            }
        };

        ChangeBase rst_def = new ChangeBase("result_defined", new String[]{"contract", "setting", "input", "input_defined", "account"}, this.result_defined, this.cs) {

            @Override
            public void doChangeAction() {
                ObjectFuturesResultDefined rst = (ObjectFuturesResultDefined) getUserObject();
                if (!contract.isValid() || !setting.isValid() || !input.isValid() || !input_defined.isValid() || !account.isValid()) {
                    rst.setInvalid();
                    cs.fireChanged(new String[]{"result_defined"});
                    return;
                }

                int lot = input_defined.getLot();
                double open_price = input.getOpen_price();
                double margin = contract.getMargin();
                int unit = contract.getUnit();
                double money = account.getMoney();
                try {
                    double pp = Compute.getPositionMoneyPercent(lot, open_price, margin, unit, money);
                    rst.setPosition_percent(pp);
                } catch (IllegalArgumentException e) {
                    rst.setInvalid();
                    Vutil.showErrorMsg(e.getMessage());
                }
                String direct = input.getDirect();
                double close_price = input.getLoss_price();
                try {
                    double lp = Math.abs(Compute.getLossPercentOfAccount(direct, lot, open_price, close_price, unit, money));
                    rst.setLoss_percent(lp);
                } catch (IllegalArgumentException e) {
                    rst.setInvalid();
                    Vutil.showErrorMsg(e.getMessage());
                }
                cs.fireChanged(new String[]{"result_defined"});
            }
        };

        JLabel pp = (JLabel) view.get("position_percent");
        ChangeBase pp1 = new ChangeBase("view_position_percent", new String[]{"input", "result_defined"}, pp, this.cs) {

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

        JLabel lp = (JLabel) view.get("loss_percent");
        ChangeBase lp1 = new ChangeBase("view_loss_percent", new String[]{"input", "result_defined"}, lp, this.cs) {

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

        // 增加功能，记住当前已计算的信息
        ChangeBase rem = new ChangeBase("rem", new String[]{"result_std"}, this.remember, this.cs) {

            @Override
            public void doChangeAction() {
                if (contract.isValid() && input.isValid() && result_std.isValid()) {
                    String con = ((FuturesInfoBean) ((JComboBox) view.get("contract")).getSelectedItem()).getCode();
                    String direct = input.getDirect();
                    String open_price = ((JTextField) view.get("open_price")).getText();
                    String loss_price = ((JTextField) view.get("loss_price")).getText();
                    Map<String, String> map = new HashMap();
                    map.put("direct", direct);
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
        fireContractList();
        fireContractNotify();
        fireSettingNotify();
        fireAccountNotify();
    }

    private void fireContractList() {
        JComboBox con = (JComboBox) view.get("contract");
        // 记住原来的选择
        Object o = con.getSelectedItem();
        List<FuturesInfoBean> list = Root.getInstance().getBean(FuturesCache.class).getInterest();
        FuturesInfoBean[] ss = new FuturesInfoBean[list.size()];
        list.toArray(ss);
        ComboBoxModel model = new DefaultComboBoxModel(ss);
        con.setModel(model);
        if (ss.length == 0) {
            contract.setInvalid();
        } else if (o != null) {
            con.setSelectedItem(o);
        }
        fireContractNotify();
    }

    private void fireSettingNotify() {
        TradeSettingCache set = Root.getInstance().getBean(TradeSettingCache.class);
        this.setting.setOpen_percent(set.getOpenPercent());
        this.setting.setLoss_percent(set.getLossPercent());
        this.cs.fireChanged(new String[]{"setting"});
    }

    private void fireAccountNotify() {
        AccountFuturesCache ca = Root.getInstance().getBean(AccountFuturesCache.class);
        this.account.setMoney(ca.getMoney());
        this.cs.fireChanged(new String[]{"account"});
    }

    void fireContractNotify() {
        JComboBox jcb = (JComboBox) view.get("contract");
        Object o = jcb.getSelectedItem();
        if (o == null) {
            this.contract.setInvalid();
        } else {
            FuturesInfoBean bean = (FuturesInfoBean) o;
            this.contract.setContract(bean.getCode());
            this.contract.setName(bean.getName());
            this.contract.setId(bean.getId());
            this.contract.setMargin(bean.getMargin());
            this.contract.setMin(bean.getMin());
            this.contract.setUnit(bean.getUnit());
        }
        // 通知刷新
        this.cs.fireChanged(new String[]{"contract"});
    }

    void fireDirectNotify() {
        this.cs.fireChanged(new String[]{"direct"});
    }

    void fireInputNotify() {
        ButtonGroup bg = (ButtonGroup) view.get("direct");
        ButtonModel direct = bg.getSelection();
        if (direct == null) {
            this.input.setDirect(null);
        } else {
            this.input.setDirect(direct.getActionCommand());
        }

        String op = ((JTextField) view.get("open_price")).getText().trim();
        if (op.equals("")) {
            this.input.setOpen_price(null);
        } else {
            Double d = Util.getDouble(op);
            if (d == null || d <= 0) {
                this.input.setOpen_price(null);
                Vutil.showErrorMsg("错误的开仓价");
            } else {
                this.input.setOpen_price(d);
            }
        }

        String lp = ((JTextField) view.get("loss_price")).getText().trim();
        if (lp.equals("")) {
            this.input.setLoss_price(null);
        } else {
            Double d = Util.getDouble(lp);
            if (d == null || d <= 0) {
                this.input.setLoss_price(null);
                Vutil.showErrorMsg("错误的止损价");
            } else {
                this.input.setLoss_price(d);
            }
        }

        this.cs.fireChanged(new String[]{"input"});

    }

    void fireInputDefinedNotify() {
        String lo = ((JTextField) view.get("lot")).getText().trim();
        if (lo.equals("")) {
            this.input_defined.setLot(null);
        } else {
            Integer i = Util.getInt(lo);
            if (i == null || i <= 0) {
                this.input_defined.setLot(null);
                Vutil.showErrorMsg("错误的手数");
            } else {
                this.input_defined.setLot(i);
            }
        }
        this.cs.fireChanged(new String[]{"input_defined"});
    }

    @Override
    public void flush() {
        fireAccountNotify();
        fireContractList();
        fireSettingNotify();
    }

    void setInputInvalid() {
        this.input.setInvalid();
    }

    void setInput(String direct, Double open_price, Double loss_price) {
        this.input.setDirect(direct);
        this.input.setOpen_price(open_price);
        this.input.setLoss_price(loss_price);
    }
}
