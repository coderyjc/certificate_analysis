package top.coderyjc.certificate.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.entity.WrittenScore;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 笔试成绩 服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface IWrittenScoreService extends IService<WrittenScore> {

    IPage<WrittenScore> listAll(Integer pn, Integer limit, String gender, String name, String examId, String identificationId,
                                String educationStatus, String educationPsychologyStatus, String professionalEthicStatus,
                                Integer educationSort, Integer educationPsychologySort, Integer professionalEthicSort,
                                String workAddress, String examDate);


    void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition);

    String importExcel(MultipartFile file) throws Exception;

//    List<String> listWorkAddress();
//
//    List<String> listExamYear();

}
