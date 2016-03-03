package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.PositionFuturesActionsBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesDeleteBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesEditBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionFuturesInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Notify;
import org.yecq.baseframework.plain.service.Sret;

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
        String json = HttpUtil.post(getUrlString() + "get_list_all.php", null);
        Sret sr = getSret(json, PositionFuturesInfoBean.class);
        return sr;
    }

    @Notify({"position_futures"})
    public Sret editQuit(PositionFuturesEditBean bean) {
        String json = HttpUtil.post(getUrlString() + "edit_quit.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret open(PositionFuturesOpenBean bean) {
        String json = HttpUtil.post(getUrlString() + "open.php", bean);
        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret close(PositionFuturesCloseBean bean) {
        String json = HttpUtil.post(getUrlString() + "close.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret delete(PositionFuturesDeleteBean bean) {
        String json = HttpUtil.post(getUrlString() + "delete.php", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getActions(PositionFuturesActionsBean bean) {
        String json = HttpUtil.post(getUrlString() + "get_actions.php", bean);
        Sret sr = getSret(json, String.class);
        return sr;
    }

}
