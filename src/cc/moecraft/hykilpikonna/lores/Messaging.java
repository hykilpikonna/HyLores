package cc.moecraft.hykilpikonna.lores;

import org.bukkit.entity.Player;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Permission.hasPermission;
import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/06/11 创建!
 * Created by Hykilpikonna on 2017/06/11!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Messaging
{
    /**
     * 判断权限并给玩家发送一条消息
     * @param player 玩家
     * @param message 消息
     */
    public static void sendMessage(Player player, String message)
    {
        //判断权限
        if (hasPermission(player, "ReceiveMessage"))
        {
            sendMessageDirect(player, message);
        }
    }

    /**
     * 直接向玩家发送消息
     * @param player 玩家
     * @param message 消息
     */
    public static void sendMessageDirect(Player player, String message)
    {
        //发送消息
        player.sendMessage(getInstance().getConfig().getString("Messaging.Prefix") + message);
    }


    /**
     * 发送没有权限的消息
     * @param player 玩家
     * @param permission 权限节点
     */
    public static void sendNoPermissionMessage(Player player, String permission)
    {
        player.sendMessage(getInstance().getConfig().getString("Messaging.Prefix") + RED + "您没有权限接收次消息!");
        player.sendMessage(getInstance().getConfig().getString("Messaging.Prefix") + RED + String.format("缺少的权限: %s", permission));
    }

    /**
     * 发送帮助消息
     * @param player 玩家
     */
    public static void sendHelpMessage(Player player)
    {
        loglogger.Debug(String.format("[消息]正在给玩家%s发送帮助消息", player.getName()));
        //判断权限
        if (hasPermission(player, "ReceiveHelpMessage"))
        {
            //发送消息
            sendMessageDirect(player, GRAY + "###############" + GOLD + "Hy" + LIGHT_PURPLE + "Lores" + GRAY + "###############");
            sendMessageDirect(player, GRAY + "#" + AQUA + "帮助: " + BLUE + BOLD + "/hyl help                    #");
            sendMessageDirect(player, GRAY + "#" + AQUA + "重载: " + BLUE + BOLD + "/hyl reload                  #");
            sendMessageDirect(player, GRAY + "#                                   #");
            sendMessageDirect(player, GRAY + "#####################################");
        }
    }
}
