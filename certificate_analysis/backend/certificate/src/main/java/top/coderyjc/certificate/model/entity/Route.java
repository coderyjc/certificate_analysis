package top.coderyjc.certificate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 路由表
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */

@Data
@TableName("tbl_route")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路由id，自增，无意义
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 路由名称
     */
    @TableField("name")
    private String name;

    /**
     * 页面标题
     */
    @TableField("title")
    private String title;

    /**
     * 父节点的id
     */
    @TableField("parent_id")
    private Integer parentId;
}
