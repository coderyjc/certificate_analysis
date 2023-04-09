package top.coderyjc.certificate.StringTest;

import org.junit.jupiter.api.Test;

/**
 * ClassName: StringTest
 * Package: top.coderyjc.certificate.StringTest
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/9/2023 7:47 AM
 * @Version 1.0
 */
public class StringTest {

    @Test
    public void getSexFromIdentification(){
        String id = "111111111111111318";
        System.out.println(id.charAt(16));
        int i = Integer.parseInt(String.valueOf(id.charAt(16))) % 2;
    }

}
