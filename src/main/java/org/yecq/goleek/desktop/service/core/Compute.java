package org.yecq.goleek.desktop.service.core;

/**
 *
 * @author yecq
 */
public final class Compute {

    // 计算开仓标准手数
//    public static int getStandardOpenLot(double open_price, double margin, int unit, double position_percent, double money) {
//        if (open_price < 0 || (margin > 1 || margin < 0) || unit <= 0
//                || (position_percent > 1 || position_percent < 0) || money < 0) {
//            throw new IllegalArgumentException("参数有误");
//        }
//
//        return Algorithm.getOpenLotOfAccount(open_price, margin, unit, position_percent, money);
//    }
    // 计算止损比例
    public static double getLossPercentOfAccount(String direct, int lot, double open_price, double close_price, int unit, double money) {
        if (direct == null || (!direct.equals("多") && !direct.equals("空")) || lot < 0 || open_price <= 0 || close_price <= 0
                || unit <= 0 || money < 0) {
            throw new IllegalArgumentException("参数有误");
        }
        if (Double.compare(money, 0) == 0) {
            throw new IllegalArgumentException("账户权益为0");
        }

        if (direct.equals("多") && open_price < close_price) {
            throw new IllegalArgumentException("止损价应小于开仓价");
        }
        if (direct.equals("空") && open_price > close_price) {
            throw new IllegalArgumentException("止损价应大于开仓价");
        }
        return Algorithm.getWinLossPercentOfAccount(direct, lot, open_price, close_price, unit, money);

    }

    // 计算股票止损比例
    public static double getLossPercentOfAccount4Stock(int lot, double open_price, double close_price, int unit, double money) {
        if (lot < 0 || open_price <= 0 || close_price <= 0
                || unit <= 0 || money < 0) {
            throw new IllegalArgumentException("参数有误");
        }

        if (Double.compare(money, 0) == 0) {
            throw new IllegalArgumentException("账户权益为0");
        }
        if (open_price < close_price) {
            throw new IllegalArgumentException("止损价应小于开仓价");
        }

        return (open_price - close_price) * lot * unit / money;

    }

    // 计算开仓占用的权益比例
    public static double getPositionMoneyPercent(int lot, double open_price, double margin, int unit, double money) {
        if (lot < 0 || open_price <= 0 || (margin <= 0 || margin > 1) || unit <= 0 || money < 0) {
            throw new IllegalArgumentException("参数有误");
        }
        if (Double.compare(money, 0) == 0) {
            throw new IllegalArgumentException("账户权益为0");
        }

        return Algorithm.getMoneyUsedPercent(lot, open_price, margin, unit, money);
    }

    // 计算盈亏额
//    public static double getWinLoss(String direct, int lot, double open_price, double close_price, int unit) {
//        if (direct == null || (!direct.equals("多") && !direct.equals("空")) || lot < 0 || open_price < 0
//                || close_price < 0 || unit <= 0) {
//            throw new IllegalArgumentException("参数有误");
//        }
//        return Algorithm.getWinLoss(direct, lot, open_price, close_price, unit);
//    }
    // 计算盈亏的账户比例
//    public static double getWinLossPercentOfAccount(String direct, int lot, double open_price, double close_price, int unit, double money) {
//        if (direct == null || (!direct.equals("多") && !direct.equals("空")) || lot < 0 || open_price < 0
//                || close_price < 0 || unit <= 0 || money < 0) {
//            throw new IllegalArgumentException("参数有误");
//        }
//
//        return Algorithm.getWinLossPercentOfAccount(direct, lot, open_price, close_price, unit, money);
//    }
    //  计算按仓位比例和止损比例确定的开仓手数
    public static int getOpenLotByPercentOfPositionAndLoss(double open_price, double margin,
            int unit, double position_percent, double money, double loss_price, double loss_percent) {
        if (open_price < 0 || (margin > 1 || margin < 0) || unit <= 0
                || (position_percent > 1 || position_percent < 0) || money < 0 || loss_price < 0 || (loss_percent > 1 || loss_percent < 0)) {
            throw new IllegalArgumentException("参数有误");
        }
        if (Double.compare(money, 0) == 0) {
            throw new IllegalArgumentException("账户权益为0");
        }
        int lot1 = Algorithm.getOpenLotOfAccount(open_price, margin, unit, position_percent, money);
        int lot2 = Algorithm.getLotsByOpenLossPriceOfAccount(open_price, loss_price, unit, loss_percent, money);
        return Math.min(lot1, lot2);
    }
}
