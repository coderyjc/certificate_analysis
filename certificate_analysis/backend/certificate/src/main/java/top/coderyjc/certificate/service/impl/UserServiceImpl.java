package top.coderyjc.certificate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.coderyjc.certificate.model.entity.User;
import top.coderyjc.certificate.mapper.UserMapper;
import top.coderyjc.certificate.model.vo.UserListVO;
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

    @Override
    public IPage<UserListVO> listAll(Integer pn, Integer size, JSONObject condition) {
        IPage<UserListVO> page = new Page<>(pn, size);
        return baseMapper.listAll(page);
    }
}
