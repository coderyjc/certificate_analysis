package top.coderyjc.certificate.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.dto.IdentificationStatisticDTO;
import top.coderyjc.certificate.model.entity.Identification;
import com.baomidou.mybatisplus.extension.service.IService;
import top.coderyjc.certificate.model.vo.ProvinceCountVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 认定表 服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface IIdentificationService extends IService<Identification> {

    IPage<Identification> listAll(Integer pn, Integer limit, JSONObject condition);

    void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition);

    String importExcel(MultipartFile file) throws Exception;

    List<String> listAffirmBatch(Integer limit);

    List<IdentificationStatisticDTO> statisticIdentification(String affirmBatch, String affirmBatchStart, String affirmBatchEnd, List<String> statisticItemList);

    void exportStatisticExcel(HttpServletResponse response, String affirmBatch, String affirmBatchStart, String affirmBatchEnd, List<String> statisticItemList);

    List<ProvinceCountVO> listCityCount();
}
