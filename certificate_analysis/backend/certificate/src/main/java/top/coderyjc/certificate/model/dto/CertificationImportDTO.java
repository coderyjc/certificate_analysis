package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.sql.Date;

/**
 * ClassName: CertificationImportDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 8:10 PM
 * @Version 1.0
 */
@Data
public class CertificationImportDTO {

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
    @Excel(name = "有效期", format = "yyyy-MM-dd")
    @TableField("validate_date")
    private Date validateDate;


}
