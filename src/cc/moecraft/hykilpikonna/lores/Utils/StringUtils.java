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
     * @param string 源字符串
     * @return 替换后字符串
     */
    public static String convertColorCode(String string)
    {
        return string == null || string.length() == 0 ? "" : string.replace('&', '§');
    }

    /**
     * 把所有的&替换成§
     * @param reg 源字符串ArrayList
     * @return 替换后字符串ArrayList
     */
    public static ArrayList<String> convertColorCode(ArrayList<String> reg)
    {
        if (reg == null || reg.size() == 0) return null;
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
        if (reg == null || reg.size() == 0) return null;
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
        if (string == null || string.length() == 0) return "";
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
        if (reg == null || reg.size() == 0) return null;
        //这里有可能出错
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(removeColorCode(reg.get(i)));
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
        if (reg == null || reg.size() == 0) return null;
        //这里有可能出错
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(removeColorCode(reg.get(i)));
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
        if (str == null || str.length() == 0) return false;
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
        return string == null ? "" : string.replace(" ", "");
    }

    /**
     * 去除一个字符串中的空格
     * @param string 字符串
     * @return 空格
     */
    public static ArrayList<String> removeSpace(ArrayList<String> string)
    {
        if (string == null || string.size() == 0) return new ArrayList<>();

        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < string.size(); i++)
        {
            output.add(removeSpace(string.get(i)));
        }
        return output;
    }
}
