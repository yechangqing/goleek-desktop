package org.yecq.goleek.desktop.view;

import com.jhhc.baseframework.client.Root;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.agent.StockAgent;
import org.yecq.goleek.desktop.bean.param.StockInterestBean;
import org.yecq.goleek.desktop.bean.param.StockModifyBean;
import org.yecq.goleek.desktop.bean.param.StockUninterestBean;
import org.yecq.goleek.desktop.bean.result.StockInfoBean;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.StockCache;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yecq
 */
class StockTableModel extends AbstractTableModel implements CacheListener {

    private final String[] header = {"#", "代号", "名称", "交易所", "关注"};
    private List<StockInfoBean> data;

    StockTableModel() {
        Root.getInstance().getBean(StockCache.class).addCacheListener(this);
        flush();
    }

    private List<StockInfoBean> getData() {
        return Root.getInstance().getBean(StockCache.class).getAll();
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
        return columnIndex == 1 || columnIndex == 2 || columnIndex == 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StockInfoBean bean = this.data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return bean.getCode();
            case 2:
                return bean.getName();
            case 3:
                return bean.getExchange();
            case 4:
                return bean.getInterest();
            default:
                throw new IllegalArgumentException("表格下标越界");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StockInfoBean bean = getRowBean(rowIndex);
        StockModifyBean modi = new StockModifyBean(bean.getId());
        Sret sr = null;
        StockAgent agent = Root.getInstance().getBean(StockAgent.class);
        switch (columnIndex) {
            case 1:
                String code = (aValue + "").trim();
                String exchange = Root.getInstance().getBean(StockCache.class).getExchangeNameByCode(code);
                if (exchange == null) {
                    Vutil.showErrorMsg("错误的股票代号");
                    return;
                }
                modi.setCode(code);
                modi.setExchange(exchange);
                sr = agent.modify(modi);
                if (!sr.isOk()) {
                    Vutil.showErrorMsg(sr.getMessage());
                }
                break;
            case 2:
                String name = aValue + "";
                modi.setName(name);
                sr = agent.modify(modi);
                if (!sr.isOk()) {
                    Vutil.showErrorMsg(sr.getMessage());
                }
                break;
            case 4:
                if (aValue.equals("y")) {
                    sr = agent.interest(new StockInterestBean(bean.getId()));
                } else if (aValue.equals("n")) {
                    sr = agent.unInterest(new StockUninterestBean(bean.getId()));
                } else {
                    throw new IllegalArgumentException("错误的interest");
                }
                if (!sr.isOk()) {
                    Vutil.showErrorMsg(sr.getMessage());
                }
                break;
            default:
                throw new IllegalArgumentException("错误的表格下标");
        }
    }

    StockInfoBean getRowBean(int row) {
        return this.data.get(row);
    }

    @Override
    public void flush() {
        this.data = Root.getInstance().getBean(StockCache.class).getAll();
        this.fireTableDataChanged();
    }
}
