package top.coderyjc.certification;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.coderyjc.certification.entity.Certification;
import top.coderyjc.certification.mapper.CertificationMapper;

import java.sql.Wrapper;

/**
 * ClassName: CertificationMapperTest
 * Package: top.coderyjc.certification.MapperTest
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/2/2023 11:43 AM
 * @Version 1.0
 */

@SpringBootTest
public class CertificationMapperTest {

    @Autowired
    private CertificationMapper mapper;

    @Test
    public void testSelectList(){
        QueryWrapper wrapper = new QueryWrapper<Certification>();
        wrapper.eq("专业", "中医");
        mapper.selectList(wrapper).forEach(System.out::println);
    }

}
