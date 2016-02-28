package org.yecq.goleek.desktop.view;

import com.jhhc.baseframework.client.Root;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.agent.FuturesAgent;
import org.yecq.goleek.desktop.bean.param.FuturesInterestBean;
import org.yecq.goleek.desktop.bean.param.FuturesModifyBean;
import org.yecq.goleek.desktop.bean.param.FuturesUninterestBean;
import org.yecq.goleek.desktop.bean.result.FuturesInfoBean;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.FuturesCache;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yecq
 */
class FuturesTableModel extends AbstractTableModel implements CacheListener {

    private final String[] header = {"#", "代号", "名称", "保证金", "交易所", "关注"};
    private List<FuturesInfoBean> data;

    FuturesTableModel() {
        Root.getInstance().getBean(FuturesCache.class).addCacheListener(this);
        flush();
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
        return (columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 5) ? true : false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FuturesInfoBean bean = this.data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return bean.getCode();
            case 2:
                return bean.getName();
            case 3:
                return bean.getMargin();
            case 4:
                return bean.getExchange();
            case 5:
                return bean.getInterest();
            default:
                throw new IllegalArgumentException("表格下标越界");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        FuturesAgent agent = Root.getInstance().getBean(FuturesAgent.class);
        Sret sr = null;
        FuturesInfoBean bean = getRowBean(rowIndex);
        FuturesModifyBean modi = new FuturesModifyBean(bean.getId());
        switch (columnIndex) {
            case 1:
                modi.setCode(aValue + "");
                agent.modify(modi);
                break;
            case 2:
                modi.setName(aValue + "");
                agent.modify(modi);
                break;
            case 3:
                modi.setMargin(Double.parseDouble(aValue + ""));
                agent.modify(modi);
                break;
            case 4:
                modi.setExchange(aValue + "");
                agent.modify(modi);
                break;
            case 5:
                if (aValue.equals("y")) {
                    agent.interest(new FuturesInterestBean(bean.getId()));
                } else if (aValue.equals("n")) {
                    agent.unInterest(new FuturesUninterestBean(bean.getId()));
                } else {
                    throw new IllegalArgumentException("错误的参数");
                }

        }
    }

    FuturesInfoBean getRowBean(int row) {
        return this.data.get(row);
    }

    @Override
    public void flush() {
        this.data = Root.getInstance().getBean(FuturesCache.class).getAll();
        this.fireTableDataChanged();
    }
}
