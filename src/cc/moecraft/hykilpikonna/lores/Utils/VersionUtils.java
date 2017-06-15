package cc.moecraft.hykilpikonna.lores.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.isNumeric;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class VersionUtils
{
    /**
     * 获取短版本号
     * @return 短版本号
     */
    public static Double getShortVersion()
    {
        Double tempDouble;
        loglogger.Debug("getServer().getClass().getPackage().getName() = " + Bukkit.getServer().getClass().getPackage().getName());
        loglogger.Debug("name.substring(name.lastIndexOf('.') + 1) = " + Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1));
        String version = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
        if (isNumeric(version.charAt(1)))
        {
            tempDouble = Double.parseDouble(String.valueOf(version.charAt(1)));
            if (!isNumeric(version.charAt(2)))
            {
                if (isNumeric(version.charAt(3)))
                {
                    tempDouble = tempDouble + (Double.parseDouble(String.valueOf(version.charAt(2))) / 10);
                    if (isNumeric(version.charAt(4)))
                    {
                        tempDouble = tempDouble + (Double.parseDouble(String.valueOf(version.charAt(2))) / 100);
                    }
                }
            }
        }
        else
        {
            return 0.0;
        }
        loglogger.Debug("计算出的版本号为" + tempDouble);
        return tempDouble;
    }

    /**
     * 获取玩家手上物品(全版本)
     * @param player 玩家
     * @return 手上物品
     */
    public static ItemStack getAllVersionItemInHand(Player player)
    {
        if (getShortVersion() >= 1.9)
        {
            return player.getInventory().getItemInMainHand();
        }
        else
        {
            return player.getInventory().getItemInHand();
        }
    }

    /**
     * 设置玩家手上物品(全版本)
     * @param player 玩家
     * @param itemStack 手上物品
     */
    public static void setAllVersionItemInHand(Player player, ItemStack itemStack)
    {
        if (getShortVersion() >= 1.9)
        {
            player.getInventory().setItemInMainHand(itemStack);
        }
        else
        {
            player.getInventory().setItemInHand(itemStack);
        }
    }
}
