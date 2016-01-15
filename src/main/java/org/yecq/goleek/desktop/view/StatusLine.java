package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.view.surface.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author yecq
 */
class StatusLine extends JPanel {

    StatusLine() {
        initView();
    }

    private void initView() {
        this.setPreferredSize(new Dimension(100, 25));
//        this.setBorder(BorderFactory.createEtchedBorder());
    }

}
