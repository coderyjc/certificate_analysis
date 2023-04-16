package top.coderyjc.certificate.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.entity.InterviewScore;
import com.baomidou.mybatisplus.extension.service.IService;
import top.coderyjc.certificate.model.entity.WrittenScore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 面试成绩 服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface IInterviewScoreService extends IService<InterviewScore> {

    IPage<InterviewScore> listAll(Integer pn, Integer limit, JSONObject condition);

    void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition);

    String importExcel(MultipartFile file) throws Exception;

    List<String> listExamDate();
}
