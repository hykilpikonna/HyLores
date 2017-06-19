package cc.moecraft.hykilpikonna.lores.Features.Lores.Buff;

import cc.moecraft.hykilpikonna.lores.HyLores;
import me.fromgate.playeffect.VisualEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.inventivetalent.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.Random;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.lores.Utils.EventUtils.getEntityDamageByEntityEventPlayerDamager;
import static cc.moecraft.hykilpikonna.lores.Utils.ItemUtils.getAllItemLores;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeSpace;
import static me.fromgate.playeffect.PlayEffect.play;

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

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event)
    {
        loglogger.Debug("[事件监听器][DB]事件被激发.");
        if (HyLores.getInstance().getConfig().getBoolean("Lore.Buffs.DamageBoost.Enable"))
        {
            //获取玩家
            Player player = getEntityDamageByEntityEventPlayerDamager(event);
            if (player == null) return;

            //TODO: Permission
            event.setDamage(getTotalDamage(player, event.getDamage()));
        }
    }

    public double getTotalDamage(Player player, double inputDamage)
    {
        ArrayList<ArrayList<String>> allItemLores = getAllItemLores(player);
        double totalDamage = inputDamage + 0.0;

        if (allItemLores == null || allItemLores.size() < 1)
        {
            return totalDamage;
        }

        for (int i = 0; i < allItemLores.size(); i++)
        {
            if (allItemLores.get(i) != null)
            {
                for (int j = 0; j < allItemLores.get(i).size(); j++)
                {
                    String one = removeSpace(removeColorCode(allItemLores.get(i).get(j)));
                    String keyWord = getInstance().getConfig().getString("Lore.Buffs.DamageBoost.KeyWord");
                    if (one.contains(keyWord))
                    {
                        one = one.replace(keyWord, "");
                        if (one.length() > 0)
                        {
                            loglogger.Debug("one.charAt(0) == " + one.charAt(0));
                            switch (one.charAt(0))
                            {
                                case '+':
                                    totalDamage += Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                case '-':
                                    totalDamage -= Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                case '*':
                                    totalDamage *= Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                case '/':
                                    totalDamage /= Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                default:
                                    //+但是少了一位
                                    totalDamage += Double.parseDouble(getTheRestToString(one, 0));
                                    break;
                            }
                        }
                    }
                }
            }
        }
        loglogger.Debug("[获取总伤害Buff] 已获取: " + totalDamage);
        return totalDamage;
    }


}
