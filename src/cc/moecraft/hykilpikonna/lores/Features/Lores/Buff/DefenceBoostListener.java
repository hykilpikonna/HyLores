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
 * 此类由 Hykilpikonna 在 2017/06/19 创建!
 * Created by Hykilpikonna on 2017/06/19!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class DefenceBoostListener implements Listener
{
    public DefenceBoostListener()
    {
        loglogger.Debug("[事件监听器][注册]DefenceBoost已经注册监听");
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("HyLores"));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(EntityDamageByEntityEvent event)
    {
        //防御加成
        loglogger.Debug("[事件监听器][DB]事件被激发.");
        if (HyLores.getInstance().getConfig().getBoolean("Lore.Buffs.DefenceBoost.Enable"))
        {
            if (!(event.getEntity() instanceof Player)) return;
            //获取玩家
            Player player = (Player) event.getEntity();
            //检测权限
            if (!hasPermission(player, "Lore.Buff.DefenceBoost")) return;
            //设置伤害
            double calculated = Misc.calculateTotal(player, event.getDamage(), getInstance().getConfig().getString("Lore.Buffs.DefenceBoost.KeyWord"), true);
            if (calculated < 0) calculated = 0;
            event.setDamage(calculated);
        }
    }
}
