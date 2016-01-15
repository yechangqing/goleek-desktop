package org.yecq.goleek.desktop.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.yecq.goleek.desktop.agent.AccountAgent;
import org.yecq.goleek.desktop.bean.param.AccountModifyBean;
import org.yecq.goleek.desktop.bean.param.AccountUnuseBean;
import org.yecq.goleek.desktop.bean.param.AccountUseBean;
import org.yecq.goleek.desktop.bean.result.AccountStockInfoBean;
import org.yecq.goleek.desktop.cache.AccountStockCache;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.service.Sret;
import org.yecq.goleek.desktop.service.core.Root;

/**
 *
 * @author yecq
 */
class AccountStockTableModel extends AbstractTableModel implements CacheListener {

    private final String[] header = {"#", "账号", "公司", "资金", "使用"};
    private List<AccountStockInfoBean> data;

    AccountStockTableModel() {
        Root.getInstance().getBean(AccountStockCache.class).addCacheListener(this);
        flush();
    }

    private List<AccountStockInfoBean> getData() {
        return Root.getInstance().getBean(AccountStockCache.class).getAll();
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
        return columnIndex != 0 ? true : false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AccountStockInfoBean bean = this.data.get(rowIndex);
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
        AccountStockInfoBean bean = getRowBean(rowIndex);
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

    AccountStockInfoBean getRowBean(int row) {
        return this.data.get(row);
    }

    @Override
    public void flush() {
        this.data = Root.getInstance().getBean(AccountStockCache.class).getAll();
        this.fireTableDataChanged();
    }
}
