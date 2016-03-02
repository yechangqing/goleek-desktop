package org.yecq.goleek.desktop.agent;

import com.google.gson.Gson;
import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import java.util.HashMap;
import java.util.Map;
import org.yecq.goleek.desktop.bean.param.TradeSettingSaveBean;
import org.yecq.goleek.desktop.bean.result.TradeSettingInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class TradeSettingAgent extends AgentBase {

    //复写路径
//    @Override
//    public String getUrlString() {
//        return super.getUrlString() + "trade_setting/";
//    }
    public Sret getDefault() {
        Sret sr = rest.get4Object(getUrlString() + "/trade_settings/default", TradeSettingInfoBean.class);
//        String json = HttpUtil.post(getUrlString() + "get_default.go", null);
//        Sret sr = getSretSingle(json, TradeSettingInfoBean.class);
        return sr;
    }

    @Notify({"setting"})
    public Sret saveDefault(TradeSettingSaveBean bean) {
        Map map = new HashMap();
        map.put("json", new Gson().toJson(bean));
        Sret sr = rest.put(getUrlString() + "/trade_settings/default", map);
//        String json = HttpUtil.post(getUrlString() + "save_default.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }
}
