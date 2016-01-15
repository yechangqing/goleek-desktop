package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.view.surface.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * etf信息显示区
 *
 * @author yecq
 */
class EtfPanel extends JPanel {

    private Map obj;

    EtfPanel() {
        this.obj = new HashMap();
        this.obj.put("root", this);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.setPreferredSize(new Dimension(Constants.CENTER_PANEL_WIDTH, Constants.CENTER_PANEL_HEIGHT));
    }

    private void initData() {

    }

    private void initListener() {

    }
}
