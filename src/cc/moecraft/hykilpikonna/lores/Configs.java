package cc.moecraft.hykilpikonna.lores;

import org.bukkit.configuration.Configuration;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Configs
{
    static Configuration config = HyLores.getInstance().getConfig();
    static void checkConfig()
    {
        loglogger.Debug("[加载]正在检查配置");
        if (config.getBoolean("DefaultConfig"))
        {

        }
    }
}