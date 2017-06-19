package cc.moecraft.hykilpikonna.lores.Features.Lores.Buff;

import cc.moecraft.hykilpikonna.lores.HyLores;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Permission.hasPermission;
import static cc.moecraft.hykilpikonna.lores.Utils.EventUtils.getEntityDamageByEntityEventPlayerDamager;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class DamageBoostListener implements Listener
{
    public DamageBoostListener()
    {
        loglogger.Debug("[事件监听器][注册]DamageBoost已经注册监听");
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("HyLores"));
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onEvent(EntityDamageByEntityEvent event)
    {
        //伤害加成
        loglogger.Debug("[事件监听器][DB]事件被激发.");
        if (HyLores.getInstance().getConfig().getBoolean("Lore.Buffs.DefenceBoost.Enable"))
        {
            //获取玩家
            Player player = getEntityDamageByEntityEventPlayerDamager(event);
            //检测玩家
            if (player == null) return;
            //检测权限
            if (!hasPermission(player, "Lore.Buff.DamageBoost")) return;
            //设置伤害
            loglogger.Debug(String.format("Damage = %s,  Final Damage = %s", event.getDamage(), event.getFinalDamage()));
            event.setDamage(Misc.calculateTotal(player, event.getDamage(), getInstance().getConfig().getString("Lore.Buffs.DamageBoost.KeyWord"), false));
        }
    }
}
