package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * ClassName: WrittenScoreStatisticDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/18/2023 4:12 PM
 * @Version 1.0
 */

@Data
public class WrittenScoreStatisticDTO {

    /**
     * 教育学成绩
     */
    @Excel(name = "教育学成绩", replace = {"没有报名考试_-1"}, width = 12)
    @TableField("education_score")
    private String educationScore;

    /**
     * 教育心理学成绩
     */
    @Excel(name = "教育心理学成绩", replace = {"没有报名考试_-1"}, width = 15)
    @TableField("education_psychology_score")
    private String educationPsychologyScore;

    /**
     * 职业道德修养和高等教育法规成绩
     */
    @Excel(name = "职业道德修养和高等教育法规成绩", replace = {"没有报名考试_-1"}, width = 30)
    @TableField("professional_ethic_score")
    private String professionalEthicScore;

    /**
     * 教育学考试状态
     */
    @Excel(name = "教育学考试状态", width = 15)
    @TableField("education_status")
    private String educationStatus;

    /**
     * 教育心理学考试状态
     */
    @Excel(name = "教育心理学考试状态", width = 20)
    @TableField("education_psychology_status")
    private String educationPsychologyStatus;

    /**
     * 职业道德修养和高等教育法规状态
     */
    @Excel(name = "职业道德修养和高等教育法规状态", width = 30)
    @TableField("professional_ethic_status")
    private String professionalEthicStatus;

    /**
     * 工作单位
     */
    @Excel(name = "工作单位", width = 20)
    @TableField("work_address")
    private String workAddress;

    /**
     * 考试时间
     */
    @Excel(name = "考试时间", suffix = "年", width = 10)
    @TableField("exam_date")
    private String examDate;

    /**
     * 性别
     */
    @Excel(name = "性别", replace = { "男_1", "女_0" }, width = 8)
    @TableField("gender")
    private Integer gender;


    /**
     *  数量
     */
    @Excel(name = "数量", width = 15)
    private Integer count;
}
