package top.coderyjc.certificate.util;

import cn.hutool.core.util.ArrayUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Arrays;

import static cn.afterturn.easypoi.util.PoiCellUtil.getCellValue;

/**
 * ClassName: VerificationUtil
 * Package: top.coderyjc.certificate.util
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 9:54 AM
 * @Version 1.0
 */
public class VerificationUtil {

    public static Boolean verificationWrittenScoreExcelHeadLine(Workbook wb, String[] columnName){
        String result = null;
        try {
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(0);
//            System.out.println("应该有的列名——" + Arrays.toString(columnName));
//            System.out.println("实际的列名——" + row.getCell(1));
//            System.err.println("columnName.length:" + columnName.length);
//            if (row != null && row.getLastCellNum() == columnName.length) {
//                int lastCellNum = row.getLastCellNum();
//                System.err.println("lastCellNum:" + lastCellNum);
//                for (int idx = 0; idx < lastCellNum; idx++) {
//                    String value = getCellValue(row.getCell(idx));
//                    if (idx < 6) {
//                        if (StringUtils.isBlank(value) || !columnName[idx].equals(value)) {
//                            result = "标题行第" + (idx + 1) + "列名称错误！";
//                            return false;
//                        }
//                    }
//                }
//            } else {
//                return false;
//            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
