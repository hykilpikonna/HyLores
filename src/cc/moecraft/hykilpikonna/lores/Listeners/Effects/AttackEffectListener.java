package cc.moecraft.hykilpikonna.lores.Listeners.Effects;

import cc.moecraft.hykilpikonna.lores.HyLores;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.inventivetalent.particle.ParticleEffect;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
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
        if (HyLores.getInstance().getConfig().getBoolean("Features.AttackEffect.Enable"))
        {
            if (event.getDamager() instanceof Player)
            {
                if (Math.round(event.getDamage()) > 0)
                {
                    Player player = (Player) event.getDamager();
                    loglogger.Debug(String.format("[事件监听器][AEL]玩家已被存入缓存, 玩家名: %s", player.getName()));
                    Location location = event.getEntity().getLocation();
                    int amount = (int) Math.round(event.getDamage());
                    if (amount <= getInstance().getConfig().getInt("Features.AttackEffect.Amount.Maximum"))
                    {
                        if (amount >= getInstance().getConfig().getInt("Features.AttackEffect.Amount.Minimum"))
                        {
                            amount = amount * getInstance().getConfig().getInt("Features.AttackEffect.Amount.Multiplier");
                            for (int i = 0; i < event.getDamage(); i++)
                            {
                                double xCenter = location.getX() + getInstance().getConfig().getInt("Features.AttackEffect.Centering.OffsetX");
                                double yCenter = location.getY() + getInstance().getConfig().getInt("Features.AttackEffect.Centering.OffsetY");
                                double zCenter = location.getZ() + getInstance().getConfig().getInt("Features.AttackEffect.Centering.OffsetZ");
                                loglogger.Debug(String.format("[事件监听器][AEL]已获取坐标中心, [%s,%s,%s]", xCenter, yCenter, zCenter));
                                Random random = new Random();
                                double xRandom = ((double) random.nextInt(getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.X.Range")) + getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.X.Add")) / getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.X.Divide");
                                double yRandom = ((double) random.nextInt(getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Y.Range")) + getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Y.Add")) / getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Y.Divide");
                                double zRandom = ((double) random.nextInt(getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Z.Range")) + getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Z.Add")) / getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Z.Divide");
                                loglogger.Debug(String.format("[事件监听器][AEL]随机已生成, [%s,%s,%s]", xRandom, yRandom, zRandom));
                                xCenter = xCenter + xRandom;
                                yCenter = yCenter + yRandom;
                                zCenter = zCenter + zRandom;
                                loglogger.Debug(String.format("[事件监听器][AEL]随机坐标已保存, [%s,%s,%s]", xCenter, yCenter, zCenter));
                                ParticleEffect.HEART.send(Bukkit.getOnlinePlayers(), xCenter, yCenter, zCenter, 0, 0, 0, 10, amount, getInstance().getConfig().getInt("Features.AttackEffect.VisibleRange"));
                                loglogger.Debug("[事件监听器][AEL]已发送");
                            }
                        }
                        else
                        {
                            loglogger.Debug("[事件监听器][AEL]事件已退出, 心心数量小于最小");
                        }
                    }
                    else
                    {
                        loglogger.Debug("[事件监听器][AEL]事件已退出, 心心数量大于最大");
                    }
                }
            }
            else
            {
                loglogger.Debug("[事件监听器][AEL]事件已退出, 攻击者不是玩家");
            }
        }
    }
}
