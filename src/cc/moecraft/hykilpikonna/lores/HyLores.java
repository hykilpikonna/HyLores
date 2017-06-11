package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.essentials.logger.Logger;
import cc.moecraft.hykilpikonna.lores.Listeners.Effects.AttackEffectListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static cc.moecraft.hykilpikonna.lores.Configs.checkConfig;
import static cc.moecraft.hykilpikonna.lores.Setup.setup;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyLores extends JavaPlugin implements Listener
{
    public static Logger loglogger = new Logger("HyLores", true);
    private static HyLores instance = null;

    /**
     * 加载插件
     */
    public void onEnable()
    {
        loglogger.log("[加载]此插件正在加载......");
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        setup();
        
        Bukkit.getPluginManager().registerEvents(this, this);
        loglogger.log("[加载]此插件加载完成!");
    }

    /**
     * 卸载插件
     */
    public void onDisable()
    {
        loglogger.log("此插件已卸载!");
    }

    /**
     * 获取实例
     * @return 实例
     */
    public static HyLores getInstance()
    {
        return instance;
    }
}
