package org.yecq.goleek.desktop.view;

import org.yecq.goleek.desktop.agent.IpPort;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.yecq.baseframework.plain.core.Root;

/**
 *
 * @author yecq
 */
public class MainFrame extends JFrame {

    private static MainFrame single = null;
    private Map obj;

    public static MainFrame getInstance() {
        if (single == null) {
            single = new MainFrame();
        }
        return single;
    }

    private MainFrame() {
        this.obj = new HashMap();
        this.obj.put("root", this);

        initView();
        initListener();
    }

    private void initView() {
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        CenterPanel cp = new CenterPanel();
        this.obj.put("center_panel", cp);
        CenterPanelSimple cps = new CenterPanelSimple();
        this.obj.put("center_panel_simple", cps);
        StatusLine status = new StatusLine();
        this.obj.put("status_line", status);
        String mode = Root.getInstance().getBean("mode", Mode.class).getMode();
        if (mode.equals("normal")) {
//            this.add(cp, BorderLayout.CENTER);
//            this.add(status, BorderLayout.SOUTH);
            switchNormal();
        } else if (mode.equals("simple")) {
            switchSimple();
        } else {
            Vutil.showErrorMsg("窗口模式设置不正确");
            return;
        }
        this.setJMenuBar(new MenuBar());
//        this.add(new ToolBar(), BorderLayout.NORTH);
        this.setTitle("常规版-" + Root.getInstance().getBean(IpPort.class).getIp());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(d.width - 10, d.height - 40);
        Dimension dd1 = this.getSize();
        int x = d.width - dd1.width;
        int y = d.height - dd1.height;
        this.setLocation(x / 2, y / 3);
    }

    private void adjustSize() {
        CenterPanel cp = (CenterPanel) obj.get("center_panel");
        cp.adjustSize();
    }

    void switchNormal() {
        CenterPanel normal = (CenterPanel) obj.get("center_panel");
        CenterPanelSimple simple = (CenterPanelSimple) obj.get("center_panel_simple");
        StatusLine status = (StatusLine) obj.get("status_line");
        LayoutManager layout = this.getLayout();
        layout.removeLayoutComponent(simple);
        this.remove(simple);
        normal.reload();
        this.add(normal, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);
        this.pack();
//        this.setAlwaysOnTop(false);
    }

    void switchSimple() {
        CenterPanel normal = (CenterPanel) obj.get("center_panel");
        CenterPanelSimple simple = (CenterPanelSimple) obj.get("center_panel_simple");
        StatusLine status = (StatusLine) obj.get("status_line");
        LayoutManager layout = this.getLayout();
        layout.removeLayoutComponent(normal);
        layout.removeLayoutComponent(status);
        this.remove(normal);
        this.remove(status);
        simple.reload();
        this.add(simple, BorderLayout.CENTER);
        this.pack();
//        this.setAlwaysOnTop(true);
    }

    @Override
    public void pack() {
        super.pack();
        adjustSize();
    }

    private void initListener() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                JFrame root = (JFrame) obj.get("root");
                if (JOptionPane.showConfirmDialog(root, "是否退出？", "退出", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
