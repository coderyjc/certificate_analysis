package top.coderyjc.certificate.service.impl;

import top.coderyjc.certificate.model.entity.User;
import top.coderyjc.certificate.mapper.UserMapper;
import top.coderyjc.certificate.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
