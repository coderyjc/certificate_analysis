package top.coderyjc.certificate.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-23
 */
@Data
@TableName("tbl_written_statistic")
public class WrittenStatistic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年份
     */
    @TableField("year")
    @Excel(name = "年份")
    @TableId(value = "year", type = IdType.AUTO)
    private Integer year;

    /**
     * 教育学平均成绩
     */
    @TableField("education_average_score")
    @Excel(name = "教育学平均成绩")
    private Double educationAverageScore;


    /**
     * 教育学及格分
     */
    @TableField("education_pass_score")
    @Excel(name = "教育学及格分")
    private Double educationPassScore;


    /**
     * 教育学通过率
     */
    @TableField("education_pass_rate")
    @Excel(name = "教育学通过率", suffix = "%")
    private Double educationPassRate;

    /**
     * 教育心理学平均成绩
     */
    @TableField("psychology_average_score")
    @Excel(name = "教育心理学平均成绩")
    private Double psychologyAverageScore;

    /**
     * 教育心理学通过率
     */
    @TableField("psychology_pass_rate")
    @Excel(name = "教育心理学通过率", suffix = "%")
    private Double psychologyPassRate;

    /**
     * 教育心理学及格分
     */
    @TableField("psychology_pass_score")
    @Excel(name = "教育心理学及格分")
    private Double psychologyPassScore;

    /**
     * 职业道德平均分数
     */
    @TableField("ethic_average_score")
    @Excel(name = "职业道德平均分数")
    private Double ethicAverageScore;

    /**
     * 职业道德通过率
     */
    @TableField("ethic_pass_rate")
    @Excel(name = "职业道德通过率", suffix = "%")
    private Double ethicPassRate;

    /**
     * 职业道德通过率
     */
    @TableField("ethic_pass_score")
    @Excel(name = "职业道德及格分")
    private Double ethicPassScore;

    /**
     * 当年三科都通过的通过率
     */
    @TableField("pass_rate")
    @Excel(name = "三科都通过的通过率", suffix = "%")
    private Double passRate;
}
