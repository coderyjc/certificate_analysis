package top.coderyjc.certificate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.coderyjc.certificate.model.dto.WrittenScoreDTO;
import top.coderyjc.certificate.model.entity.WrittenScore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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



    List<String> listWorkAddress();

    List<String> listExamYear();
}
