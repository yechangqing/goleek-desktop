package org.yecq.goleek.desktop.view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JTabbedPane;

/**
 * 精简界面，只包含计算面板
 *
 * @author yecq
 */
class CenterPanelSimple extends JTabbedPane {

    private Map obj;

    CenterPanelSimple() {
        this.obj = new HashMap();
        this.obj.put("root", this);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        FuturesComputePanel fcp = FuturesComputePanel.getInstance();
        this.obj.put("futures", fcp);
        this.add("期货", fcp);
        StockComputePanel scp = StockComputePanel.getInstance();
        this.obj.put("stock", scp);
        this.add("股票", scp);
    }

    private void initData() {

    }

    private void initListener() {

    }

    void reload() {
        this.removeAll();
        this.obj.clear();
        this.obj.put("root", this);
        initView();
    }

}
