package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * ClassName: IdentificationDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/9/2023 8:31 AM
 * @Version 1.0
 */
@Data
public class IdentificationDTO {

    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 10)
    @TableField("name")
    private String name;


    /**
     * 性别
     */
    @Excel(name = "性别", width = 8)
    @TableField("gender")
    private String gender;

    /**
     * 专业类别
     */
    @Excel(name = "专业类别", width = 8)
    @TableField("major_type")
    private String majorType;


    /**
     * 毕业院校
     */
    @Excel(name = "毕业学校", width = 25)
    @TableField("graduation_school")
    private String graduationSchool;

    /**
     * 所学专业
     */
    @Excel(name = "所学专业", width = 35)
    @TableField("major")
    private String major;

    /**
     * 最高学历
     */
    @Excel(name = "最高学历", width = 10)
    @TableField("highest_education_background")
    private String highestEducationBackground;

    /**
     * 资格种类（高校，中学等）
     */
    @Excel(name = "资格种类", width = 10)
    @TableField("qualification_type")
    private String qualificationType;

    /**
     * 证件类型（身份证、台湾）
     */
    @Excel(name = "证件号码", width = 25)
    @TableField("identification_type")
    private String identificationType;

    /**
     * 最高学位
     */
    @Excel(name = "最高学位", width = 10)
    @TableField("highest_degree")
    private String highestDegree;

    /**
     * 认定批次
     */
    @Excel(name = "认定批次", width = 12)
    @TableField("affirm_batch")
    private String affirmBatch;

    /**
     * 确认地点
     */
    @Excel(name = "确认点", width = 35)
    @TableField("confirm_address")
    private String confirmAddress;

    /**
     * 认定机构
     */
    @Excel(name = "认定机构", width = 15)
    @TableField("affirm_institution")
    private String affirmInstitution;

    /**
     * 考试类型（是不是国考）
     */
    @Excel(name = "考试类型", width = 10)
    @TableField("exam_type")
    private String examType;

    /**
     * 机构类型
     */
    @Excel(name = "机构类型", width = 10)
    @TableField("organization_type")
    private String organizationType;

    /**
     * 任教学科
     */
    @Excel(name = "任教学科", width = 20)
    @TableField("subject")
    private String subject;

    /**
     * 教师资格证书号码
     */
    @Excel(name = "教师资格证书号码", width = 20)
    @TableField("certification_id")
    private String certificationId;

    /**
     * 市
     */
    @Excel(name = "市", width = 15)
    @TableField("city")
    private String city;


}
