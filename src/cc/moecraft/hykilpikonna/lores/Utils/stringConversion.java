package cc.moecraft.hykilpikonna.lores.Utils;

import java.util.ArrayList;
import java.util.List;

public class stringConversion {
    public static String convertName(String reg)
    {
        return reg.replace('&', '§');
    }
    
    public static ArrayList<String> convertName(ArrayList<String> reg)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(reg.get(i).replace('&', '§'));
        }
        return output;
    }
    
    public static ArrayList<String> convertName(List<String> reg)
    {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++)
        {
            output.add(reg.get(i).replace('&', '§'));
        }
        return output;
    }
}
