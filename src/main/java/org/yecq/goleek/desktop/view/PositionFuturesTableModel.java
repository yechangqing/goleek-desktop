package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.yecq.goleek.desktop.cache.CacheListener;
import org.yecq.goleek.desktop.cache.PositionFuturesCache;
import org.yecq.goleek.desktop.service.core.Root;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yecq
 */
class PositionFuturesTableModel extends AbstractTableModel implements CacheListener {

    private final String[] header = {"#", "合约", "方向", "手数", "开仓价", "退出价", "账户"};
    private List<PositionFuturesInfoBean> data;

    PositionFuturesTableModel() {
        Root.getInstance().getBean(PositionFuturesCache.class).addCacheListener(this);
        flush();
    }

    private List<PositionFuturesInfoBean> getData() {
        return Root.getInstance().getBean(PositionFuturesCache.class).getListAll();
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
        PositionFuturesInfoBean bean = this.data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return bean.getContract();
            case 2:
                return bean.getDirect();
            case 3:
                return bean.getLot();
            case 4:
                return bean.getOpen_price();
            case 5:
                return bean.getAction() + " " + bean.getQuit_price();
            case 6:
                return bean.getAccount();
            default:
                throw new IllegalArgumentException("表格下标越界");
        }
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        
//    }

    PositionFuturesInfoBean getRowBean(int row) {
        return this.data.get(row);
    }

    @Override
    public void flush() {
        this.data = Root.getInstance().getBean(PositionFuturesCache.class).getListAll();
        this.fireTableDataChanged();
    }

}
