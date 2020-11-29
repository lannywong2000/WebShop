package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String data2string(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(date);
        return sDate;
    }
}
