package cn.qfys521.xiaoming.plugin;

import cn.qfys521.xiaoming.plugin.LikeLucky;
import cn.chuanwise.xiaoming.annotation.Filter;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.chuanwise.xiaoming.user.GroupXiaoMingUser;
import cn.chuanwise.xiaoming.annotation.Required;
import cn.chuanwise.xiaoming.annotation.FilterParameter;
import cn.chuanwise.xiaoming.interactor.SimpleInteractors;

/**
插件交互器类，也就是命令。
@author qfys521
*/
public class LuckInteractor extends SimpleInteractors<LikeLucky> {
    @Filter("签到")
    public void lcs(GroupXiaoMingUser user) {
        int lccount = new Random().nextInt(20);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日，hh时mm分");
        Date date = new Date();
        String nowtime = sdf.format(date);

        if(nowtime.equals(plugin.datas.getLastDate(user.getCode()))){
            user.sendMessage("ヾ(≧へ≦)〃,您今天已经过签到啦");
        }else {
            plugin.datas.addLuckyCount(user.getCode(), lccount);
            long myC = plugin.datas.getLuckyCount(user.getCode());
            plugin.datas.saveOrFail();
            user.sendMessage("现在是" + nowtime+
                    "\n您获得了" + lccount + "点Coin。\n"+"您当前拥有" + myC + "点Coin。");
        }


    }

    @Filter("修改Coin {QQ} {Count}")
    @Required("Lucky.admin.set")
    public void setC(GroupXiaoMingUser user, @FilterParameter("QQ") long qq, @FilterParameter("Count") long count) {
        plugin.datas.setLuckyCount(qq, count);
        plugin.datas.saveOrFail();
        user.sendMessage("已经尝试修改辣！");
        user.sendMessage("现在" + qq + "拥有" + plugin.datas.getLuckyCount(qq) + "点Coin！");

    }

    @Filter("抽奖 {投入的数量}")
    public void dfp(GroupXiaoMingUser user, @FilterParameter("投入的数量") long count) {
        if (plugin.datas.getLuckyCount(user.getCode()) < count) {
            user.sendError("您没有那么多Coin！请签到获得吧！");
        } else if (count >= Integer.MAX_VALUE) {
            user.sendError("数量太多辣");
        } else if (count <= Integer.MIN_VALUE) {
            user.sendError("这个数字太奇怪辣");
        } else if (count < 0) {
            long c = new Random().nextInt(6) != 1 ? new Random().nextInt(50) : new Random().nextInt(500) * -1;
            if (plugin.datas.getLuckyCount(user.getCode()) < 0) {
                plugin.datas.setLuckyCount(user.getCode(), 0);
            }
            plugin.datas.addLuckyCount(user.getCode(), c);
            plugin.datas.saveOrFail();
            user.sendMessage("您本次投入了一枚命运のLuckyCoin，得到了" + c + "。现在您一共拥有" + plugin.datas.getLuckyCount(user.getCode()));
        } else if (count > 0) {
            plugin.datas.addLuckyCount(user.getCode(), count * -1);
            long ll = count;
            long c = new Random().nextInt(((int) (ll * 2)));
            plugin.datas.addLuckyCount(user.getCode(), c);
            plugin.datas.saveOrFail();
            user.sendMessage(
                    "您本次投入了" + count + "枚Coin，得到了" + c + "。现在您一共拥有" + plugin.datas.getLuckyCount(user.getCode()));
        } else {
            if (plugin.datas.getLuckyCount(user.getCode()) != 0) {
                user.sendMessage("您当前还有Coin，不能投出命运のLuckyCoin！");
            } else {
                long c = new Random().nextInt(6) != 1 ? new Random().nextInt(50) : new Random().nextInt(500) * -1;
                if (plugin.datas.getLuckyCount(user.getCode()) < 0) {
                    plugin.datas.setLuckyCount(user.getCode(), 0);
                }
                plugin.datas.addLuckyCount(user.getCode(), c);
                plugin.datas.saveOrFail();
                user.sendMessage(
                        "您本次投入了一枚命运のLuckyCoin，得到了" + c + "。现在您一共拥有" + plugin.datas.getLuckyCount(user.getCode()));
            }
        }
    }

    @Filter("我的Coin")
    public void myCount(GroupXiaoMingUser user) {
        user.sendMessage("您当前一共有" + plugin.datas.getLuckyCount(user.getCode()) + "枚Coin！");
    }

    @Filter("Coin查询 {QQ}")
    @Required("Lucky.admin.get")
    public void checkCoin(GroupXiaoMingUser user, @FilterParameter("QQ") long qq) {
        user.sendMessage("该用户一共有" + plugin.datas.getLuckyCount(user.getCode()) + "枚Coin！");
    }

    @Filter("剥夺命运のLuckyCoin {QQ}")
    @Required("Lucky.admin.cof.disable.other")
    public void isCOFCoinDisable(GroupXiaoMingUser user, @FilterParameter("QQ") long qq) {
        plugin.datas.isCOFEnable(qq, false);
        user.sendMessage("邪恶至极，剥夺命运！");
    }

    @Filter("赠与命运のLuckyCoin {QQ}")
    @Required("Lucky.admin.cof.enable.other")
    public void isCOFCoinEnable(GroupXiaoMingUser user, @FilterParameter("QQ") long qq) {
        plugin.datas.isCOFEnable(qq, true);
        user.sendMessage("神灵在世，祝福人间！");
    }

    @Filter("查询命运のLuckyCoin {QQ}")
    public void checkCOF(GroupXiaoMingUser user, @FilterParameter("QQ") long qq) {
        if (plugin.datas.getCOFEnable(qq)) {
            user.sendMessage("TA能够使用命运のLuckyCoin");
        } else {
            user.sendMessage("TA不能使用命运のLuckyCoin");
        }
    }

    @Filter("赠送 {QQ} {数量}")
    public void zs(GroupXiaoMingUser user, @FilterParameter("QQ") long qq, @FilterParameter("数量") int count) {
        if (plugin.datas.getLuckyCount(user.getCode()) < count) {
            user.sendMessage("抱歉，您没有这么多Coin!");
        } else if (count > Integer.MAX_VALUE) {
            user.sendMessage("抱歉，数量太大了！");
        } else {
            plugin.datas.addLuckyCount(user.getCode(), count * -1);
            plugin.datas.addLuckyCount(qq, count);
            user.sendMessage(
                    "已经向对方赠送了" + count + "枚Coin！\n" + "当前您还剩下" + plugin.datas.getLuckyCount(user.getCode()) + "枚Coin！");
        }
    }

}