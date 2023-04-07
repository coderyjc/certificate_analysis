package top.coderyjc.certificate.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: DownloadExcelUtil
 * Package: top.coderyjc.certificate.util
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/7/2023 2:03 PM
 * @Version 1.0
 */
public class DownloadUtil {

    /**
     * 通用的文件下载工具
     * @param response
     * @param fileName
     * @param directory
     */
    public static void downloadFile(HttpServletResponse response, String fileName, String directory){
        File file = new File(directory + "/" + fileName);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/force-download");
//            response.addHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setHeader("Content-Length", "" + file.length());
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                assert bis != null;
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载excel表格
     * @param response
     * @param entity
     * @param pojoClass
     * @param dataSet
     * @throws IOException
     */
    public static void downloadExcel(HttpServletResponse response, ExportParams entity, List<ExcelExportEntity> exportColumns, Class<?> pojoClass, Collection<?> dataSet) {
//      目录是cache（缓存），文件名暂时用时间戳代替
        String directory = "./cache";
        String fileName = System.currentTimeMillis() + ".xlsx";

//      获取临时导出目录
        File file = new File(directory);
        if(!file.exists()) file.mkdir();

//      创建表格文件
        Workbook workbook = ExcelExportUtil.exportExcel(entity, exportColumns, dataSet);

//      导出文件到指定位置
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(directory + '\\' + fileName);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//      返回给客户端
        downloadFile(response, fileName, directory);
    }

}