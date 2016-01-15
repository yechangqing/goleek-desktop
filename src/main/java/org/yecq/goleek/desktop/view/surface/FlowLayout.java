package org.yecq.goleek.desktop.view.surface;

/**
 *
 * @author yecq
 */
public class FlowLayout extends java.awt.FlowLayout {

    private static final int h_base = 2;
    private static final int v_base = 2;

    @Override
    public void setVgap(int vgap) {
        super.setVgap(vgap * v_base);
    }

    @Override
    public void setHgap(int hgap) {
        super.setHgap(hgap * h_base);
    }

}
