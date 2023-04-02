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
  @TableName("tbl_certification")
public class Certification implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    @TableField("身份证")
    private String 身份证;

    @TableField("姓名")
    private String 姓名;

    @TableField("面试年份")
    private String 面试年份;

    @TableField("合格证号")
    private String 合格证号;

    @TableField("专业")
    private String 专业;

    @TableField("有效期")
    private String 有效期;
}
