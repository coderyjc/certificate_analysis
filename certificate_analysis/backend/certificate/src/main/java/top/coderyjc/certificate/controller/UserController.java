package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.coderyjc.certificate.model.entity.User;
import top.coderyjc.certificate.model.vo.UserListVO;
import top.coderyjc.certificate.service.IUserService;
import top.coderyjc.certificate.util.MD5Util;
import top.coderyjc.certificate.util.Msg;

import java.util.Date;

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
    IUserService service;


    /**
     * 用户登录，无token
     * 2023.4.4 测试成功
     * @param userName 用户名
     * @param password 用户密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    ){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        wrapper.eq("password", MD5Util.getMD5(password));
        User user = service.getOne(wrapper);
        if(null == user){
            return Msg.fail();
        }
        return Msg.success();
    }

    /**
     * 用户列表
     * @param pn
     * @param size
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(
            @RequestParam(value = "current", defaultValue = "1") Integer pn,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "searchCondition", defaultValue = "{}") String searchCondition
        ){
    JSONObject condition = JSON.parseObject(searchCondition);
    IPage<UserListVO> iPage = service.listAll(pn, size, condition);
        return Msg.success().add("data", iPage);
}

    /**
     * 添加用户
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg addUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ){
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.getMD5(password));
        user.setCreateDate(new Date());
        boolean rst = service.save(user);
        return Msg.success().add("data", rst ? "添加成功" : "添加失败");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Msg deleteUser(
            @RequestParam(value = "id") String id
    ){
        boolean rst = service.removeById(id);
        return Msg.success().add("data", rst ? "删除成功" : "删除失败");
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public Msg changePassword(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword
    ){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = service.getOne(wrapper);
        if(user == null || !user.getPassword().equals(MD5Util.getMD5(oldPassword))){
            return Msg.fail().add("data", "旧密码错误");
        }
        if(user.getPassword().equals(MD5Util.getMD5(newPassword))){
            return Msg.fail().add("data", "新密码不能和旧密码一致");
        }
        user.setPassword(MD5Util.getMD5(newPassword));
        boolean rst = service.updateById(user);
        return Msg.success().add("data", rst ? "修改成功" : "修改失败");
    }

    /**
     * 检查用户名是否重复
     * @param username
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Msg checkUsername(@RequestParam(value = "username") String username){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Boolean rst = service.getOne(wrapper) != null;
        return Msg.success().add("data", rst);
    }
}