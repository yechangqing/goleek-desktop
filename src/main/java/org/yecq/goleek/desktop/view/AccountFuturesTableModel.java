package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.AccountAgent;
import org.yecq.goleek.desktop.bean.param.AccountModifyBean;
import org.yecq.goleek.desktop.bean.param.AccountUnuseBean;
import org.yecq.goleek.desktop.bean.param.AccountUseBean;
import org.yecq.goleek.desktop.bean.result.AccountFuturesInfoBean;
import org.yecq.goleek.desktop.cache.AccountFuturesCache;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yecq
 */
class AccountFuturesTableModel extends AbstractTableModel implements CacheListener {

    private final String[] header = {"#", "账号", "公司", "权益", "使用"};
    private List<AccountFuturesInfoBean> data;

    AccountFuturesTableModel() {
        Root.getInstance().getBean(AccountFuturesCache.class).addCacheListener(this);
        flush();
    }

    private List<AccountFuturesInfoBean> getData() {
        return Root.getInstance().getBean(AccountFuturesCache.class).getAll();
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.header[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 ? false : true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AccountFuturesInfoBean bean = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return bean.getCode();
            case 2:
                return bean.getCompany();
            case 3:
                return bean.getMoney();
            case 4:
                return bean.getUsed();
            default:
                throw new IllegalArgumentException("表格下标越界");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        AccountAgent agent = Root.getInstance().getBean(AccountAgent.class);
        Sret sr = null;
        AccountFuturesInfoBean bean = getRowBean(rowIndex);
        AccountModifyBean modi = new AccountModifyBean(bean.getId());
        switch (columnIndex) {
            case 1:
                modi.setCode(aValue + "");
                sr = agent.modify(modi);
                break;
            case 2:
                modi.setCompany(aValue + "");
                sr = agent.modify(modi);
                break;
            case 3:
                modi.setMoney(Double.parseDouble(aValue + ""));
                sr = agent.modify(modi);
                break;
            case 4:
                if (aValue.equals("y")) {
                    sr = agent.use(new AccountUseBean(bean.getId()));
                } else if (aValue.equals("n")) {
                    sr = agent.unUse(new AccountUnuseBean(bean.getId()));
                } else {
                    throw new IllegalArgumentException("使用（取消使用）账户参数出错");
                }
                break;
            default:
                throw new IllegalArgumentException("表格下标越界");
        }
        if (!sr.isOk()) {
            Vutil.showErrorMsg(sr.getMessage());
        }
    }

    AccountFuturesInfoBean getRowBean(int row) {
        return this.data.get(row);
    }

    @Override
    public void flush() {
        this.data = Root.getInstance().getBean(AccountFuturesCache.class).getAll();
        this.fireTableDataChanged();
    }
}
