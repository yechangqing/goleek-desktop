package org.yecq.goleek.desktop;

import org.yecq.goleek.desktop.view.MainFrame;
import org.yecq.goleek.desktop.view.Vutil;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yecq
 */
public class Main {

    public static void main(String[] args) {
        try {
            new ClassPathXmlApplicationContext("applicationContext.xml");
        } catch (Throwable e) {
            Vutil.showErrorMsg(e.getCause().getMessage());
            System.exit(-1);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainFrame.getInstance().setVisible(true);
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Vutil.showErrorMsg("不支持该视界风格");
        }
    }
}
