package cc.moecraft.hykilpikonna.lores.Utils;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.ArrayUtils.readLore;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeSpace;
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
    public static ArrayList<ArrayList<String>> getAllArmorLores(Player player)
    {
        ArrayList<ArrayList<String>> output = new ArrayList<>();

        //头盔
        try
        {
            output.add(readLore((ArrayList<String>) player.getInventory().getHelmet().getItemMeta().getLore()));
            loglogger.Debug("[获取物品]检测到头盔, 已添加");
        }
        catch (NullPointerException e)
        {
            loglogger.Debug("[获取物品]未检测到头盔, 已无视");
        }

        //胸甲
        try
        {
            output.add(readLore((ArrayList<String>) player.getInventory().getChestplate().getItemMeta().getLore()));
            loglogger.Debug("[获取物品]检测到胸甲, 已添加");
        }
        catch (NullPointerException e)
        {
            loglogger.Debug("[获取物品]未检测到胸甲, 已无视");
        }

        //护腿
        try
        {
            output.add(readLore((ArrayList<String>) player.getInventory().getLeggings().getItemMeta().getLore()));
            loglogger.Debug("[获取物品]检测到护腿, 已添加");
        }
        catch (NullPointerException e)
        {
            loglogger.Debug("[获取物品]未检测到护腿, 已无视");
        }

        //鞋子
        try
        {
            output.add(readLore((ArrayList<String>) player.getInventory().getBoots().getItemMeta().getLore()));
            loglogger.Debug("[获取物品]检测到鞋子, 已添加");
        }
        catch (NullPointerException e)
        {
            loglogger.Debug("[获取物品]未检测到鞋子, 已无视");
        }

        //手上物品
        try
        {
            output.add(readLore((ArrayList<String>) getAllVersionItemInHand(player).getItemMeta().getLore()));
            loglogger.Debug("[获取物品]检测到手上物品, 已添加");
        }
        catch (NullPointerException e)
        {
            loglogger.Debug("[获取物品]未检测到手上物品, 已无视");
        }

        for (int i = 0; i < output.size(); i++)
        {
            output.set(i, removeSpace(removeColorCode(output.get(i))));
        }

        return output;
    }
}
