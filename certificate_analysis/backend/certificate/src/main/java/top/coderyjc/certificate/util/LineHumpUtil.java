package top.coderyjc.certificate.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ClassName: LineHumpUtil
 * Package: top.coderyjc.certificate.util
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/17/2023 7:05 PM
 * @Version 1.0
 */
public class LineHumpUtil {

    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z0-9]");

        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }



}
