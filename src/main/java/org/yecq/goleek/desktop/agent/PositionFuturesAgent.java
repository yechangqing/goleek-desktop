package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.aspect.Notify;
import org.yecq.goleek.desktop.bean.param.PositionFuturesActionsBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesDeleteBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesEditBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.yecq.goleek.desktop.service.Sret;
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
        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
        Sret sr = getSret(json, PositionFuturesInfoBean.class);
        return sr;
    }

    @Notify({"position_futures"})
    public Sret editQuit(PositionFuturesEditBean bean) {
        String json = HttpUtil.post(getUrlString() + "edit_quit.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret open(PositionFuturesOpenBean bean) {
        String json = HttpUtil.post(getUrlString() + "open.go", bean);
        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret close(PositionFuturesCloseBean bean) {
        String json = HttpUtil.post(getUrlString() + "close.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret delete(PositionFuturesDeleteBean bean) {
        String json = HttpUtil.post(getUrlString() + "delete.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getActions(PositionFuturesActionsBean bean) {
        String json = HttpUtil.post(getUrlString() + "get_actions.go", bean);
        Sret sr = getSret(json, String.class);
        return sr;
    }

}
