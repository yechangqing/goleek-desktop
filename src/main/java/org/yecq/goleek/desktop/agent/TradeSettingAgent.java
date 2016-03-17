package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.TradeSettingSaveBean;
import org.yecq.goleek.desktop.bean.result.TradeSettingInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Notify;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author yecq
 */
@Component
public class TradeSettingAgent extends AgentBase {

    //复写路径
    @Override
    public String getUrlString() {
        return super.getUrlString() + "trade_setting/";
    }

    public Sret getDefault() {
        String json = HttpUtil.post(getUrlString() + "get_default.php", null);
        Sret sr = getSretObject(json, TradeSettingInfoBean.class);
        return sr;
    }

    @Notify({"setting"})
    public Sret saveDefault(String id, TradeSettingSaveBean bean) {
        String json = HttpUtil.post(getUrlString() + "save_default.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }
}
