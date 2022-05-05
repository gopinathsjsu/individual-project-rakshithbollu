package singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ItemCap {
    private static ItemCap itemCap = null;
    //static HashMap<String, items> items = new HashMap<String, items>();
    public static HashMap<String,Integer> itemInfo = new HashMap<String,Integer>();
    private ItemCap() {
        itemInfo.put("Essential",3);
        itemInfo.put("Luxury",4);
        itemInfo.put("Misc",6);

    }

    public static ItemCap getInstance() {
        if (itemCap != null) {
            return itemCap;
        }
        else {
            itemCap = new ItemCap();
            return itemCap;
        }
    }

}