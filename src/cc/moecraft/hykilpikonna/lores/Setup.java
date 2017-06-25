package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.lores.Features.Lores.Buff.DamageBoostListener;
import cc.moecraft.hykilpikonna.lores.Features.Lores.Buff.DefenceBoostListener;
import cc.moecraft.hykilpikonna.lores.Features.Effects.AttackEffectListener;
import cc.moecraft.hykilpikonna.lores.Features.Lores.Buff.LootingListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

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
    //加载监听器
    public static final AttackEffectListener ATTACK_EFFECT_LISTENER = new AttackEffectListener();
    public static final DamageBoostListener DAMAGE_BOOST_LISTENER = new DamageBoostListener();
    public static final DefenceBoostListener DEFENCE_BOOST_LISTENER = new DefenceBoostListener();
    public static final LootingListener LOOTING_LISTENER = new LootingListener();

    public static void setup()
    {
        loglogger.Debug("[加载]正在运行Setup");
        if (getInstance().getConfig().getBoolean("API.UsePlayEffectAPIInsteadOfParticleLib"))
        {
            if (!checkPlayEffect())
            {
                loglogger.log(ChatColor.RED + "[加载]未检测到PlayEffectAPI, 插件自动退出");
                unload(Bukkit.getPluginManager().getPlugin("HyLores"));
                return;
            }
        }
        else
        {
            if (!checkParticleLIB())
            {
                loglogger.log(ChatColor.RED + "[加载]未检测到ParticleLibAPI, 插件自动退出");
                unload(Bukkit.getPluginManager().getPlugin("HyLores"));
                return;
            }
        }
        checkConfig();
    }

    private static boolean checkPlayEffect()
    {
        return  Bukkit.getServer().getPluginManager().getPlugin("PlayEffect") != null;
    }

    private static boolean checkParticleLIB()
    {
        return Bukkit.getServer().getPluginManager().getPlugin("ParticleLIB") != null;
    }
}
