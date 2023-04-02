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
  @TableName("tbl_written_score")
public class WrittenScore implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    @TableField("姓名")
    private String 姓名;

    @TableField("准考证号")
    private String 准考证号;

    @TableField("身份证号")
    private String 身份证号;

    @TableField("教育学成绩")
    private String 教育学成绩;

    @TableField("教育心理学成绩")
    private String 教育心理学成绩;

    @TableField("职业道德修养和高等教育法规成绩")
    private String 职业道德修养和高等教育法规成绩;

    @TableField("教育学考试状态")
    private String 教育学考试状态;

    @TableField("教育心理学考试状态")
    private String 教育心理学考试状态;

    @TableField("职业道德修养和高等教育法规状态")
    private String 职业道德修养和高等教育法规状态;

    @TableField("工作单位")
    private String 工作单位;

    @TableField("考试时间")
    private String 考试时间;
}
