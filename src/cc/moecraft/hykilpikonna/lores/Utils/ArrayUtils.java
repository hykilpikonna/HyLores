package cc.moecraft.hykilpikonna.lores.Utils;

import java.util.ArrayList;

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
            output += strings[i];
        }
        return output;
    }
}
