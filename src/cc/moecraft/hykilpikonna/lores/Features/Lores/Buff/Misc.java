package cc.moecraft.hykilpikonna.lores.Features.Lores.Buff;

import org.bukkit.entity.Player;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.lores.HyLores.loglogger;
import static cc.moecraft.hykilpikonna.lores.Utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.lores.Utils.ItemUtils.getAllItemLores;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeColorCode;
import static cc.moecraft.hykilpikonna.lores.Utils.StringUtils.removeSpace;

/**
 * 此类由 Hykilpikonna 在 2017/06/19 创建!
 * Created by Hykilpikonna on 2017/06/19!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Misc
{
    public static double calculateTotal(Player player, double inputDamage, String keyWord, boolean opposite)
    {
        ArrayList<ArrayList<String>> allItemLores = getAllItemLores(player);
        double totalDamage = inputDamage + 0.0;

        if (allItemLores == null || allItemLores.size() < 1)
        {
            return totalDamage;
        }

        for (int i = 0; i < allItemLores.size(); i++)
        {
            if (allItemLores.get(i) != null)
            {
                for (int j = 0; j < allItemLores.get(i).size(); j++)
                {
                    String one = removeSpace(removeColorCode(allItemLores.get(i).get(j)));
                    if (one.contains(keyWord))
                    {
                        one = one.replace(keyWord, "");
                        if (one.length() > 0)
                        {
                            //运算符逆转
                            if (opposite)
                            {
                                one = one.replace("+", "=");
                                one = one.replace("-", "+");
                                one = one.replace("=", "-");
                                one = one.replace("x", "=");
                                one = one.replace("/", "x");
                                one = one.replace("=", "/");
                            }
                            loglogger.Debug("one.charAt(0) == " + one.charAt(0));
                            switch (one.charAt(0))
                            {
                                case '+':
                                    totalDamage += Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                case '-':
                                    totalDamage -= Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                case 'x':
                                    totalDamage *= Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                case '/':
                                    totalDamage /= Double.parseDouble(getTheRestToString(one, 1));
                                    break;
                                default:
                                    //+但是少了一位
                                    totalDamage += Double.parseDouble(getTheRestToString(one, 0));
                                    break;
                            }
                        }
                    }
                }
            }
        }
        loglogger.Debug("[获取Buff] 已获取: " + totalDamage);
        return totalDamage;
    }
}
