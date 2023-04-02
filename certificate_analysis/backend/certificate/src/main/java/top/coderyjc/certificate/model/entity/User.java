package top.coderyjc.certificate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Data
@TableName("tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增，无意义
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 创建事件
     */
    @TableField("create_date")
    private Date createDate;
}
