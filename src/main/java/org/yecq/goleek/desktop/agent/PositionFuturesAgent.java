package org.yecq.goleek.desktop.agent;

import com.jhhc.baseframework.client.listener.Notify;
import com.jhhc.baseframework.client.rest.Sret;
import org.yecq.goleek.desktop.bean.param.PositionFuturesActionsBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesDeleteBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesEditBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class PositionFuturesAgent extends AgentBase {

    //复写路径
    @Override
    public String getUrlString() {
        return super.getUrlString() + "position_futures/";
    }

    public Sret getListAll() {
        Sret sr=rest.get4Object(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
//        Sret sr = getSret(json, PositionFuturesInfoBean.class);
        return sr;
    }

    @Notify({"position_futures"})
    public Sret editQuit(PositionFuturesEditBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "edit_quit.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret open(PositionFuturesOpenBean bean) {
        Sret sr=rest.post(null, null);
//        String json = HttpUtil.post(getUrlString() + "open.go", bean);
//        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret close(PositionFuturesCloseBean bean) {
        Sret sr=rest.put(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "close.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret delete(PositionFuturesDeleteBean bean) {
        Sret sr=rest.delete(null, null);
//        String json = HttpUtil.post(getUrlString() + "delete.go", bean);
//        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getActions(PositionFuturesActionsBean bean) {
        Sret sr=rest.get4Object(null, null, null);
//        String json = HttpUtil.post(getUrlString() + "get_actions.go", bean);
//        Sret sr = getSret(json, String.class);
        return sr;
    }

}
