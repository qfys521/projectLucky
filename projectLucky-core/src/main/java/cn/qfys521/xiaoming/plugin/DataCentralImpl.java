package cn.qfys521.xiaoming.plugin;

import cn.chuanwise.toolkit.preservable.AbstractPreservable;

import java.util.HashMap;

/**
    数据中心类，用于存储插件数据,实现了{@link cn.qfys521.xiaoming.plugin.DataCentral}接口。
    @author qfys521
*/
public class DataCentralImpl extends AbstractPreservable implements DataCentral{
    //硬币，Coin
    HashMap<Long, Long> luckyCount = new HashMap<>();
    //命运の硬币，LuckyCoin
    HashMap<Long,Boolean> coinOfFateCount = new HashMap<>();

    /**
     * 设置指定用户的Coin数量。
     *
     * @param qq 被操作者的QQ号码。
     * @param setCount 被设置的数量。
     */
    public void setLuckyCount(long qq, long setCount) {
        this.luckyCount.put(qq, setCount);
    }

    /**
     * 添加指定用户的Coin数量。
     *
     * @param qq 被操作者的QQ号码。
     * @param addCount 被添加的数量。
     */
    public void addLuckyCount(long qq, long addCount) {
        this.luckyCount.put(qq, this.luckyCount.getOrDefault(qq, addCount)+addCount);
    }

    /**
     * 获取指定用户的Coin数量。
     * @param qq 被操作者的QQ号码。
     * @return 返回Coin数量
     */
    public long getLuckyCount(long qq) {
        return this.luckyCount.getOrDefault(qq,(long)0);
    }

    /**
     * COF是<code>coinOfFate</code>。</br>
     * 设置指定用户是否启用COF。
     *
     * @param qq 被操作者的QQ号码。
     * @param setCOFEnable 被设置的数量。
     */
    public void isCOFEnable(long qq,boolean setCOFEnable){
        this.coinOfFateCount.put(qq,setCOFEnable);
    }

    /**
     * COF是<code>coinOfFate</code>。</br>
     * 获取指定用户是否能够使用COF
     *
     * @param qq 被操作者的QQ号码。
     * @return 结果。
     */
    public boolean getCOFEnable(long qq){
        return this.coinOfFateCount.getOrDefault(qq,true);
    }
}