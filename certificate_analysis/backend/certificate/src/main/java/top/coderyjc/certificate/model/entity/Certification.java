package top.coderyjc.certificate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

/**
 * <p>
 * 证书表哦
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Data
@TableName("tbl_certification")
public class Certification implements Serializable {

    /**
     * 主键，无意义，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 身份证号
     */
    @TableField("idenfication_id")
    private String idenficationId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 面试年份
     */
    @TableField("interview_year")
    private String interviewYear;

    /**
     * 合格证号
     */
    @TableField("qualification_id")
    private String qualificationId;

    /**
     * 专业
     */
    @TableField("major")
    private String major;

    /**
     * 有效期
     */
    @TableField("validate_date")
    private Date validateDate;
}
