package cc.moecraft.hykilpikonna.lores.Features;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class SetNameAndLore
{
    /**
     * 设置一个ItemStack的物品显示名
     * @param itemStack 物品
     * @param name 新的显示名
     * @return 新的物品
     */
    public static ItemStack setName(ItemStack itemStack, String name)
    {
        itemStack.getItemMeta().setDisplayName(name);
        return itemStack;
    }

    /**
     * 设置一个物品的Lore
     * @param itemStack 物品
     * @param lore 新的Lore
     * @return 新的物品
     */
    public static ItemStack setLoreArrayList(ItemStack itemStack, ArrayList<String> lore)
    {
        itemStack.getItemMeta().setLore(lore);
        return itemStack;
    }

    /**
     * 给一个物品的Lore加一行
     * @param itemStack 物品
     * @param lore lore
     * @return 新的物品
     */
    public static ItemStack addLore(ItemStack itemStack, String lore)
    {
        ArrayList<String> tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        tempAL.add(lore);
        itemStack.getItemMeta().setLore(tempAL);
        return itemStack;
    }

    /**
     * 给一个物品删除Lore的一行
     * @param itemStack 物品
     * @param index 行数
     * @return 新的物品
     */
    public static ItemStack removeLore(ItemStack itemStack, int index)
    {
        ArrayList<String> lore = (ArrayList<String>) itemStack.getItemMeta().getLore();
        lore.remove(index);
        itemStack.getItemMeta().setLore(lore);
        return itemStack;
    }

    /**
     * 设置一个物品的Lore的某一行
     * @param itemStack 物品
     * @param index 某一行
     * @param lore 新的Lore
     * @return 新的物品
     */
    public static ItemStack setLore(ItemStack itemStack, int index, String lore)
    {
        ArrayList<String> tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        tempAL.set(index, lore);
        itemStack.getItemMeta().setLore(tempAL);
        return itemStack;
    }

    /**
     * 插入Lore到物品的某一行
     * @param itemStack 物品
     * @param index 行数
     * @param lore 要插入的Lore
     * @return 插入后的物品
     */
    public static ItemStack insertLore(ItemStack itemStack, int index, String lore)
    {
        ArrayList<String> tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        tempAL.add(index, lore);
        itemStack.getItemMeta().setLore(tempAL);
        return itemStack;
    }
}
