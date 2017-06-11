package cc.moecraft.hykilpikonna.lores.Listeners.Items;

import cc.moecraft.kelores.KELores;
import static cc.moecraft.kelores.KELores.MultiHand;
import static cc.moecraft.kelores.KELores.bpLore;
import static cc.moecraft.kelores.KELores.bpWastDurability;
import static cc.moecraft.kelores.KELores.enablebp;
import static cc.moecraft.kelores.perm.get;
import static cc.moecraft.kelores.perm.det;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Kilpikonna
 */
public class BigPickaxeListener
{
    public static void onBlockBreakRunner(BlockBreakEvent e)
    {
        if (!e.isCancelled())
        {
            if (enablebp)
            {
                Player s = e.getPlayer();
                if ("bp".equals(detItemString(s)))
                {
                    if ("Main".equals(detHand(s)))
                    {
                        s.getInventory().getItemInMainHand().setDurability((short) (s.getInventory().getItemInMainHand().getDurability() - bpWastDurability));
                        if (s.getInventory().getItemInMainHand().getDurability() > 0)
                        {
                            s.getInventory().removeItem(s.getInventory().getItemInMainHand());
                        }
                    } else {
                        s.getInventory().getItemInOffHand().setDurability((short) (s.getInventory().getItemInOffHand().getDurability() - bpWastDurability));
                        if (s.getInventory().getItemInOffHand().getDurability() > 0)
                        {
                            s.getInventory().removeItem(s.getInventory().getItemInOffHand());
                        }
                    }
                    //s.saveData();
                    if (det(s, get("bp", "use")))
                    {
                        int Radius = KELores.bpRadius;
                        Block mainblock = e.getBlock();
                        World world = mainblock.getWorld();
                        int bx = mainblock.getX();
                        int by = mainblock.getY();
                        int bz = mainblock.getZ();
                        if (s.isOp() && KELores.bpop)
                        {
                            Radius = KELores.bpopr;
                        }
                        if (KELores.bpWorlds.contains(world.getName()))
                        {
                            for (int fx = - Radius; fx <= Radius; fx++)
                            {
                                for (int fy = - Radius; fy <= Radius; fy++)
                                {
                                    for (int fz = - Radius; fz <= Radius; fz++)
                                    {
                                        Block b = world.getBlockAt(bx + fx, by + fy, bz + fz);
                                        switch (b.getType()) {
                                            case BEDROCK:
                                                if (KELores.bpbedr)
                                                {
                                                    world.getBlockAt(bx + fx, by + fy, bz + fz).setType(Material.AIR);
                                                }   break;
                                            case BARRIER:
                                                if (KELores.bpbarr)
                                                {
                                                    world.getBlockAt(bx + fx, by + fy, bz + fz).setType(Material.AIR);
                                                }   break;
                                            default:
                                                b.getLocation().getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
                                                b.breakNaturally();
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
    
    private static String detHand(Player s) 
    {
        if (!MultiHand)
        {
            return "Main";
        } else {
            String temp = detItemLore(s.getInventory().getItemInMainHand());
            if (temp.equals("false"))
            {
                return "Off";
            } else {
                return "Main";
            }
        }
    }
    
    private static String detItemLore(ItemStack i) 
    {
        if (i.hasItemMeta())
        {
            if (i.getItemMeta().hasLore())
            {
                if (i.getItemMeta().getLore().containsAll(bpLore))
                {
                    return "bp";
                } else {
                    return "false";
                }
            }
        }
        return "false";
    }
}
