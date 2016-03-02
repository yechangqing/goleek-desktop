package org.yecq.goleek.desktop.view;

/**
 * 版本信息
 *
 * @author yecq
 */
public final class Version {

    public static String getVersion() {
        return "v2.0";
    }

    public static String[] getFuncitonList() {
        return new String[]{
            "版本号改为2.0，跟服务器2.0版本的RESTful api保持一致",
            "http方法改为使用RestUtil的REST api",
            "修改某些组件尺寸已适合平板上的尺寸"
        };
    }
}
