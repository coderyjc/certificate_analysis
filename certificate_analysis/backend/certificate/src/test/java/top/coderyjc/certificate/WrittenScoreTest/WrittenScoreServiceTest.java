package top.coderyjc.certificate.WrittenScoreTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.service.IWrittenScoreService;

import java.util.List;

/**
 * ClassName: WrittenScoreServiceTest
 * Package: top.coderyjc.certificate.WrittenScoreTest
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/5/2023 8:09 AM
 * @Version 1.0
 */

@SpringBootTest
public class WrittenScoreServiceTest {

    @Autowired
    IWrittenScoreService writtenScoreService;

    @Test
    public void listAll(){

        List<WrittenScore> scoreList = writtenScoreService.list();

//        只查询前10条数据
        for (int i = 0; i < 10; i++){
            System.out.println(scoreList.get(i));
        }
    }
}
