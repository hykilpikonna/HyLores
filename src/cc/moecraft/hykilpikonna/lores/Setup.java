package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.lores.Listeners.Effects.AttackEffectListener;
import jdk.nashorn.internal.runtime.Debug;

import static cc.moecraft.hykilpikonna.lores.Configs.checkConfig;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Setup
{
    public static final AttackEffectListener ATTACK_EFFECT_LISTENER = new AttackEffectListener();
    public static void setup()
    {
        loglogger.Debug("[加载]正在运行Setup");
        checkConfig();
    }
}
