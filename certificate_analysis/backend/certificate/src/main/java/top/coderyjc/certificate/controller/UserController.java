package top.coderyjc.certificate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.coderyjc.certificate.mapper.UserMapper;
import top.coderyjc.certificate.model.entity.User;
import top.coderyjc.certificate.service.IUserService;
import top.coderyjc.certificate.service.impl.UserServiceImpl;
import top.coderyjc.certificate.util.MD5Util;
import top.coderyjc.certificate.util.Msg;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;


    /**
     * 用户登录，无token
     * 2023.4.4 测试成功
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Msg login(
            String username,
            String password
    ){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", MD5Util.getMD5(password));
        User user = userService.getOne(wrapper);
        if(null == user){
            return Msg.fail().add("msg", "登录失败");
        }
        return Msg.success().add("msg", "登录成功");
    }

}

