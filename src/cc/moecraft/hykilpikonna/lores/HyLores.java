package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.essentials.logger.Logger;
import cc.moecraft.hykilpikonna.lores.Listeners.Effects.AttackEffectListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static cc.moecraft.hykilpikonna.lores.Configs.checkConfig;
import static cc.moecraft.hykilpikonna.lores.Messaging.sendHelpMessage;
import static cc.moecraft.hykilpikonna.lores.PluginUtil.reload;
import static cc.moecraft.hykilpikonna.lores.Setup.setup;

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
                switch (args.length)
                {
                    case 1:
                        //长度是1时第1项是0
                        switch (args[0])
                        {
                            case "reload":
                                loglogger.Debug("[指令]检测到指令是重载, 正在开始重载");
                                reload(this);
                                break;
                            default:
                                sendHelpMessage(player);
                                break;
                        }
                        break;
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
