package top.coderyjc.certificate.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
     * 姓名
     */
    @TableField("name")
    @Excel(name = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    @TableField("identification_id")
    private String identificationId;

    /**
     * 面试年份
     */
    @Excel(name = "面试年份")
    @TableField("interview_year")
    private String interviewYear;

    /**
     * 合格证号
     */
    @Excel(name = "合格证号")
    @TableField("qualification_id")
    private String qualificationId;

    /**
     * 专业
     */
    @Excel(name = "专业")
    @TableField("major")
    private String major;

    /**
     * 有效期
     */
    @Excel(name = "有效期")
    @TableField("validate_date")
    private Date validateDate;

    /**
     * 性别
     */
    @Excel(name = "性别")
    @TableField("gender")
    private Integer gender;

}
