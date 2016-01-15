package org.yecq.goleek.desktop.service.core;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 期货各类算法
 *
 * @author yecq
 */
public final class Algorithm {

    // 根据开仓价和止损价计算开仓手数
    public static int getLotsByOpenLossPriceOfAccount(double open_price, double loss_price, int unit, double loss_percent,
            double money) {
        double lots = 0;
        if (open_price > 0 && loss_price > 0 && loss_percent > 0 && money > 0 && unit > 0) {
            lots = (money * loss_percent) / (unit * Math.abs(loss_price - open_price));
        }
        return (int) lots;
    }

    //计算止损价格
    public static double getLossPrice(String direct, double price, double percent, double margin) {
        if (direct != null && (direct.equals("多") || direct.equals("空")) && price > 0 && percent > 0 && margin > 0) {
            if (direct.equals("多")) {
                return price * (1 - percent * margin);
            } else {
                return price * (1 + percent * margin);
            }
        }

        throw new IllegalArgumentException("输入的数据有误");
    }

    // 根据总权益值计算开仓价格
    public static double getOpenPriceOfAccount(String direct, double price, int lots, int unit, double money, double percent) {
        if (lots == 0) {
            return Double.NaN;
        }
        if (direct != null && (direct.equals("多") || direct.equals("空")) && price > 0 && percent > 0 && unit > 0) {
            double lots1 = (double) lots;
            double unit1 = (double) unit;
            if (direct.equals("多")) {
                return price + (money * percent) / (lots1 * unit1);
            } else {
                return price - (money * percent) / (lots1 * unit1);
            }
        }

        throw new IllegalArgumentException("输入的数据有误");
    }

