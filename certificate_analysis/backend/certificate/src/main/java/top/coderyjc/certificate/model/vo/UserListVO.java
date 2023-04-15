package top.coderyjc.certificate.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: UserListVO
 * Package: top.coderyjc.certificate.model.vo
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/10/2023 12:46 PM
 * @Version 1.0
 */

@Data
public class UserListVO {

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
     * 创建事件
     */
    @TableField("create_date")
    private Date createDate;

}
