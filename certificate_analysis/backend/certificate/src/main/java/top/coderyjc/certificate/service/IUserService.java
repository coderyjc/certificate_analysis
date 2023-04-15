package top.coderyjc.certificate.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.coderyjc.certificate.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.coderyjc.certificate.model.vo.UserListVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface IUserService extends IService<User> {

    IPage<UserListVO> listAll(Integer pn, Integer size, JSONObject condition);
}
