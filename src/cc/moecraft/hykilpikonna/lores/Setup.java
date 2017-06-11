package cc.moecraft.hykilpikonna.lores;

import cc.moecraft.hykilpikonna.lores.Listeners.Effects.AttackEffectListener;

import static cc.moecraft.hykilpikonna.lores.Configs.checkConfig;

/**
 * 此类由 Hykilpikonna 在 2017/06/10 创建!
 * Created by Hykilpikonna on 2017/06/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Setup
{
    public static void setup()
    {
        checkConfig();
        AttackEffectListener attackEffectListener = new AttackEffectListener();
    }
}
