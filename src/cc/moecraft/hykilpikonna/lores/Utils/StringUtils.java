package cc.moecraft.hykilpikonna.lores.Utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个用来做字符串处理的类
 */
public class StringUtils
{
    /**
     * 把所有的&替换成§
     * @param reg 源字符串
     * @return 替换后字符串
     */
    public static String convertColorCode(String reg)
    {
        return reg.replace('&', '§');
    }

    /**
     * 把所有的&替换成§
     * @param reg 源字符串ArrayList
     * @return 替换后字符串ArrayList
     */
    public static ArrayList<String> convertColorCode(ArrayList<String> reg)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(reg.get(i).replace('&', '§'));
        }
        return output;
    }

    /**
     * 把所有的&替换成§
     * @param reg 源字符串List
     * @return 替换后字符串ArrayList
     */
    public static ArrayList<String> convertColorCode(List<String> reg)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(reg.get(i).replace('&', '§'));
        }
        return output;
    }

    /**
     * 删除所有颜色代码
     * @param string 源字符串
     * @return 删除后字符串
     */
    public static String removeColorCode(String string)
    {
        //TODO: Fix dis
        String temp = convertColorCode(string);
        String output = "";
        for (int i = 0; i < temp.length(); i++)
        {
            if (temp.charAt(i) == '§')
            {
                i += 1;
            }
            else
            {
                output += temp.charAt(i);
            }
        }
        return output;
    }

    /**
     * 删除所有颜色代码
     * @param reg 源字符串ArrayList
     * @return 删除后字符串ArrayList
     */
    public static ArrayList<String> removeColorCode(ArrayList<String> reg)
    {
        //这里有可能出错
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(convertColorCode(reg.get(i)).replace("§", ""));
        }
        return output;
    }

    /**
     * 删除所有颜色代码
     * @param reg 源字符串ArrayList
     * @return 删除后字符串ArrayList
     */
    public static ArrayList<String> removeColorCode(List<String> reg)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(convertColorCode(reg.get(i)).replace("§", ""));
        }
        return output;
    }

    /**
     * 判断一个String是不是数字
     * @param str 字符串
     * @return 是不是数字
     */
    public static boolean isNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == '_') return false;
            try
            {
                if (!Character.isDigit(str.charAt(i)))
                {
                    return false;
                }
            }
            catch (NumberFormatException e)
            {
                return  false;
            }
        }
        return true;
    }

    /**
     * 判断一个Char是不是数字
     * @param character 字符
     * @return 是不是数字
     */
    public static boolean isNumeric(Character character)
    {
        return Character.isDigit(character);
    }

    /**
     * 去除一个字符串中的空格
     * @param string 字符串
     * @return 空格
     */
    public static String removeSpace(String string)
    {
        return string.replace(" ", "");
    }

    /**
     * 去除一个字符串中的空格
     * @param string 字符串
     * @return 空格
     */
    public static ArrayList<String> removeSpace(ArrayList<String> string)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < string.size(); i++)
        {
            output.add(removeSpace(string.get(i)));
        }
        return output;
    }
}
