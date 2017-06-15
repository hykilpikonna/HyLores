package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.essentials.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static cc.moecraft.hykilpikonna.lores.Features.SetNameAndLore.addLore;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendHelpMessage;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendMessage;
import static cc.moecraft.hykilpikonna.lores.Permission.hasPermission;
import static cc.moecraft.hykilpikonna.lores.PluginUtil.reload;
import static cc.moecraft.hykilpikonna.lores.Setup.setup;
import static cc.moecraft.hykilpikonna.lores.Utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.lores.Utils.ItemUtils.isNull;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.convertColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.VersionUtils.getAllVersionItemInHand;
import static cc.moecraft.hykilpikonna.lores.Utils.VersionUtils.setAllVersionItemInHand;
import static cc.moecraft.hykilpikonna.lores.Features.SetNameAndLore.setName;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyLores extends JavaPlugin implements Listener
{
    public static Logger loglogger = new Logger("HyLores", true);
    private static HyLores instance = null;

    /**
     * 加载插件
     */
    public void onEnable()
    {
        loglogger.log("[加载]此插件正在加载......");
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        setup();
        saveConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        loglogger.log("[加载]此插件加载完成!");
    }

    /**
     * 卸载插件
     */
    public void onDisable()
    {
        loglogger.log("此插件已卸载!");
    }

    /**
     * 获取实例
     * @return 实例
     */
    public static HyLores getInstance()
    {
        return instance;
    }

    //TODO: Command

    /**
     * 执行指令
     * @param sender 发送指令的人
     * @param cmd 指令
     * @param label 指令标签
     * @param args 指令Arguments
     * @return 是否执行成功
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        //判断指令名
        loglogger.Debug("[指令]指令被触发!");
        if (cmd.getName().equalsIgnoreCase("hyl") || cmd.getName().equalsIgnoreCase("hylores") || cmd.getName().equalsIgnoreCase("hylore"))
        {
            loglogger.Debug("[指令]检测指令名正确");
            //判断发送者是否是玩家
            if (sender instanceof Player)
            {
                Player player = (Player) sender;
                //TODO: LanguageAPI
                loglogger.Debug("[指令]发送者是玩家");
                loglogger.Debug(String.format("[指令]指令长度为%s", args.length));
                //长度是1时第1项是0
                if (args.length == 0)
                {
                    sendHelpMessage(player);
                    return true;
                }
                switch (args[0].toLowerCase())
                {
                    case "reload":
                        loglogger.Debug("[指令]检测到指令是重载, 正在开始重载");
                        if (hasPermission(player, "Command.reload"))
                        {
                            sendMessage(player, ChatColor.GREEN + "插件正在重新加载....");
                            String tempPrefix = getInstance().getConfig().getString("Messaging.Prefix");
                            reload(this);
                            player.sendMessage(tempPrefix + ChatColor.GREEN + "插件重载成功!");
                        }
                        break;
                    case "setname":
                        loglogger.Debug("[指令]检测到指令是设置物品名");
                        if (hasPermission(player, "Command.setname"))
                        {
                            ItemStack itemInHand = getAllVersionItemInHand(player);
                            String oldName = itemInHand.getItemMeta().getDisplayName();
                            if (oldName == null)
                            {
                                oldName = itemInHand.getType().name();
                            }
                            else if (oldName.isEmpty())
                            {
                                    oldName = itemInHand.getType().name();
                            }
                            loglogger.Debug("[指令]现在的物品名为: " + oldName);
                            String newName = convertColorCode(getTheRestToString(args, 1));
                            setAllVersionItemInHand(player, setName(itemInHand, newName));
                            sendMessage(player, ChatColor.GREEN + String.format("已将主手内物品名字从%s改为%s", oldName, newName));
                        }
                        break;
                    case "lore":
                        if (!(args.length >= 3))
                        {
                            sendHelpMessage(player);
                            return true;
                        }
                        switch (args[1].toLowerCase())
                        {
                            case "add":
                                if (hasPermission(player, "Command.lore.add"))
                                {
                                    ItemStack itemInHand = getAllVersionItemInHand(player);
                                    String lore = convertColorCode(getTheRestToString(args, 2));
                                    setAllVersionItemInHand(player, addLore(itemInHand, lore));
                                    sendMessage(player, ChatColor.GREEN + String.format("已在主手物品Lore内添加%s", lore));
                                }
                                break;
                            case "remove":
                                break;
                            case "insert":
                                break;
                            case "set":
                                break;
                        }
                        break;
                    //TODO: lore set
                    //TODO: lore add
                    //TODO: lore remove
                    //TODO: lore insert
                    //TODO: lore removeall
                    default:
                        sendHelpMessage(player);
                        break;
                }
            }
            else
            {
                loglogger.Debug("[指令]发送者不是玩家");
                //TODO: 不是玩家的情况
            }
            return true;
        }
        else
        {
            loglogger.Debug("[指令]指令名字不对, 已退出, 返回False");
            return false;
        }
    }
}
