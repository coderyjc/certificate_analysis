package top.coderyjc.certificate.UserTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.coderyjc.certificate.mapper.UserMapper;
import top.coderyjc.certificate.model.entity.User;
import top.coderyjc.certificate.util.MD5Util;

import java.util.Date;

/**
 * ClassName: UserInsertTest
 * Package: top.coderyjc.certificate.UserTest
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/2/2023 7:27 PM
 * @Version 1.0
 */

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insertUser(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword(MD5Util.getMD5("123456"));
        user.setCreateDate(new Date());
        userMapper.insert(user);
        System.out.println(user + "插入成功");
    }
}
