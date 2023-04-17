package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: CertificationStatisticDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/17/2023 8:32 PM
 * @Version 1.0
 */

@Data
public class CertificationStatisticDTO {

    /**
     * 面试年份
     */
    @Excel(name = "面试年份", suffix = "年", width = 8)
    @TableField("interview_year")
    private String interviewYear;

    /**
     * 专业
     */
    @Excel(name = "专业", width = 25)
    @TableField("major")
    private String major;

    /**
     * 有效期
     */
    @Excel(name = "有效期", format = "yyyy-MM-dd", width = 15)
    @TableField("validate_date")
    private Date validateDate;

    /**
     * 性别
     */
    @Excel(name = "性别", width = 8, replace = { "男_1", "女_0" })
    @TableField("gender")
    private Integer gender;

    /**
     *  数量
     */
    @Excel(name = "数量", width = 15)
    private Integer count;
}
