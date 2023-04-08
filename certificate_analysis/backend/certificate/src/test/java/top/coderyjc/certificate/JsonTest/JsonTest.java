package top.coderyjc.certificate.JsonTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: JsonTest
 * Package: top.coderyjc.certificate.JsonTest
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 4:12 PM
 * @Version 1.0
 */

@SpringBootTest
public class JsonTest {


    @Test
    public void testNullString(){
//       查看空字符串的大小
        String str = "{}";
        JSONObject json = JSONObject.parseObject(str);
        System.out.println(json.size());
    }

}
