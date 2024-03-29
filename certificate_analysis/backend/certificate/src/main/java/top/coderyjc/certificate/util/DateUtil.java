package top.coderyjc.certificate.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * ClassName: DateUtil
 * Package: top.coderyjc.certificate.util
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 10:06 PM
 * @Version 1.0
 */
public class DateUtil {


    /**
     * 把日期字符串转化为 yyyy-MM-dd 日期类型
     * @param dateStr 日期字符串
     * @return 日期类型
     */
    public static Date toDate(String dateStr){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 把日期字符串转化为 yyyy-MM-dd 日期类型
     * @param dateStr 日期字符串
     * @return 日期类型
     */
    public static Date toDateTime(String dateStr){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String toDateTimeString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 获取当前时间的字符串形式
     * @return
     */
    public static String getCurrentTimeString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }




}
