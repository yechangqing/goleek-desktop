package org.yecq.goleek.desktop;

import java.awt.Font;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yecq.goleek.desktop.view.MainFrame;
import org.yecq.goleek.desktop.view.Vutil;

/**
 *
 * @author yecq
 */
public class Main {

    public static void main(String[] args) {
        setUI();
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

    private static void setUI() {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 26);          // 正常字体大小
        Font bold_font = new Font(Font.MONOSPACED, Font.BOLD, 26);      // 正常粗体大小
        Font big_bold_font = new Font(Font.MONOSPACED, Font.BOLD, 40);  // 大号粗体

//        UIManager.put("InternalFrame.titleFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.messageFont", font);
//        UIManager.put("Tree.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("Table.gridHeight", 40);
        UIManager.put("TableHeader.font", bold_font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("RadioButton.font", font);
//        UIManager.put("CheckBox.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("ScrollBar.width", 30);
//        UIManager.put("Tree.rowHeight", 50);
//        UIManager.put("TextArea.font", font);
    }
}
