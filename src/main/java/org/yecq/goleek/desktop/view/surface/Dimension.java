package org.yecq.goleek.desktop.view.surface;

/**
 *
 * @author yecq
 */
public class Dimension extends java.awt.Dimension {

    private static final int x_base = 2;
    private static final int y_base = 2;

    public Dimension(int width, int height) {
        super(width * x_base, (height - 1) * y_base);
    }

}
