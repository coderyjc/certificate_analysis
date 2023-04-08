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
 * 面试成绩
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Data
@TableName("tbl_interview_score")
public class InterviewScore implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 考试地点（测试点）
     */
    @TableField("exam_address")
    private String examAddress;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 身份证号
     */
    @TableField("identification_id")
    private String identificationId;

    /**
     * 工作单位
     */
    @TableField("work_address")
    private String workAddress;

    /**
     * 申请专业
     */
    @TableField("apply_major")
    private String applyMajor;

    /**
     * 等级（ABCD缺考）
     */
    @TableField("level")
    private String level;

    /**
     * 考试时间
     */
    @TableField("exam_date")
    private String examDate;

    /**
     *  性别
     */
    @TableField("gender")
    private Integer gender;


}
