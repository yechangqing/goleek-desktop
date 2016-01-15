package org.yecq.goleek.desktop.view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author yecq
 */
public final class Vutil {

    public static void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "！", JOptionPane.ERROR_MESSAGE);
    }

    public static void showErrorMsg(String msg, Component comp) {
        JOptionPane.showMessageDialog(comp, msg, "！", JOptionPane.ERROR_MESSAGE);
    }

    public static void showPlateMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "！", JOptionPane.PLAIN_MESSAGE);
    }

    public static void showPlateMsg(String msg, Component comp) {
        JOptionPane.showMessageDialog(comp, msg, "！", JOptionPane.PLAIN_MESSAGE);
    }

}
