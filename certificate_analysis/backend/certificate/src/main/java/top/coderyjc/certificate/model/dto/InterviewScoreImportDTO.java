package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * ClassName: InterviewScoreDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 4:34 PM
 * @Version 1.0
 */
@Data
public class InterviewScoreImportDTO {

    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 10)
    private String name;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号", width = 25)
    private String identificationId;


    /**
     * 考试地点（测试点）
     */
    @Excel(name = "测试点", width = 9)
    private String examAddress;


    /**
     * 工作单位
     */
    @Excel(name = "工作单位", width = 35)
    private String workAddress;

    /**
     * 申请专业
     */
    @Excel(name = "申请专业", width = 20)
    private String applyMajor;

    /**
     * 等级（ABCD缺考）
     */
    @Excel(name = "等级", width = 5)
    private String level;

    /**
     * 考试时间
     */
    @Excel(name = "考试时间", suffix = "年", width = 8)
    private String examDate;

    /**
     *  性别
     */
    @Excel(name = "性别", replace = { "男_1", "女_0" }, width = 8)
    private Integer gender;


}
