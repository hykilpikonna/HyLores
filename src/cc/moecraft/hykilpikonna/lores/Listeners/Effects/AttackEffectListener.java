package cc.moecraft.hykilpikonna.lores.Listeners.Effects;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.inventivetalent.particle.ParticleEffect;

import java.util.concurrent.ThreadLocalRandom;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AttackEffectListener implements Listener
{
    public AttackEffectListener()
    {
        loglogger.Debug("[事件监听器][注册]AttackEffectListener已经注册监听");
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("HyLores"));
    }

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event)
    {
        loglogger.Debug("[事件监听器][AEL]事件被激发.");
        if (event.getDamager() instanceof Player)
        {
            if (Math.round(event.getDamage()) > 0)
            {
                Player player = (Player) event.getDamager();
                loglogger.Debug(String.format("[事件监听器][AEL]玩家已被存入缓存, 玩家名: %s", player.getName()));
                Location location = event.getEntity().getLocation();
                loglogger.Debug("[事件监听器][AEL]已发送");
                ParticleEffect.HEART.send(Bukkit.getOnlinePlayers(), location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D, 1, (int) Math.round(event.getDamage()), 2);
                /*for (int i = 0; i < event.getDamage(); i++)
                {
                    double xCenter = location.getX() + 0.5;
                    double yCenter = location.getY() + 1;
                    double zCenter = location.getZ() + 0.5;
                    loglogger.Debug(String.format("[事件监听器][AEL]已获取坐标中心, [%s,%s,%s]", xCenter, yCenter, zCenter));
                    xCenter = xCenter + (ThreadLocalRandom.current().nextInt(-500, 500) / 1000);
                    yCenter = yCenter + (ThreadLocalRandom.current().nextInt(-1000, 1000) / 1000);
                    zCenter = zCenter + (ThreadLocalRandom.current().nextInt(-500, 500) / 1000);
                    loglogger.Debug(String.format("[事件监听器][AEL]坐标已随机, [%s,%s,%s]", xCenter, yCenter, zCenter));
                    Location randomLocation = event.getEntity().getLocation();
                    randomLocation.setX(xCenter);
                    randomLocation.setY(yCenter);
                    randomLocation.setZ(zCenter);
                    loglogger.Debug("[事件监听器][AEL]坐标已存入坐标变量");
                    randomLocation.getWorld().playEffect(randomLocation, Effect.HEART, );
                }*/
            }
        }
        else
        {
            loglogger.Debug("[事件监听器][AEL]事件已退出, 攻击者不是玩家");
        }
    }
}
