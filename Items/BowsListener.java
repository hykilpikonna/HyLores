package cc.moecraft.hykilpikonna.lores.Listeners.Items;

import cc.moecraft.kelores.KELores;
import static cc.moecraft.kelores.KELores.MultiHand;
import static cc.moecraft.kelores.KELores.eLore;
import static cc.moecraft.kelores.KELores.ePower;
import static cc.moecraft.kelores.KELores.eWorlds;
import static cc.moecraft.kelores.KELores.enablee;
import static cc.moecraft.kelores.KELores.enablelb;
import static cc.moecraft.kelores.KELores.enabletb;
import static cc.moecraft.kelores.KELores.exOffset;
import static cc.moecraft.kelores.KELores.eyOffset;
import static cc.moecraft.kelores.KELores.ezOffset;
import static cc.moecraft.kelores.KELores.lightLore;
import static cc.moecraft.kelores.KELores.realExplosion;
import static cc.moecraft.kelores.KELores.setFire;
import static cc.moecraft.kelores.KELores.tpLore;
import static cc.moecraft.kelores.Message.noPermMsg;
import static cc.moecraft.kelores.effects.PlayEffectOnPlayer.playEffectOnPlayer;
import cc.moecraft.kelores.perm;
import static cc.moecraft.kelores.perm.det;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Kilpikonna
 */
public class BowsListener
{
    public static void TpLbBowEvent(ProjectileHitEvent e)
    {
        if (e.getEntity() instanceof Arrow)
        {
            Arrow a = (Arrow)e.getEntity();
            if (a.getShooter() instanceof Player)
            {
                Player s = (Player)a.getShooter();
                if (detItem(s))
                {
                    if (null != detItemString(s))
                    {
                        switch (detItemString(s)) {
                            case "tb":
                                if (enabletb)
                                {
                                    if (det(s, "tb", "use"))
                                    {
                                        if (KELores.tpWorlds.contains(s.getWorld().getName()) || s.hasPermission("ke.tb.ignoreworld"))
                                        {
                                            e.getEntity().remove();
                                            s.teleport(a.getLocation().setDirection(s.getLocation().getDirection()));
                                            playEffectOnPlayer(s);
                                        }
                                    }
                                }   break;
                            case "lb":
                                if (enablelb)
                                {
                                    if (det(s, "lb", "use"))
                                    {
                                        if (KELores.lightWorlds.contains(s.getWorld().getName()) || s.hasPermission("ke.lb.ignoreworld"))
                                        {
                                            e.getEntity().remove();
                                            if (KELores.realLightning)
                                            {
                                                a.getWorld().strikeLightning(a.getLocation());
                                            } else {
                                                a.getWorld().strikeLightningEffect(a.getLocation());
                                            }
                                            playEffectOnPlayer(s);
                                        }
                                    } else {
                                        noPermMsg(s, perm.get("lb", "use"));
                                    }
                                }   break;
                            case "eb":
                                if (enablee)
                                {
                                    if (s.hasPermission(perm.get("e", "use")))
                                    {
                                        if (eWorlds.contains(s.getWorld().getName()) || s.hasPermission("ke.ignoreworld"))
                                        {
                                            e.getEntity().remove();
                                            Location loc = a.getLocation();
                                            double x = loc.getX() + exOffset, y = loc.getY() + eyOffset, z = loc.getZ() + ezOffset;
                                            a.getWorld().createExplosion(x, y, z, ePower.get(0), setFire, realExplosion);
                                            playEffectOnPlayer(s);
                                        }
                                    } else {
                                        noPermMsg(s, perm.get("e", "use"));
                                    }
                                }   break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    private static boolean detItem(Player s) 
    {
        if (!MultiHand)
        {
            String temp = detItemLore(s.getInventory().getItemInMainHand());
            return !temp.equals("false");
        } else {
            String temp = detItemLore(s.getInventory().getItemInMainHand());
            if (temp.equals("false"))
            {
                temp = detItemLore(s.getInventory().getItemInOffHand());
                return !temp.equals("false");
            } else {
                return true;
            }
        }
    }
    
    private static String detItemString(Player s) 
    {
        if (!MultiHand)
        {
            return detItemLore(s.getInventory().getItemInMainHand());
        } else {
            String temp = detItemLore(s.getInventory().getItemInMainHand());
            if (temp.equals("false"))
            {
                return detItemLore(s.getInventory().getItemInOffHand());
            } else {
                return temp;
            }
        }
    }
    
    private static String detItemLore(ItemStack i) 
    {
        if (i.hasItemMeta())
        {
            if (i.getItemMeta().hasLore())
            {
                if (i.getItemMeta().getLore().containsAll(tpLore))
                {
                    return "tb";
                } else if (i.getItemMeta().getLore().containsAll(lightLore)) {
                    return "lb";
                } else if (i.getItemMeta().getLore().containsAll(eLore)) {
                    return "eb";
                } else {
                    return "false";
                }
            }
        }
        return "false";
    }
}
