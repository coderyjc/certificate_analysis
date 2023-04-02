package top.coderyjc.certification.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Getter
@Setter
  @TableName("tbl_interview_score")
public class InterviewScore implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    @TableField("测试点")
    private String 测试点;

    @TableField("姓名")
    private String 姓名;

    @TableField("身份证号")
    private String 身份证号;

    @TableField("工作单位")
    private String 工作单位;

    @TableField("申请专业")
    private String 申请专业;

    @TableField("等级")
    private String 等级;

    @TableField("考试时间")
    private String 考试时间;
}
