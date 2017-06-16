package cc.moecraft.hykilpikonna.lores.Features.Command;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class SetNameAndLore
{
    private static String prefix = getInstance().getConfig().getString("Lore.Prefix");

    /**
     * 设置一个ItemStack的物品显示名
     * @param itemStack 物品
     * @param name 新的显示名
     * @return 新的物品
     */
    public static ItemMeta setName(ItemStack itemStack, String name)
    {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        return itemMeta;
    }

    /**
     * 设置一个物品的Lore
     * @param itemStack 物品
     * @param lore 新的Lore
     * @return 新的物品
     */
    public static ItemMeta setLoreArrayList(ItemStack itemStack, ArrayList<String> lore)
    {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        return itemMeta;
    }

    /**
     * 给一个物品的Lore加一行
     * @param itemStack 物品
     * @param lore lore
     * @return 新的物品
     */
    public static ItemMeta addLore(ItemStack itemStack, String lore)
    {
        ArrayList<String> tempAL;
        if ((ArrayList<String>) itemStack.getItemMeta().getLore() != null)
        {
            tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        }
        else
        {
            tempAL = new ArrayList<>();
        }
        tempAL.add(prefix + lore);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(tempAL);
        return itemMeta;
    }

    /**
     * 给一个物品删除Lore的一行
     * @param itemStack 物品
     * @param index 行数
     * @return 新的物品
     */
    public static ItemMeta removeLore(ItemStack itemStack, int index)
    {
        ArrayList<String> tempAL;
        if ((ArrayList<String>) itemStack.getItemMeta().getLore() != null)
        {
            tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        }
        else
        {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(new ArrayList<String>());
            return itemMeta;
        }
        tempAL.remove(index);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(tempAL);
        return itemMeta;
    }

    /**
     * 设置一个物品的Lore的某一行
     * @param itemStack 物品
     * @param index 某一行
     * @param lore 新的Lore
     * @return 新的物品
     */
    public static ItemMeta setLore(ItemStack itemStack, int index, String lore)
    {
        ArrayList<String> tempAL;
        if ((ArrayList<String>) itemStack.getItemMeta().getLore() != null)
        {
            tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        }
        else
        {
            tempAL = new ArrayList<>();
        }
        tempAL.set(index, prefix + lore);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(tempAL);
        return itemMeta;
    }

    /**
     * 插入Lore到物品的某一行
     * @param itemStack 物品
     * @param index 行数
     * @param lore 要插入的Lore
     * @return 插入后的物品
     */
    public static ItemMeta insertLore(ItemStack itemStack, int index, String lore)
    {
        ArrayList<String> tempAL;
        if ((ArrayList<String>) itemStack.getItemMeta().getLore() != null)
        {
            tempAL = (ArrayList<String>) itemStack.getItemMeta().getLore();
        }
        else
        {
            tempAL = new ArrayList<>();
        }
        tempAL.add(index, prefix + lore);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(tempAL);
        return itemMeta;
    }
}
