package top.coderyjc.certificate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 认定表
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Data
@TableName("tbl_identification")
public class Identification implements Serializable {

    /**
     * 主键、自增、无意义
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资格种类（高校，中学等）
     */
    @TableField("qualification_type")
    private String qualificationType;

    /**
     * 证件类型（身份证、台湾）
     */
    @TableField("identification_type")
    private String identificationType;

    /**
     * 最高学位
     */
    @TableField("highest_degree")
    private String highestDegree;

    /**
     * 认定批次
     */
    @TableField("affirm_batch")
    private String affirmBatch;

    /**
     * 确认地点
     */
    @TableField("confirm_address")
    private String confirmAddress;

    /**
     * 证件号码（身份证或者大陆通行证）
     */
    @TableField("identification_id")
    private String identificationId;

    /**
     * 毕业院校
     */
    @TableField("graduation_school")
    private String graduationSchool;

    /**
     * 认定机构
     */
    @TableField("affirm_institution")
    private String affirmInstitution;

    /**
     * 考试类型（是不是国考）
     */
    @TableField("exam_type")
    private String examType;

    /**
     * 机构类型
     */
    @TableField("orgnization_type")
    private String orgnizationType;

    /**
     * 任教学科
     */
    @TableField("subject")
    private String subject;

    /**
     * 教师资格证书号码
     */
    @TableField("certification_id")
    private String certificationId;

    /**
     * 所学专业
     */
    @TableField("major")
    private String major;

    /**
     * 市
     */
    @TableField("city")
    private String city;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 最高学历
     */
    @TableField("highest_education_background")
    private String highestEducationBackground;

    /**
     * 专业类别
     */
    @TableField("major_type")
    private String majorType;
}
