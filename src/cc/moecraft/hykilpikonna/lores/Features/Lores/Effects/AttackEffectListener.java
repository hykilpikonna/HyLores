package cc.moecraft.hykilpikonna.lores.Features.Lores.Effects;

import cc.moecraft.hykilpikonna.lores.HyLores;
import me.fromgate.playeffect.VisualEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.inventivetalent.particle.ParticleEffect;

import java.util.Random;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.EventUtils.getEntityDamageByEntityEventPlayerDamager;
import static me.fromgate.playeffect.PlayEffect.play;

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
        if (HyLores.getInstance().getConfig().getBoolean("Features.AttackEffect.Enable"))
        {
            if (event.getEntity() instanceof LivingEntity)
            {
                //获取玩家
                Player player = getEntityDamageByEntityEventPlayerDamager(event);
                if (player == null) return;

                if (Math.round(event.getDamage()) > 0)
                {
                    Location location = event.getEntity().getLocation();
                    int amount = (int) Math.round(event.getDamage());
                    if (amount <= getInstance().getConfig().getInt("Features.AttackEffect.Amount.Maximum"))
                    {
                        if (amount >= getInstance().getConfig().getInt("Features.AttackEffect.Amount.Minimum"))
                        {
                            amount = amount * getInstance().getConfig().getInt("Features.AttackEffect.Amount.Multiplier");
                            for (int i = 0; i < amount; i++)
                            {
                                double xCenter = location.getX() + getInstance().getConfig().getInt("Features.AttackEffect.Centering.OffsetX");
                                double yCenter = location.getY() + getInstance().getConfig().getInt("Features.AttackEffect.Centering.OffsetY");
                                double zCenter = location.getZ() + getInstance().getConfig().getInt("Features.AttackEffect.Centering.OffsetZ");
                                Random random = new Random();
                                double xRandom = ((double) random.nextInt(getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.X.Range")) + getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.X.Add")) / getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.X.Divide");
                                double yRandom = ((double) random.nextInt(getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Y.Range")) + getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Y.Add")) / getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Y.Divide");
                                double zRandom = ((double) random.nextInt(getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Z.Range")) + getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Z.Add")) / getInstance().getConfig().getInt("Features.AttackEffect.Randomizing.Z.Divide");
                                xCenter = xCenter + xRandom;
                                yCenter = yCenter + yRandom;
                                zCenter = zCenter + zRandom;
                                if (getInstance().getConfig().getBoolean("API.UsePlayEffectAPIInsteadOfParticleLib"))
                                {
                                    Location tempL = event.getEntity().getLocation();
                                    tempL.setX(xCenter);
                                    tempL.setY(yCenter);
                                    tempL.setZ(zCenter);
                                    play(VisualEffect.HEART, tempL, "num:1");
                                }
                                else
                                {
                                    ParticleEffect.HEART.send(Bukkit.getOnlinePlayers(), xCenter, yCenter, zCenter, 0, 0, 0, 10, 1, getInstance().getConfig().getInt("Features.AttackEffect.VisibleRange"));
                                    loglogger.Debug("[事件监听器][AEL]已通过ParticleLIB发送");
                                }
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
        }
    }
}
