package org.yecq.goleek.desktop.agent;

import org.yecq.goleek.desktop.bean.param.PositionStockCloseBean;
import org.yecq.goleek.desktop.bean.param.PositionStockEditBean;
import org.yecq.goleek.desktop.bean.param.PositionStockOpenBean;
import org.yecq.goleek.desktop.bean.result.PositionStockInfoBean;
import org.springframework.stereotype.Component;
import org.yecq.baseframework.plain.core.Notify;
import org.yecq.baseframework.plain.service.Sret;

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
        String json = HttpUtil.post(getUrlString() + "get_list_all.php", null);
        Sret sr = getSretList(json, PositionStockInfoBean.class);
        return sr;
    }

    @Notify({"position_stock"})
    public Sret editQuit(String id, PositionStockEditBean bean) {
        String json = HttpUtil.post(getUrlString() + "edit_quit.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret open(PositionStockOpenBean bean) {
        String json = HttpUtil.post(getUrlString() + "open.php", bean);
        Sret sr = getSretObject(json, String.class);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret close(String id, PositionStockCloseBean bean) {
        String json = HttpUtil.post(getUrlString() + "close.php", id, bean);
        Sret sr = getSretOnly(json);
        return sr;
    }

    @Notify({"position_detail_stock", "position_stock", "detail_stock"})
    public Sret delete(String id) {
        String json = HttpUtil.post(getUrlString() + "delete.php", id, null);
        Sret sr = getSretOnly(json);
        return sr;
    }

    public Sret getActions() {
        String json = HttpUtil.post(getUrlString() + "get_actions.php", null);
        Sret sr = getSretObject(json, String[].class);
        return sr;
    }
}
