package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.aspect.Notify;
import org.yecq.goleek.desktop.bean.param.PositionStockCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionStockDeleteBean;
import org.yecq.goleek.desktop.bean.param.PositionStockEditBean;
import org.yecq.goleek.desktop.bean.param.PositionStockOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionStockInfoBean;
import org.yecq.goleek.desktop.service.Sret;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
public class PositionStockAgent extends AgentBase {

    //复写路径
    @Override
    public String getUrlString() {
        return super.getUrlString() + "position_stock/";
    }

    public Sret getgetListAll() {
        String json = HttpUtil.post(getUrlString() + "get_list_all.go", null);
        Sret sr = getSret(json, PositionStockInfoBean.class);
        return sr;
    }

    @Notify({"position_stock"})
    public Sret editQuit(PositionStockEditBean bean) {
        String json = HttpUtil.post(getUrlString() + "edit_quit.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret open(PositionStockOpenBean bean) {
        String json = HttpUtil.post(getUrlString() + "open.go", bean);
        Sret sr = getSretSingle(json, String.class);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret close(PositionStockCloseBean bean) {
        String json = HttpUtil.post(getUrlString() + "close.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret delete(PositionStockDeleteBean bean) {
        String json = HttpUtil.post(getUrlString() + "delete.go", bean);
        Sret sr = getSret(json, null);
        return sr;
    }

    public Sret getActions() {
        String json = HttpUtil.post(getUrlString() + "get_actions.go", null);
        Sret sr = getSret(json, String.class);
        return sr;
    }
}
