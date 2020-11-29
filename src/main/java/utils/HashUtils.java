package utils;

import java.util.ArrayList;
import java.util.List;

public class HashUtils {
    List<Object> objectList = null;
    public HashUtils(Object...args) {
        objectList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            objectList.add(args[i]);
        }
    }
    public int hash() {
        return Math.abs(objectList.hashCode()%1000000);
    }
}
