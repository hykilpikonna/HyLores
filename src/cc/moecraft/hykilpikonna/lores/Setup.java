package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.lores.Listeners.Effects.AttackEffectListener;
import jdk.nashorn.internal.runtime.Debug;
import me.fromgate.playeffect.PlayEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import static cc.moecraft.hykilpikonna.lores.Configs.checkConfig;
import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.PluginUtil.unload;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Setup
{
    public static final AttackEffectListener ATTACK_EFFECT_LISTENER = new AttackEffectListener();
    public static void setup()
    {
        loglogger.Debug("[加载]正在运行Setup");
        if (!checkPlayEffect() && getInstance().getConfig().getBoolean("API.UsePlayEffectAPIInsteadOfParticleLib"))
        {
            loglogger.log(ChatColor.RED + "[加载]未检测到PlayEffectAPI, 插件自动退出");
            unload(Bukkit.getPluginManager().getPlugin("HyLores"));
        }
        checkConfig();
    }

    private static boolean checkPlayEffect()
    {
        Plugin vplg = Bukkit.getServer().getPluginManager().getPlugin("PlayEffect");
        return  ((vplg != null) && (vplg instanceof PlayEffect));
    }
}
