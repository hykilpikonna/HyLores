package cc.moecraft.hykilpikonna.lores.Utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;

/**
 * 此类由 Hykilpikonna 在 2017/06/19 创建!
 * Created by Hykilpikonna on 2017/06/19!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class EventUtils
{
    public static Player getEntityDamageByEntityEventPlayerDamager(EntityDamageByEntityEvent event)
    {
        Player player;
        try
        {
            if (event.getDamager() instanceof Player)
            {
                player = (Player) event.getDamager();
                loglogger.Debug(String.format("[事件Utils][玩家获取]实体为玩家, 玩家已被存入缓存, 玩家名: %s", player.getName()));
            }
            else if (event.getDamager() instanceof Arrow)
            {
                player = (Player) ((Arrow) event.getDamager()).getShooter();
                loglogger.Debug(String.format("[事件Utils][玩家获取]实体为箭, 玩家已被存入缓存, 玩家名: %s", player.getName()));
            }
            else
            {
                loglogger.Debug("[事件Utils][玩家获取]事件已退出, 攻击者不是玩家");
                return null;
            }
        }
        catch (ClassCastException e)
        {
            loglogger.Debug("[事件Utils][玩家获取]事件已退出, Cast出错");
            return null;
        }
        return player;
    }
}
