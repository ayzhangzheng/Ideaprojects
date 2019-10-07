package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串
    public static String datetoString(Date date,String pattren){
        SimpleDateFormat sdf=new SimpleDateFormat(pattren);
        String format = sdf.format(date);
        return format;
    }
    //字符串转换成日期
    public static Date stringtoDate(String str,String pattern) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date parse = sdf.parse(str);
        return parse;
    }
}
