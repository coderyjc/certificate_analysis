package top.coderyjc.certificate.service.impl;

import top.coderyjc.certificate.model.entity.Backup;
import top.coderyjc.certificate.mapper.BackupMapper;
import top.coderyjc.certificate.service.IBackupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.util.DateUtil;
import top.coderyjc.certificate.util.MySQLDatabaseBackup;

/**
 * <p>
 * 数据库备份和恢复 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-22
 */
@Service
public class BackupServiceImpl extends ServiceImpl<BackupMapper, Backup> implements IBackupService {

    @Override
    public void backup(String tableName, String description) {
        boolean rst;
        String timeNow = DateUtil.getCurrentTimeString();
        String filename = timeNow.replace(' ', '-').replace(':','-') + '-' + tableName + ".sql";
        try {
            rst = MySQLDatabaseBackup.exportDatabaseTool("localhost", "root",
                    "333",
                    "./mysql_backup/",
                    tableName,
                    filename,
                    "certificate_analysis");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(rst){
            Backup backup = new Backup();
            backup.setBackupDate(DateUtil.toDateTime(timeNow));
            backup.setDescription(description);
            backup.setFilename(filename);
            backup.setTableName(tableName);
            baseMapper.insert(backup);
        }
    }

    @Override
    public void recover(Integer id) {
        Backup backup = this.getById(id);
        String currentPath = System.getProperty("user.dir");
        String path = currentPath + "\\" + backup.getFilename();

        try {
            MySQLDatabaseBackup.dbRestoreMysql(
                    "root",
                    "333",
                    "localhost",
                    path
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
