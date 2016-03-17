package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.PositionFuturesActionsBean;
import org.yecq.goleek.desktop.bean.param.PositionFuturesCloseBean;
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
        Sret sr = getSretList(json, PositionFuturesInfoBean.class);
        return sr;
    }

    @Notify({"position_futures"})
    public Sret editQuit(String id, PositionFuturesEditBean bean) {
        String json = HttpUtil.post(getUrlString() + "edit_quit.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret open(PositionFuturesOpenBean bean) {
        String json = HttpUtil.post(getUrlString() + "open.php", bean);
        Sret sr = getSretObject(json, String.class);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret close(String id, PositionFuturesCloseBean bean) {
        String json = HttpUtil.post(getUrlString() + "close.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"position_detail_futures", "position_futures", "detail_futures"})
    public Sret delete(String id) {
        String json = HttpUtil.post(getUrlString() + "delete.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    public Sret getActions(PositionFuturesActionsBean bean) {
        String json = HttpUtil.post(getUrlString() + "get_actions.php", bean);
        Sret sr = getSretObject(json, String[].class);
        return sr;
    }

}