    // 根据总权益值计算止损价格
    public static double getLossPriceOfAccount(String direct, double price, int lots, int unit, double money, double percent) {
        if (lots == 0) {
            return Double.NaN;
        }
        if (direct != null && (direct.equals("多") || direct.equals("空")) && price > 0 && percent > 0 && unit > 0) {
            double lots1 = (double) lots;
            double unit1 = (double) unit;
            if (direct.equals("多")) {
                return price - (money * percent) / (lots1 * unit1);
            } else {
                return (money * percent) / (lots1 * unit1) + price;
            }
        }

        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算标准参考的止损价格，注意这个价格还没有经过Fit
    public static double getStandardLossPrice(String direct, double open_price, double money, double open_percent, double margin, int unit, double loss_percent) {
        // 计算此时开仓可以开多少手
        int lots = (int) ((money * open_percent) / (open_price * unit * margin));
        return getLossPriceOfAccount(direct, open_price, lots, unit, money, loss_percent);
    }

    // 计算止盈价格
    public static double getWinPrice(String direct, double price, double percent, double margin) {
        if (direct != null && (direct.equals("多") || direct.equals("空")) && price > 0 && percent > 0 && margin > 0) {
            if (direct.equals("多")) {
                return price * (1 + percent * margin);
            } else {
                return price * (1 - percent * margin);
            }
        }

        throw new IllegalArgumentException("输入的数据有误");
    }

    // 根据总权益值计算止盈价格
    public static double getWinPriceOfAccount(String direct, double price, int lots, int unit, double money, double percent) {
        if (lots == 0) {
            return Double.NaN;
        }
        if (direct != null && (direct.equals("多") || direct.equals("空")) && price > 0 && percent > 0 && unit > 0) {
            double lots1 = (double) lots;
            double unit1 = (double) unit;
            if (direct.equals("多")) {
                return price + (money * percent) / (lots1 * unit1);
            } else {
                return price - (money * percent) / (lots1 * unit1);
            }
        }

        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算开仓所用的保证金
    public static double getMoneyUsed(int lot, double price, double margin, int unit) {
        if (lot >= 0 && price > 0 && margin > 0 && unit > 0) {
            return lot * unit * price * margin;
        }

        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算开仓所占权益的比例
    public static double getMoneyUsedPercent(int lot, double price, double margin, int unit, double money) {
        if (lot >= 0 && price > 0 && margin > 0 && unit > 0 && money > 0) {
            return getMoneyUsed(lot, price, margin, unit) / money;
        }
        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算盈利或者亏损额
    public static double getWinLoss(String direct, int lot, double price1, double price2, int unit) {
        if (direct != null && (direct.equals("多") || direct.equals("空")) && lot >= 0 && price1 > 0 && price2 > 0 && unit > 0) {
            if (direct.equals("多")) {
                return (price2 - price1) * lot * unit;
            } else {
                return (price1 - price2) * lot * unit;
            }
        }
        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算盈利或者亏损比例，这是与开仓用的资金比的
    public static double getWinLossPercent(String direct, int lot, double price1, double price2, int unit, double margin) {
        if (direct != null && (direct.equals("多") || direct.equals("空")) && lot >= 0 && price1 > 0 && price2 > 0 && unit > 0 && margin > 0) {
            return getWinLoss(direct, lot, price1, price2, unit) / getMoneyUsed(lot, price1, margin, unit);
        }
        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算盈利或者亏损比例，与总权益值相比
    public static double getWinLossPercentOfAccount(String direct, int lot, double price1, double price2, int unit, double money) {
        if (direct != null && (direct.equals("多") || direct.equals("空")) && lot >= 0 && price1 > 0 && price2 > 0 && unit > 0 && money >= 0) {
            return getWinLoss(direct, lot, price1, price2, unit) / money;
        }
        throw new IllegalArgumentException("输入的数据有误");

    }

    // 输入任意一个价格，根据最小变动单位来自动对齐价格，简单一点的算法
    public static double getFitPrice(double price, double min_change) {
        return ((int) (price / min_change)) * min_change;
    }

    // 由止损价格计算开仓价格
    public static double getOpenPriceByLossPrice(String direct, double loss_price, double loss_percent, double margin) {
        if (direct != null && (direct.equals("多") || direct.equals("空")) && loss_price > 0
                && (loss_percent >= 0 && loss_percent <= 1) && (margin >= 0 && margin <= 1)) {
            if (direct.equals("多")) {
                return loss_price / (1 - loss_percent * margin);
            } else {
                return loss_price / (1 + loss_percent * margin);
            }
        }
        throw new IllegalArgumentException("输入的数据有误");
    }

    // 计算盈亏率
    public static double getWinLossRatio(double win, double loss) {
        return Math.abs(win / loss);
    }

    // 计算胜率
    public static double getWinPercent(int win, int loss) {
        return (double) win / ((double) win + (double) loss);
    }

    // 根据比例计算价格
    public static double getPriceByPercent(String direct, double price, double percent, double margin, double min_change) {
        double ret = 0;
        margin = 0.1;     //margin可能会经常变，这里暂采用固定的值
        if (direct.equals("多")) {
            ret = price * (1 + percent * margin);
        } else if (direct.equals("空")) {
            ret = price * (1 - percent * margin);
        }
        return getFitPrice(ret, min_change);
    }

    // 根据开仓价格和开仓比例计算开仓手数
    public static int getOpenLotOfAccount(double price, double margin, int unit, double position_percent, double money) {
        return (int) ((money * position_percent) / (price * unit * margin));
    }

    /**
     * ***************** 净值计算公式 ******************* 
     * 当日收益率 = (当日权益 + 当天出金)/(昨日权益 + 当天入金) -1 
     * 当日累计净值 = (1 + 当日收益率) * 昨日累计净值 
     * 账户第一天开始算，昨日累计净值为1 
     * “当日权益 + 当天出金”和“昨日权益 + 当天入金”只要一个是0， 则 
     * 当日累计净值 = 昨日累计净值 
     * 当日累计收益率 = （当日累计净值 - 1）* 100%
     *
     * @param data
     * @return
     */
    public static List<Object[]> getEquity(List<Object[]> data) {   // String, Double
        List<Object[]> ret = new LinkedList<>();

        // data 的格式：日期，权益，入金，出金 (String,Double,Double,Double)
        int len = data.size();
        for (int i = 0; i < len; i++) {
            Object[] tmp = data.get(i);
            String date = String.valueOf(tmp[0]);
            Double money_today = (Double) tmp[1];
            Double income = (Double) tmp[2];
            Double outcome = (Double) tmp[3];

            Object[] o = new Object[2];
            o[0] = date;

            // 如果是第一条记录，则净值为1
            if (i == 0) {
                o[1] = 1.00;
            } else {
                // 取出上一日的权益
                Double money_last = (Double) data.get(i - 1)[1];
                // 取出上一日的净值
                Double e_last = (Double) ret.get(i - 1)[1];

                Double e = 0.;
                if (Double.compare(money_today + outcome, 0) == 0
                        || Double.compare(money_last + income, 0) == 0) {
                    e = e_last;
                } else {
                    e = e_last * (money_today + outcome) / (money_last + income);
                }
//                DecimalFormat format = new DecimalFormat("0.00");
//                o[1] = format.format(e);
                o[1] = e;
            }
            ret.add(o);
        }

        return ret;
    }
}
