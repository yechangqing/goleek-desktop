package org.yecq.goleek.desktop.agent;

/**
 * 服务器的ip和端口设置，具体数值配置在spring中
 *
 * @author yecq
 */
public class IpPort {

    private String ip;
    private String port;
    private String app;

    public IpPort() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
