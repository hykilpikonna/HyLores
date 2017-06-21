package cc.moecraft.hykilpikonna.lores.Features.Command;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendHelpMessage;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendMessage;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendMessageDirect;
import static cc.moecraft.hykilpikonna.lores.Permission.hasPermission;
import static cc.moecraft.hykilpikonna.lores.Utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.isNumeric;
import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/06/21 创建!
 * Created by Hykilpikonna on 2017/06/21!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class GetHead
{
    /**
     * 获取玩家头颅
     * @param playerName 玩家名
     * @param amount 数量
     * @return 头颅物品ItemStack
     */
    public static ItemStack getHead(String playerName, int amount)
    {
        loglogger.Debug(String.format("Amount = %s, PlayerName = %s", amount, playerName));
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(playerName);
        if (getInstance().getConfig().getBoolean("Features.Item.ChangeDisplayName"))
        {
            meta.setDisplayName(String.format(getInstance().getConfig().getString("Features.Item.DisplayName"), playerName));
        }
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 玩家用指令获取玩家的头
     * @param commandSender 发送指令的玩家
     * @param amount 头的数量
     * @param playerName 头的玩家名
     */
    public static void commandGetHead(Player commandSender, int amount, String playerName)
    {
        if (hasPermission(commandSender, "Command.head.get"))
        {
            sendMessage(commandSender, String.format("%s您成功获取%s个%s%s%s%s的头颅", GREEN, amount, AQUA, BOLD, playerName, GREEN));
            commandSender.getInventory().addItem(getHead(playerName, amount));
            commandSender.updateInventory();
        }
    }

    /**
     * 玩家用指令获取玩家的头
     * @param commandSender 发送指令的玩家
     * @param args 指令
     */
    public static void commandGetHead(Player commandSender, String[] args)
    {
        int amount;
        if (isNumeric(args[1]))
        {
            amount = Integer.parseInt(args[1]);
        }
        else
        {
            sendMessage(commandSender, RED + "指令错误");
            sendHelpMessage(commandSender);
            return;
        }
        commandGetHead(commandSender, amount, getTheRestToString(args, 2));
    }
}
