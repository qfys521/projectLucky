package cn.qfys521.xiaoming.plugin;

/**
 * 接口 DataCentral
 * @author qfys521
 */
public interface DataCentral {
    /**
     * 设置指定用户的Coin数量。
     *
     * @param qq 被操作者的QQ号码。
     * @param setCount 被设置的数量。
     */
    void setLuckyCount(long qq, long setCount);

    /**
     * 添加指定用户的Coin数量。
     *
     * @param qq 被操作者的QQ号码。
     * @param addCount 被添加的数量。
     */
    void addLuckyCount(long qq, long addCount);

    /**
     * 获取指定用户的Coin数量。
     * @param qq 被操作者的QQ号码。
     * @return 返回Coin数量
     */
    long getLuckyCount(long qq);

    /**
     * COF是<code>coinOfFate</code>。</br>
     * 设置指定用户是否启用COF。
     *
     * @param qq 被操作者的QQ号码。
     * @param setCOFEnable 被设置的数量。
     */
    void isCOFEnable(long qq,boolean setCOFEnable);

    /**
     * COF是<code>coinOfFate</code>。</br>
     * 获取指定用户是否能够使用COF
     *
     * @param qq 被操作者的QQ号码。
     * @return 结果。
     */
    boolean getCOFEnable(long qq);

    /**
     * 获取最后一次签到日期
     *
     * @param qq 被操作者的QQ号码。
     * @return 最后一次签到日期,yyyy-MM-dd
     */
    Long getLastDate(long qq);

    /**
     * 修改最后签到日期
     * @param qq 被操作者的QQ号码。
     */
    void setLastDate(long qq);

}
