package cc.moecraft.hykilpikonna.lores;

import org.bukkit.configuration.Configuration;

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
        if (config.getBoolean("DefaultConfig"))
        {

        }
    }
}