package top.coderyjc.certificate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 笔试成绩
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Data
@TableName("tbl_written_score")
public class WrittenScore implements Serializable {

    /**
     * 主键、自增、无意义
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 准考证号
     */
    @TableField("exam_id")
    private String examId;

    /**
     * 身份证号
     */
    @TableField("identification_id")
    private String identificationId;

    /**
     * 教育学成绩
     */
    @TableField("education_score")
    private String educationScore;

    /**
     * 教育心理学成绩
     */
    @TableField("education_psychology_score")
    private String educationPsychologyScore;

    /**
     * 职业道德修养和高等教育法规成绩
     */
    @TableField("professional_ethic_score")
    private String professionalEthicScore;

    /**
     * 教育学考试状态
     */
    @TableField("education_status")
    private String educationStatus;

    /**
     * 教育心理学考试状态
     */
    @TableField("education_psychology_status")
    private String educationPsychologyStatus;

    /**
     * 职业道德修养和高等教育法规状态
     */
    @TableField("professional_ethic_status")
    private String professionalEthicStatus;

    /**
     * 工作单位
     */
    @TableField("work_address")
    private String workAddress;

    /**
     * 考试时间
     */
    @TableField("exam_date")
    private String examDate;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;
}
