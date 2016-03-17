package org.yecq.goleek.desktop.view;

/**
 * 版本信息
 *
 * @author yecq
 */
public final class Version {

    public static String getVersion() {
        return "v1.1.php";
    }

    public static String[] getFuncitonList() {
        return new String[]{
            "修改版本号为v1.1.php",
            "配合php做的服务器后端，修改了agent的一些接口"
        };
    }
}
