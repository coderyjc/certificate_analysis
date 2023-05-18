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
     * @param username
     * @param pwd
     * @param path
     */
    public static void dbRestoreMysql(String username, String pwd, String path) throws IOException {

        Runtime runtime = Runtime.getRuntime();
        //把所执行的命令将以字符串数组的形式出现
        //根据属性文件的配置获取数据库导入所需的命令，组成一个数组
        String cmdarray[] = getImportCommand(username, pwd, "localhost", "3306", "certificate_analysis", path, "F:\\db\\mysql-8.0.30-winx64\\bin\\");
        System.out.println(cmdarray);
        Process process = runtime.exec(cmdarray[0]);
        //执行了第一条命令以后已经登录到mysql了，所以之后就是利用mysql的命令窗口
        java.io.OutputStream os = process.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        //命令1和命令2要放在一起执行
        // 这里会执行后面的代码， 将命令输出到mysql的命令窗口，进行执行
        writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
        writer.flush();
        writer.close();
        os.close();

    }

    private static String[] getImportCommand(String username, String password, String host, String port, String importDatabaseName, String importPath,
                                             String MysqlPath) {

        //第一步，获取登录命令语句
        String loginCommand = MysqlPath + "mysql -h" + host + " -u" + username + " -p" + password +
                " -P" + port;
        //第二步，获取切换数据库到目标数据库的命令语句
        String switchCommand = "use " + importDatabaseName;
        //第三步，获取导入的命令语句
        String importCommand = " source " + importPath;

        //需要返回的命令语句数组
        String[] strings = {loginCommand, switchCommand, importCommand};
        return strings;
    }


}
