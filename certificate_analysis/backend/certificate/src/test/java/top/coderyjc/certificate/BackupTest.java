package top.coderyjc.certificate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.coderyjc.certificate.service.IBackupService;
import top.coderyjc.certificate.util.MySQLDatabaseBackup;

/**
 * ClassName: BackupTest
 * Package: top.coderyjc.certificate
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/29/2023 9:59 AM
 * @Version 1.0
 */

@SpringBootTest
public class BackupTest {

    @Test
    public void test(){
        try {
            MySQLDatabaseBackup.exportDatabaseTool("localhost",
                    "root", "333",
                    "./mysql_backup/",
                    "tbl_written_statistic",
                    "tbl_written_statistic.sql",
                    "certificate_analysis"
                    );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
