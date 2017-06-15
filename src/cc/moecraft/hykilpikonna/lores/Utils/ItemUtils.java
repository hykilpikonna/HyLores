package cc.moecraft.hykilpikonna.lores.Utils;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
}
