package top.coderyjc.certificate.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.dto.CertificationStatisticDTO;
import top.coderyjc.certificate.model.entity.Certification;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 证书表哦 服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface ICertificationService extends IService<Certification> {

    IPage<Certification> listAll(Integer pn, Integer limit, JSONObject condition);

    void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition);

    String importExcel(MultipartFile file) throws Exception;

    List<String> listInterviewYear();

    List<Integer> listAllInterviewYears(int limit);

    List<String> listAllValidateYears(int limit);

    List<CertificationStatisticDTO> statisticCertification(String interviewYear, String interviewStartYear, String interviewEndYear, List<String> statisticItemList);

    void exportStatisticExcel(HttpServletResponse response, String interviewYear, String interviewStartYear, String interviewEndYear, List<String> statisticItemList);
}
