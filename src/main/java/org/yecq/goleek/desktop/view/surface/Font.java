package org.yecq.goleek.desktop.view.surface;

/**
 *
 * @author yecq
 */
public class Font extends java.awt.Font {

    private static final int base = 2;

    public Font(String name, int style, int size) {
        super(name, style, size * base);
    }

}
