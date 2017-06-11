/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.moecraft.hykilpikonna.lores.Listeners.Items;

import cc.moecraft.kelores.KELores;
import static cc.moecraft.kelores.KELores.swichLore;
import cc.moecraft.kelores.Message;
import static cc.moecraft.kelores.effects.PlayEffectOnPlayer.playEffectOnPlayer;
import cc.moecraft.kelores.perm;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import static org.bukkit.entity.EntityType.ARMOR_STAND;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Kilpikonna
 */
public class SwitchBowListener
{
    public static void SwichBowEventRunner(EntityDamageByEntityEvent e)
    {
        if (e.getEntity() instanceof LivingEntity)
        {
            LivingEntity m = (LivingEntity) e.getEntity();
            if (m.getType() == ARMOR_STAND){
                return;
            }
            double healthm = m.getHealth();
            if(KELores.enablesb)
            {
                if (e.getDamager() instanceof Arrow)
                {
                    Arrow arrow = (Arrow) e.getDamager();
                    if (arrow.getShooter() instanceof Player)
                    {
                        Player s = (Player) arrow.getShooter();
                        if (s.hasPermission(perm.get("sb", "use")))
                        {
                            ItemStack itemInHand = s.getInventory().getItemInMainHand();
                            if (itemInHand.hasItemMeta())
                            {
                                if (itemInHand.getItemMeta().getLore() != null)
                                {
                                    if (itemInHand.getItemMeta().getLore().containsAll(swichLore))
                                    {
                                        if (KELores.swichWorlds.contains(s.getWorld().getName()) || s.hasPermission("ke.sb.ignoreworld"))
                                        {
                                            e.getDamager().remove();
                                            Location S = (Location) s.getLocation();
                                            Location M = (Location) m.getLocation();
                                            s.damage(1.0);
                                            if (healthm == m.getHealth())
                                            {
                                                m.damage(7.0);
                                            }
                                            s.teleport(M.setDirection(M.getDirection()));
                                            m.teleport(S.setDirection(S.getDirection()));
                                            playEffectOnPlayer(s);
                                            if (m instanceof Player){
                                                Player mp = ((Player) m).getPlayer();
                                                playEffectOnPlayer(mp);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            Message.noPermMsg(s, perm.get("sb", "use"));
                        }
                    }
                }
            }
        }
    }
}
