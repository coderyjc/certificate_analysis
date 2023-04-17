package top.coderyjc.certificate.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * ClassName: InterviewScoreStatisticExportDTO
 * Package: top.coderyjc.certificate.model.dto
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/17/2023 3:57 PM
 * @Version 1.0
 */
@Data
public class InterviewScoreStatisticDTO {

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

    /**
     *  数量
     */
    @Excel(name = "数量", width = 15)
    private Integer count;
}
