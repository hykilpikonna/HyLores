package cc.moecraft.hykilpikonna.lores.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyItem
{
    //是否启用
    private boolean enable;

    //物品名
    private String name;

    //物品IS
    //包含物品Material public Material material;
    //包含物品Lore public ArrayList<String> lore;
    //包含物品显示名称
    private ItemStack itemStack;

    //启用该物品的世界
    private List<String> enabledWorlds;

    //所有物品
    public ArrayList<HyItem> hyItems = new ArrayList<>();

    /**
     * 新建一个Hy物品
     * @param enable 是否启用
     * @param name 物品名
     * @param itemStack 物品IS
     * @param enabledWorlds 启用该物品的世界
     */
    public HyItem(boolean enable, String name, ItemStack itemStack, List<String> enabledWorlds)
    {
        this.enable = enable;
        this.name = name;
        this.itemStack = itemStack;
        this.enabledWorlds = enabledWorlds;
        hyItems.add(this);
        loglogger.Debug("[物品]已新建一个物品缓存!");
        loglogger.Debug(String.format("[物品][信息]物品名: %s", name));
        loglogger.Debug(String.format("[物品][信息]是否启用: %s", enable));
        loglogger.Debug(String.format("[物品][信息]物品数量: %s", itemStack.getAmount()));
        loglogger.Debug(String.format("[物品][信息]物品显示名: %s", itemStack.getItemMeta().getDisplayName()));
        loglogger.Debug(String.format("[物品][信息]物品Lore: %s", itemStack.getItemMeta().getLore().get(0)));
    }

    /**
     * 新建一个完整的Hy物品
     * @param enable 是否启用
     * @param name 物品名
     * @param material 物品ID
     * @param displayName 物品显示名
     * @param lore 物品Lore
     * @param enabledWorlds 启用该物品的世界
     */
    public HyItem(boolean enable, String name, Material material, String displayName, ArrayList<String> lore, List<String> enabledWorlds)
    {
        this.enable = enable;
        this.name = name;
        ItemStack tempIS = new ItemStack(material, 1);
        tempIS.getItemMeta().setLore(lore);
        tempIS.getItemMeta().setDisplayName(displayName);
        this.itemStack = tempIS;
        this.enabledWorlds = enabledWorlds;
        hyItems.add(this);
        loglogger.Debug("[物品]已新建一个物品缓存!");
        loglogger.Debug(String.format("[物品][信息]物品名: %s", name));
        loglogger.Debug(String.format("[物品][信息]是否启用: %s", enable));
        loglogger.Debug(String.format("[物品][信息]物品数量: %s", itemStack.getAmount()));
        loglogger.Debug(String.format("[物品][信息]物品显示名: %s", itemStack.getItemMeta().getDisplayName()));
        loglogger.Debug(String.format("[物品][信息]物品Lore: %s", itemStack.getItemMeta().getLore().get(0)));
    }

    /**
     * 设置物品是否启用
     * @param enable 是否启用
     */
    public void setEnable(boolean enable)
    {
        loglogger.Debug(String.format("Enable值已被更改, 以前: %s, 现在: %s", this.enable, enable));
        this.enable = enable;
    }

    /**
     * 设置物品名
     * @param name 物品名
     */
    public void setName(String name)
    {
        loglogger.Debug(String.format("Name值已被更改, 以前: %s, 现在: %s", this.name, name));
        this.name = name;
    }

    /**
     * 设置ItemStack
     * @param itemStack ItemStack
     */
    public void setItemStack(ItemStack itemStack)
    {
        loglogger.Debug(String.format("ItemStack值已被更改."));
        this.itemStack = itemStack;
    }

    /**
     * 设置启用它的世界列表
     * @param enabledWorlds 世界列表
     */
    public void setEnabledWorlds(List<String> enabledWorlds)
    {
        loglogger.Debug(String.format("EnabledWorlds值已被更改."));
        this.enabledWorlds = enabledWorlds;
    }

    /**
     * 设置显示名
     * @param displayName 显示名
     */
    public void setDisplayName(String displayName)
    {
        loglogger.Debug(String.format("DisplayName值已被更改, 以前: %s, 现在: %s", itemStack.getItemMeta().getDisplayName(), displayName));
        this.itemStack.getItemMeta().setDisplayName(displayName);
    }

    /**
     * 设置Lore标签
     * @param lore Lore标签
     */
    public void setLore(ArrayList<String> lore)
    {
        loglogger.Debug(String.format("Lore值已被更改, 以前: %s, 现在: %s", itemStack.getItemMeta().getLore(), lore));
        this.itemStack.getItemMeta().setLore(lore);
    }

    /**
     * 设置数量
     * @param amount 数量
     */
    public void setAmount(int amount)
    {
        loglogger.Debug(String.format("Amount值已被更改, 以前: %s, 现在: %s", itemStack.getAmount(), amount));
        this.itemStack.setAmount(amount);
    }

    /**
     * 获取是否启用
     * @return 是否启用
     */
    public boolean getEnable()
    {
        return enable;
    }

    /**
     * 获取名字
     * @return 名字
     */
    public String getName()
    {
        return name;
    }

    /**
     * 获取ItemStack
     * @return ItemStack
     */
    public ItemStack getItemStack()
    {
        return itemStack;
    }

    /**
     * 获取启用它的世界
     * @return 启用它的世界
     */
    public List<String> getEnabledWorlds()
    {
        return enabledWorlds;
    }

    /**
     * 获取显示名
     * @return 显示名
     */
    public String getDisplayName()
    {
        return itemStack.getItemMeta().getDisplayName();
    }

    /**
     * 获取Lore标签
     * @return Lore标签
     */
    public List<String> getLore()
    {
        return itemStack.getItemMeta().getLore();
    }

    /**
     * 获取数量
     * @return 数量
     */
    public int getAmount()
    {
        return itemStack.getAmount();
    }
}
