package cc.moecraft.hykilpikonna.lores;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;

import java.util.Objects;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Configs
{
    static Configuration config = null;
    static void checkConfig()
    {
        loglogger.Debug("[加载]正在检查配置");
        config = HyLores.getInstance().getConfig();
        if (config.getBoolean("DefaultConfig"))
        {
            loglogger.Debug("[加载]检查到配置是新的, 正在生成配置");
            writeConfig();
            loglogger.setDebug(config.getBoolean("Debug"));
        }
        else
        {
            if (!config.getString("ConfigVersion").equals(Bukkit.getPluginManager().getPlugin("HyLores").getDescription().getVersion()))
            {
                loglogger.Debug("[加载]检查到配置不是最新的, 正在生成配置");
                writeConfig();
                loglogger.setDebug(config.getBoolean("Debug"));
            }
            else
            {
                loglogger.Debug("[加载]配置已是最新的");
                loglogger.setDebug(config.getBoolean("Debug"));
            }
        }
    }

    private static void writeConfig()
    {
        //把默认配置设成否
        config.set("DefaultConfig", false);

        //是否Debug
        config.addDefault("Debug", true);

        //配置版本
        config.set("ConfigVersion", Bukkit.getPluginManager().getPlugin("HyLores").getDescription().getVersion());

        //API版本
        config.addDefault("API.UsePlayEffectAPIInsteadOfParticleLib", Bukkit.getVersion().contains("1.7"));
        loglogger.Debug(String.format("[加载]当前服务器版本为%s", Bukkit.getVersion()));

        //权限
        config.addDefault("Permissions.ReceiveMessage.Require", false);
        config.addDefault("Permissions.ReceiveMessage.Node", "hylores.message.receive");
        config.addDefault("Permissions.ReceiveHelpMessage.Require", false);
        config.addDefault("Permissions.ReceiveHelpMessage.Node", "hylores.message.receive.help");

        //消息发送
        config.addDefault("Messaging.Prefix", GRAY + "[" + GOLD + "Hy" + LIGHT_PURPLE + "Lores" + GRAY + "]" + GREEN);

        //心心特效
        config.addDefault("Features.AttackEffect.Enable", true);
        config.addDefault("Features.AttackEffect.Amount.Multiplier", 1);
        config.addDefault("Features.AttackEffect.Amount.Maximum", 50);
        config.addDefault("Features.AttackEffect.Amount.Minimum", 0);
        config.addDefault("Features.AttackEffect.Centering.OffsetX", 0.5);
        config.addDefault("Features.AttackEffect.Centering.OffsetY", 1);
        config.addDefault("Features.AttackEffect.Centering.OffsetZ", 0.5);
        config.addDefault("Features.AttackEffect.Randomizing.X.Range", 100);
        config.addDefault("Features.AttackEffect.Randomizing.X.Add", -50);
        config.addDefault("Features.AttackEffect.Randomizing.X.Divide", 100);
        config.addDefault("Features.AttackEffect.Randomizing.Y.Range", 150);
        config.addDefault("Features.AttackEffect.Randomizing.Y.Add", -75);
        config.addDefault("Features.AttackEffect.Randomizing.Y.Divide", 100);
        config.addDefault("Features.AttackEffect.Randomizing.Z.Range", 100);
        config.addDefault("Features.AttackEffect.Randomizing.Z.Add", -50);
        config.addDefault("Features.AttackEffect.Randomizing.Z.Divide", 100);
        config.addDefault("Features.AttackEffect.VisibleRange", 100);

        //Lore
        config.addDefault("Lore.Prefix", GRAY + "[" + DARK_PURPLE + "Hyl" + GRAY + "]");

        //保存配置
        getInstance().saveConfig();
    }
}