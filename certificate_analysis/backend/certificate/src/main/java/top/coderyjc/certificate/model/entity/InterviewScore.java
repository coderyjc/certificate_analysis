package top.coderyjc.certificate.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
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
    @Excel(name = "测试点", width = 9)
    @TableField("exam_address")
    private String examAddress;

    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 10)
    @TableField("name")
    private String name;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号", width = 25)
    @TableField("identification_id")
    private String identificationId;

    /**
     * 工作单位
     */
    @Excel(name = "工作单位", width = 35)
    @TableField("work_address")
    private String workAddress;

    /**
     * 申请专业
     */
    @Excel(name = "申请专业", width = 20)
    @TableField("apply_major")
    private String applyMajor;

    /**
     * 等级（ABCD缺考）
     */
    @Excel(name = "等级", width = 5)
    @TableField("level")
    private String level;

    /**
     * 考试时间
     */
    @Excel(name = "考试时间", suffix = "年", width = 8)
    @TableField("exam_date")
    private String examDate;

    /**
     *  性别
     */
    @Excel(name = "性别", replace = { "男_1", "女_0" }, width = 8)
    @TableField("gender")
    private Integer gender;

}
