package org.yecq.goleek.desktop.view.surface;

/**
 *
 * @author yecq
 */
public class Insets extends java.awt.Insets {

    private static final int base = 2;

    public Insets(int top, int left, int bottom, int right) {
        super(top * base, left * base, bottom * base, right * base);
    }
}
