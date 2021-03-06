package cc.moecraft.hykilpikonna.lores;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;

import java.util.Objects;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.convertColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.VersionUtils.getShortVersion;
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
        config.addDefault("API.UsePlayEffectAPIInsteadOfParticleLib", true);
        loglogger.Debug(String.format("[加载]当前服务器版本为%s", Bukkit.getVersion()));

        //版本
        config.addDefault("MinecraftVersion.FullID", Bukkit.getVersion());
        config.addDefault("MinecraftVersion.ID", getShortVersion());
        loglogger.Debug(String.format("[加载]当前服务器缩略版本号为%s", getShortVersion()));

        //权限
        config.addDefault("Permissions.ReceiveMessage.Require", false);
        config.addDefault("Permissions.ReceiveMessage.Node", "hylores.message.receive");
        config.addDefault("Permissions.ReceiveHelpMessage.Require", false);
        config.addDefault("Permissions.ReceiveHelpMessage.Node", "hylores.message.receive.help");

        //指令权限
        config.addDefault("Permissions.Command.reload.Require", true);
        config.addDefault("Permissions.Command.reload.Node", "hylores.command.reload");
        config.addDefault("Permissions.Command.setname.Require", true);
        config.addDefault("Permissions.Command.setname.Node", "hylores.command.setname");
        config.addDefault("Permissions.Command.lore.add.Require", true);
        config.addDefault("Permissions.Command.lore.add.Node", "hylores.command.lore.add");
        config.addDefault("Permissions.Command.lore.remove.Require", true);
        config.addDefault("Permissions.Command.lore.remove.Node", "hylores.command.lore.remove");
        config.addDefault("Permissions.Command.lore.set.Require", true);
        config.addDefault("Permissions.Command.lore.set.Node", "hylores.command.lore.set");
        config.addDefault("Permissions.Command.lore.insert.Require", true);
        config.addDefault("Permissions.Command.lore.insert.Node", "hylores.command.lore.insert");
        config.addDefault("Permissions.Command.head.get.Require", true);
        config.addDefault("Permissions.Command.head.get.Node", "hylores.command.head.get");

        //功能权限
        config.addDefault("Permissions.Lore.Buff.DamageBoost.Require", false);
        config.addDefault("Permissions.Lore.Buff.DamageBoost.Node", "hylores.lore.buff.damageboost");
        config.addDefault("Permissions.Lore.Buff.DefenceBoost.Require", false);
        config.addDefault("Permissions.Lore.Buff.DefenceBoost.Node", "hylores.lore.buff.defenceboost");
        config.addDefault("Permissions.Lore.Buff.Looting.Require", false);
        config.addDefault("Permissions.Lore.Buff.Looting.Node", "hylores.lore.buff.looting");
        config.addDefault("Permissions.Lore.Buff.Looting.Bypass.Require", true);
        config.addDefault("Permissions.Lore.Buff.Looting.Bypass.Node", "hylores.lore.buff.looting.bypass");

        //消息发送
        config.addDefault("Messaging.Prefix", convertColorCode("&7&l[&6&lHy&3&lLores&7&l] &r"));

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

        //头颅获取
        config.addDefault("Features.GetHead.Enable", true);
        config.addDefault("Features.Item.ChangeDisplayName", false);
        config.addDefault("Features.Item.DisplayName", "%s的头颅");

        //Lore
        config.addDefault("Lore.Prefix", ChatColor.GRAY + "*");
        config.addDefault("Lore.Buffs.DamageBoost.Enable", true);
        config.addDefault("Lore.Buffs.DamageBoost.Override", false);
        config.addDefault("Lore.Buffs.DamageBoost.KeyWord", "攻击力");
        config.addDefault("Lore.Buffs.DefenceBoost.Enable", true);
        config.addDefault("Lore.Buffs.DefenceBoost.Override", false);
        config.addDefault("Lore.Buffs.DefenceBoost.KeyWord", "防御力");
        config.addDefault("Lore.Buffs.Looting.Enable", true);
        config.addDefault("Lore.Buffs.Looting.WithoutLore.Enable", false);
        config.addDefault("Lore.Buffs.Looting.WithoutLore.DefaultItems", 3);
        config.addDefault("Lore.Buffs.Looting.KeyWord", "物品抢夺");
        config.addDefault("Lore.Buffs.AntiLooting.Enable", true);
        config.addDefault("Lore.Buffs.AntiLooting.KeyWord", "不可抢夺");

        /*其他
        config.addDefault("Misc.MathSymbol.Addition", "+");
        config.addDefault("Misc.MathSymbol.Subtraction", "-");
        config.addDefault("Misc.MathSymbol.Multiplication", "x");
        config.addDefault("Misc.MathSymbol.Division", "/");*/

        //保存配置
        getInstance().saveConfig();
    }
}