package cc.moecraft.hykilpikonna.lores.Utils;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.lores.HyLores.getInstance;
import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeSpace;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ArrayUtils
{
    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static ArrayList<String> getTheRest(String[] strings, int index)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = index; i < strings.length; i++)
        {
            output.add(strings[i]);
        }
        return output;
    }

    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static String getTheRestToString(String[] strings, int index)
    {
        String output = "";
        for (int i = index; i < strings.length; i++)
        {
            output += strings[i] + " ";
        }
        loglogger.Debug("[工具处理]其余的字符串是: " + output);
        return output;
    }

    /**
     * 获取剩下的
     * @param strings ArrayList
     * @param index 最小
     * @return 剩下的
     */
    public static String getTheRestToString(ArrayList<String> strings, int index)
    {
        String output = "";
        for (int i = index; i < strings.size(); i++)
        {
            output += strings.get(i);
        }
        loglogger.Debug("[工具处理]其余的字符串是: " + output);
        return output;
    }

    /**
     * 获取剩下的
     * @param string String
     * @param index 最小
     * @return 剩下的
     */
    public static String getTheRestToString(String string, int index)
    {
        String output = "";
        for (int i = index; i < string.length(); i++)
        {
            output += string.charAt(i);
        }
        loglogger.Debug("[工具处理]其余的字符串是: " + output);
        return output;
    }

    /**
     * 把Lore转换成技能表
     * @param lore Lore
     * @return 技能表
     */
    public static ArrayList<String> readLore(ArrayList<String> lore)
    {
        if (lore == null)
        {
            loglogger.Debug("[Lore]Lore是Null");
            return null;
        }

        loglogger.Debug("[Lore]正在把Lore转换为技能列表");
        ArrayList<String> list = new ArrayList<>();
        String prefix = removeSpace(removeColorCode(getInstance().getConfig().getString("Lore.Prefix")));
        for (int i = 0; i < lore.size(); i++)
        {
            String tempString = removeSpace(removeColorCode(lore.get(i)));
            if (tempString.contains(prefix))
            {
                list.add(tempString.replace(prefix, ""));
                loglogger.Debug(String.format("[Lore]行数:%s, 值:%s", i, list.get(i)));
            }
            else
            {
                loglogger.Debug(String.format("[Lore]未通过验证: Prefix = %s, Lore = %s", prefix, tempString));
            }
        }
        return list;
    }
}
