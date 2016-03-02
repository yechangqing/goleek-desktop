package org.yecq.goleek.desktop.view;

/**
 * 版本信息
 *
 * @author yecq
 */
public final class Version {

    public static String getVersion() {
        return "v1.1";
    }

    public static String[] getFuncitonList() {
        return new String[]{
            "使用baseframework中的监听通知方式",
            "修改了版本显示的方式",
            "增加了期货和股票品种全部选和全部取消的功能",
            "改新浪云服务器地址为applinzi.com",
            "根据iwork平板修改一下一些组件的尺寸"
        };
    }
}
