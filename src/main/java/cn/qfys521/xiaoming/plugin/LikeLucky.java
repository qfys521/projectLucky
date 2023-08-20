package cn.qfys521.xiaoming.plugin;

import java.io.File;

import cn.chuanwise.xiaoming.plugin.JavaPlugin;

/**
 * 插件<code>projectLucky入口类</code></br>
 * 继承了{@link cn.chuanwise.xiaoming.plugin.JavaPlugin}类。
 * @author qfys521
 */
public class LikeLucky extends JavaPlugin{

    private static final LikeLucky INSTANCE = new LikeLucky();

    /**
     * 获取插件实例。
     *
     * @return 返回插件实例。
     */
    public static LikeLucky getInstance() {
        return INSTANCE;
    }

    protected DataCentral datas;

    /**
     * 插件被加载时的回调方法。
     */
    @Override
    @SuppressWarnings("all")
    public void onLoad() {
        getLogger().info("");
        getLogger().info("插件正在启动...");
        getXiaoMingBot().getInteractorManager().registerInteractors(new LuckInteractor(), this);
        final File dataFolder = getDataFolder();
        dataFolder.mkdirs();
        datas = xiaoMingBot.getFileLoader().loadOrSupply(DataCentral.class, new File(dataFolder, "coins.json"), DataCentral::new);
    }

}