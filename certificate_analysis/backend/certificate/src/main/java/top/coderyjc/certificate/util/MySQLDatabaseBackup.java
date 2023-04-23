package top.coderyjc.certificate.util;

import java.io.*;

/**
 * ClassName: MySQLDatabaseBackup
 * Package: top.coderyjc.certificate.util
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/22/2023 8:51 PM
 * @Version 1.0
 */
public class MySQLDatabaseBackup {

    /**
     * Java代码实现MySQL数据库导出
     *
     * @author GaoHuanjie
     * @param hostIP MySQL数据库所在服务器地址IP
     * @param userName 进入数据库所需要的用户名
     * @param password 进入数据库所需要的密码
     * @param savePath 数据库导出文件保存路径
     * @param fileName 数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean exportDatabaseTool(
            String hostIP,
            String userName,
            String password,
            String savePath,
            String tableName,
            String fileName,
            String databaseName
    ) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            String command = "./bin/mysqldump -h" + hostIP + " --databases " + databaseName + " --tables " + tableName + " -u" + userName + " -p" + password + " --default-character-set=UTF8 ";
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor() == 0){//0 表示线程正常终止。
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 数据库还原
     *  mysql -ucertificate -p333 certificate < ./2023-04-22-21-49-45-tbl_certification.sql
     * @param username 账号
     * @param pwd 密码
     * @param url 地址
     * @param path 文件存放路径
     */
    public static void dbRestoreMysql(String username, String pwd, String url, String path) {

        String cmd = "mysql -h" + url + " -u" + username + " -p" + pwd + " certificate" + " < " + path;

        System.out.println(cmd);

        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
