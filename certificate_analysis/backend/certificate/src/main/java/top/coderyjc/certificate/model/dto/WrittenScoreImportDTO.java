package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: WrittenScoreImportDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 10:24 AM
 * @Version 1.0
 */

@Data
public class WrittenScoreImportDTO {

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 准考证号
     */
    @Excel(name = "准考证号", width = 12)
    private String examId;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号", width = 20)
    private String identificationId;

    /**
     * 教育学成绩
     */
    @Excel(name = "教育学成绩", replace = {"没有报名考试_-1"}, width = 12)
    private String educationScore;

    /**
     * 教育心理学成绩
     */
    @Excel(name = "教育心理学成绩", replace = {"没有报名考试_-1"}, width = 15)
    private String educationPsychologyScore;

    /**
     * 职业道德修养和高等教育法规成绩
     */
    @Excel(name = "职业道德修养和高等教育法规成绩", replace = {"没有报名考试_-1"}, width = 30)
    private String professionalEthicScore;

    /**
     * 教育学考试状态
     */
    @Excel(name = "教育学考试状态", width = 15)
    private String educationStatus;

    /**
     * 教育心理学考试状态
     */
    @Excel(name = "教育心理学考试状态", width = 20)
    private String educationPsychologyStatus;

    /**
     * 职业道德修养和高等教育法规状态
     */
    @Excel(name = "职业道德修养和高等教育法规状态", width = 30)
    private String professionalEthicStatus;

    /**
     * 工作单位
     */
    @Excel(name = "工作单位", width = 20)
    private String workAddress;

    /**
     * 考试时间
     */
    @Excel(name = "考试时间", suffix = "年", width = 10)
    private String examDate;

    /**
     * 性别
     */
//    @Excel(name = "性别", replace = { "男_1", "女_0" }, width = 8)
//    private Integer gender;

}
