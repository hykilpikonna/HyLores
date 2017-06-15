package cc.moecraft.hykilpikonna.lores;

import org.bukkit.entity.Player;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendNoPermissionMessage;

/**
 * 此类由 Hykilpikonna 在 2017/06/11 创建!
 * Created by Hykilpikonna on 2017/06/11!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Permission
{
    /**
     * 检测一个玩家是否拥有一个指定的权限, 或者这个指定的权限未启用
     * @param player 玩家
     * @param configNode 配置中的权限点
     * @return 是否拥有权限
     */
    public static boolean hasPermission(Player player, String configNode)
    {
        //检测是否需要权限
        if (getInstance().getConfig().getBoolean("Permissions." + configNode + ".Require"))
        {
            //如果玩家没有权限
            if (!player.hasPermission(getInstance().getConfig().getString("Permissions." + configNode + ".Node")))
            {
                //给玩家发送没有权限的消息
                sendNoPermissionMessage(player, getInstance().getConfig().getString("Permissions." + configNode + ".Node"));
                //返回假
                return false;
            }
        }
        //其他情况返回真
        return true;
    }
}
