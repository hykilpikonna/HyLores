package cc.moecraft.hykilpikonna.lores.Features.Lores.Buff;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Permission.hasPermission;
import static cc.moecraft.hykilpikonna.lores.Permission.hasPermissionNoMsg;
import static cc.moecraft.hykilpikonna.lores.Utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.lores.Utils.EventUtils.getEntityDamageByEntityEventPlayerDamager;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeSpace;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/06/25 创建!
 * Created by Hykilpikonna on 2017/06/25!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class LootingListener implements Listener
{
    public LootingListener()
    {
        loglogger.Debug("[事件监听器][注册]LootingListener已经注册监听");
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("HyLores"));
    }

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event)
    {
        loglogger.Debug("1");
        if (getInstance().getConfig().getBoolean("Lore.Buffs.Looting.Enable"))
        {
            loglogger.Debug("2");
            Player damager = getEntityDamageByEntityEventPlayerDamager(event);
            if (event.getEntity() instanceof Player && damager != null)
            {
                loglogger.Debug("3");
                Player player = (Player) event.getEntity();
                loglogger.Debug(player.getName());
                loglogger.Debug(damager.getName());
                if (!player.equals(damager))
                {
                    loglogger.Debug("4");
                    loglogger.Debug("Player.getHealth = " + player.getHealth());
                    if (player.getHealth() - event.getDamage() <= 0 || player.isDead())
                    {
                        loglogger.Debug("5");
                        if (!(hasPermission(damager, "Buff.Looting")))
                        {
                            loglogger.Debug("!6(1)");
                            return;
                        }
                        /*if (hasPermissionNoMsg(player, "Buff.Looting.Bypass"))
                        {
                            loglogger.Debug("!6(2)");
                            return;
                        }*/

                        loglogger.Debug("6");
                        ArrayList<ItemStack> playerItemList = getAllItemListWitoutAntiLooting(player);
                        loglogger.Debug("PlayerItemList.size = " + playerItemList.size());
                        ArrayList<Integer> platerItemSlotList = getAllItemListWitoutAntiLootingInInt(player);
                        loglogger.Debug("SlotList.size = " + platerItemSlotList.size());
                        Integer loot;
                        if (getInstance().getConfig().getBoolean("Lore.Buffs.Looting.WithoutLore.Enable"))
                        {
                            loglogger.Debug("7(1)");
                            loot = getInstance().getConfig().getInt("Lore.Buffs.Looting.WithoutLore.DefaultItems");
                        }
                        else if (hasLooting(damager))
                        {
                            loglogger.Debug("7(2)");
                            loot = getLooting(damager);
                        }
                        else
                        {
                            loglogger.Debug("!7");
                            return;
                        }
                        loglogger.Debug("8");

                        if (loot > getEmptyBlocks(damager))
                        {
                            loglogger.Debug("8.5");
                            loot = getEmptyBlocks(damager);
                        }
                        if (loot > playerItemList.size())
                        {
                            loglogger.Debug("8.7");
                            loot = platerItemSlotList.size();
                        }
                        loglogger.Debug("Loot = " + loot);
                        for (int i = 0; i < loot; i++)
                        {
                            loglogger.Debug("9");
                            Random r = new Random();
                            int randomNumber = r.nextInt(playerItemList.size());
                            loglogger.Debug("Random = " + randomNumber);
                            loglogger.Debug("PlayerItemList.Size = " + randomNumber);
                            player.getInventory().clear(platerItemSlotList.get(randomNumber));
                            damager.getInventory().addItem(playerItemList.get(randomNumber));
                            loglogger.Debug(String.format("第%s个物品已被拿走, 并从物品列表清除, 物品名: %s", randomNumber, playerItemList.get(randomNumber).getItemMeta().getDisplayName()));
                            platerItemSlotList.remove(randomNumber);
                            playerItemList.remove(randomNumber);
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<ItemStack> getAllItemListWitoutAntiLooting(Player player)
    {
        loglogger.Debug("开始获取玩家物品列表");
        String keyWord = getInstance().getConfig().getString("Lore.Buffs.AntiLooting.KeyWord");
        loglogger.Debug(String.format("KeyWord = %s", keyWord));
        ArrayList<ItemStack> output = new ArrayList<>();
        for (int i = 0; i <= 36; i++)
        {
            if (player.getInventory().getItem(i) != null)
            {
                loglogger.Debug(String.format("玩家的第%s个物品不是空的", i));
                Boolean aSwitch = true;
                ArrayList<String> lore = (ArrayList<String>) player.getInventory().getItem(i).getItemMeta().getLore();

                if (player.getInventory().getItem(i).getItemMeta().getLore() != null)
                    for (String aLore : lore)
                    {
                        String one = removeSpace(removeColorCode(aLore));
                        if (one.contains(keyWord))
                        {
                            loglogger.Debug(String.format("玩家的第%s个物品未被记录, 因为包含AntiL, aLore = %s", i, aLore));
                            aSwitch = false;
                            break;
                        }
                    }
                if (aSwitch)
                {
                    loglogger.Debug(String.format("玩家的第%s个物品已被记录", i));
                    output.add(player.getInventory().getItem(i));
                }
            }
        }
        return output;
    }

    public static ArrayList<Integer> getAllItemListWitoutAntiLootingInInt(Player player)
    {
        ArrayList<Integer> output = new ArrayList<>();
        loglogger.Debug("开始获取玩家物品列表");
        String keyWord = getInstance().getConfig().getString("Lore.Buffs.AntiLooting.KeyWord");
        loglogger.Debug(String.format("KeyWord = %s", keyWord));
        for (int i = 0; i <= 36; i++)
        {
            if (player.getInventory().getItem(i) != null)
            {
                loglogger.Debug(String.format("玩家的第%s个物品不是空的", i));
                Boolean aSwitch = true;
                ArrayList<String> lore = (ArrayList<String>) player.getInventory().getItem(i).getItemMeta().getLore();

                if (player.getInventory().getItem(i).getItemMeta().getLore() != null)
                    for (String aLore : lore)
                    {
                        String one = removeSpace(removeColorCode(aLore));
                        if (one.contains(keyWord))
                        {
                            loglogger.Debug(String.format("玩家的第%s个物品未被记录, 因为包含AntiL, aLore = %s", i, aLore));
                            aSwitch = false;
                            break;
                        }
                    }
                if (aSwitch)
                {
                    loglogger.Debug(String.format("玩家的第%s个物品已被记录", i));
                    output.add(i);
                }
            }
        }
        return output;
    }

    public static boolean hasLooting(Player player)
    {
        for (int i = 0; i <= 36; i++)
        {
            if (player.getInventory().getItem(i) != null)
            {
                ArrayList<String> lore = (ArrayList<String>) player.getInventory().getItem(i).getItemMeta().getLore();
                String keyWord = getInstance().getConfig().getString("Lore.Buffs.Looting.KeyWord");
                for (String aLore : lore)
                {
                    String one = removeSpace(removeColorCode(aLore));
                    if (one.contains(keyWord))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Integer getLooting(Player player)
    {
        Integer output = 0;
        for (int i = 0; i <= 36; i++)
        {
            if (player.getInventory().getItem(i) != null)
            {
                ArrayList<String> lore = (ArrayList<String>) player.getInventory().getItem(i).getItemMeta().getLore();
                String keyWord = getInstance().getConfig().getString("Lore.Prefix") + getInstance().getConfig().getString("Lore.Buffs.Looting.KeyWord");
                if (player.getInventory().getItem(i).getItemMeta().getLore() != null)
                    for (String aLore : lore)
                    {
                        String one = removeSpace(removeColorCode(aLore));
                        if (one.contains(keyWord))
                        {
                            one = one.replace(keyWord, "");
                            if (one.length() > 0)
                            {
                                output += Integer.parseInt(getTheRestToString(one, 0));
                            }
                        }
                    }
            }
        }
        return output;
    }

    public static Integer getEmptyBlocks(Player player)
    {
        Integer output = 0;
        for (int i = 0; i <= 36; i++)
        {
            if (player.getInventory().getItem(i) == null)
            {
                output += 1;
            }
        }
        return output;
    }
}
