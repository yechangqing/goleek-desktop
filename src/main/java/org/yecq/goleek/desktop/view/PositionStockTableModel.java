package org.yecq.goleek.desktop.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.yecq.goleek.desktop.bean.result.PositionStockInfoBean;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.PositionStockCache;
import org.yecq.goleek.desktop.service.core.Root;

/**
 *
 * @author yecq
 */
class PositionStockTableModel extends AbstractTableModel implements CacheListener {

    private final String[] header = {"#", "代号", "名称", "手数", "买入价", "退出价", "账户"};
    private List<PositionStockInfoBean> data;

    PositionStockTableModel() {
        Root.getInstance().getBean(PositionStockCache.class).addCacheListener(this);
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
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PositionStockInfoBean bean = this.data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return bean.getCode();
            case 2:
                return bean.getName();
            case 3:
                return bean.getLot();
            case 4:
                return bean.getOpen_price();
            case 5:
                return bean.getAction() + " " + bean.getQuit_price();
            case 6:
                return bean.getAccount();
            default:
                throw new IllegalArgumentException("表格下标错误");
        }
    }

    PositionStockInfoBean getRowBean(int row) {
        return this.data.get(row);
    }

    @Override
    public void flush() {
        this.data = Root.getInstance().getBean(PositionStockCache.class).getListAll();
        this.fireTableDataChanged();
    }
}
