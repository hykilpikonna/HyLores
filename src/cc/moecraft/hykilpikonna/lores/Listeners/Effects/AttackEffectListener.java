package cc.moecraft.hykilpikonna.lores.Listeners.Effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.inventivetalent.particle.ParticleEffect;

import java.util.Random;
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
                for (int i = 0; i < event.getDamage(); i++)
                {
                    double xCenter = location.getX() + 0.5;
                    double yCenter = location.getY() + 1;
                    double zCenter = location.getZ() + 0.5;
                    loglogger.Debug(String.format("[事件监听器][AEL]已获取坐标中心, [%s,%s,%s]", xCenter, yCenter, zCenter));
                    Random random = new Random();
                    double xRandom = ((double) random.nextInt(100) - 50.0) / 100.0;
                    double yRandom = ((double) random.nextInt(150) - 75.0) / 100.0;
                    double zRandom = ((double) random.nextInt(100) - 50.0) / 100.0;
                    loglogger.Debug(String.format("[事件监听器][AEL]随机已生成, [%s,%s,%s]", xRandom, yRandom, zRandom));
                    xCenter = xCenter + xRandom;
                    yCenter = yCenter + yRandom;
                    zCenter = zCenter + zRandom;
                    loglogger.Debug(String.format("[事件监听器][AEL]随机坐标已保存, [%s,%s,%s]", xCenter, yCenter, zCenter));
                    ParticleEffect.HEART.send(Bukkit.getOnlinePlayers(), xCenter, yCenter, zCenter, 0, 0, 0, 10, (int) Math.round(event.getDamage()), 100);
                }
            }
        }
        else
        {
            loglogger.Debug("[事件监听器][AEL]事件已退出, 攻击者不是玩家");
        }
    }
}
