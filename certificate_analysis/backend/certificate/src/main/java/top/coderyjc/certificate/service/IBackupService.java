package top.coderyjc.certificate.service;

import top.coderyjc.certificate.model.entity.Backup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据库备份和恢复 服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-22
 */
public interface IBackupService extends IService<Backup> {

    void backup(String tableName, String description);

    void recover(Integer id);
}
