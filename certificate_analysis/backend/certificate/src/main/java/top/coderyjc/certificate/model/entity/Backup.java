package top.coderyjc.certificate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据库备份和恢复
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-22
 */
@Data
@TableName("tbl_backup")
public class Backup implements Serializable {

    /**
     * 主键，无意义
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 表名
     */
    @TableField("table_name")
    private String tableName;

    /**
     * 文件名
     */
    @TableField("filename")
    private String filename;

    /**
     * 备份时间
     */
    @TableField("backup_date")
    private Date backupDate;


    /**
     * 备份理由，备注
     */
    @TableField("description")
    private String description;

}

