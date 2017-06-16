package cc.moecraft.hykilpikonna.lores.Utils;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.LoreUtils.readLore;
import static cc.moecraft.hykilpikonna.lores.Utils.VersionUtils.getAllVersionItemInHand;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ItemUtils
{

    public static boolean isNull(ItemStack itemStack, Player player)
    {
        if (itemStack == null)
        {
            player.sendMessage("错误:您手中并没有物品");
        }
        return itemStack == null;
    }

    /**
     * 获取一个玩家所有的Lore (一共5个)
     * @param player 玩家
     * @return 他的所有的Lore
     */
    public static ArrayList<ArrayList<String>> getAllItemLores(Player player)
    {
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        try
        {
            output.add(readLore((ArrayList<String>) player.getInventory().getHelmet().getItemMeta().getLore()));
            loglogger.Debug("[获取物品]检测到头盔, 已添加");
        }
        catch (NullPointerException e)
        {
            loglogger.Debug("[获取物品]未检测到头盔, 已无视");
        }
        output.add(readLore((ArrayList<String>) player.getInventory().getChestplate().getItemMeta().getLore()));
        output.add(readLore((ArrayList<String>) player.getInventory().getLeggings().getItemMeta().getLore()));
        output.add(readLore((ArrayList<String>) player.getInventory().getBoots().getItemMeta().getLore()));
        output.add(readLore((ArrayList<String>) getAllVersionItemInHand(player).getItemMeta().getLore()));
        return output;
    }
}
