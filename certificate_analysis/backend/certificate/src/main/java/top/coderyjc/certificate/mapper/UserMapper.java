package top.coderyjc.certificate.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.coderyjc.certificate.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.coderyjc.certificate.model.vo.UserListVO;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<UserListVO> listAll(IPage<UserListVO> page);
}
