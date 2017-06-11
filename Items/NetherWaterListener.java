package cc.moecraft.hykilpikonna.lores.Listeners.Items;

import cc.moecraft.kelores.KELores;
import static cc.moecraft.kelores.KELores.MultiHand;
import static cc.moecraft.kelores.KELores.nLore;
import static cc.moecraft.kelores.KELores.nOnlyNether;
import static cc.moecraft.kelores.KELores.nWorlds;
import cc.moecraft.kelores.MessageLogger;
import static cc.moecraft.kelores.MessageLogger.Debug;
import static cc.moecraft.kelores.perm.det;
import static cc.moecraft.kelores.perm.get;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import static cc.moecraft.kelores.perm.det;

/**
 * @author Kilpikonna
 */
public class NetherWaterListener
{
    public static void PlayerInteractListener(PlayerInteractEvent e)
    {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) 
        {
            if (e.getItem() != null) 
            {
                ItemStack item = e.getItem();
                World w = e.getClickedBlock().getWorld();
                Player p = e.getPlayer();
                Block b = e.getClickedBlock().getRelative(e.getBlockFace());
                if (item.getType() == Material.WATER_BUCKET)
                {
                    if (detItem(p, item))
                    {
                        if (det(p, get("n", "use") + "." + w.getName()) || det(p, get("n", "use") + ".*"))
                        {
                            if (nWorlds.contains(w.getName()) || (p.hasPermission("ke.nw.bypassworld")))
                            {
                                if (w.getEnvironment() == World.Environment.NETHER || !nOnlyNether)
                                {
                                    Debug("w.getEnvironment() == World.Environment.NETHER" + (w.getEnvironment() == World.Environment.NETHER));
                                    Debug("!nOnlyNether" + !nOnlyNether);
                                    e.setCancelled(true);
                                    //Location l = b.getLocation();
                                    b.setType(Material.WATER);
                                    BlockPlaceEvent event = new BlockPlaceEvent(b, b.getState(), e.getClickedBlock(), item, p, false, EquipmentSlot.HAND);
                                    if (event.isCancelled())
                                    {
                                        b.setType(Material.AIR);
                                    } else {
                                        item.setType(Material.BUCKET);
                                    }
                                } else {
                                    e.setCancelled(true);
                                    p.sendMessage(ChatColor.GOLD + "地狱水只能用在地狱哦!");
                                }
                            }
                        }
                    }
                }
            }
        }
                    //if (this.plugin.canBuild(p, e.getClickedBlock().getRelative(e.getBlockFace())))
                    //if ((y <= this.plugin.getConfig().getInt("maxHeight")) && (y >= this.plugin.getConfig().getInt("minHeight")))
    }
    
    private static boolean detItem(Player s, ItemStack i) 
    {
        if (!MultiHand)
        {
            return detItemLore(i);
        } else {
            boolean temp = detItemLore(i);
            if (!temp)
            {
                return detItemLore(i);
            } else {
                return temp;
            }
        }
    }
    
    private static Boolean detItemLore(ItemStack i) 
    {
        if (i.hasItemMeta())
        {
            if (i.getItemMeta().hasLore())
            {
                return i.getItemMeta().getLore().containsAll(nLore);
            }
        }
        return false;
    }
}
