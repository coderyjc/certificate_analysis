package top.coderyjc.certificate.mapper;

import top.coderyjc.certificate.model.dto.IdentificationDTO;
import top.coderyjc.certificate.model.dto.IdentificationStatisticDTO;
import top.coderyjc.certificate.model.entity.Identification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 认定表 Mapper 接口
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */

public interface IdentificationMapper extends BaseMapper<Identification> {

    List<String> listAffirmBatch(Integer limit);

    List<IdentificationStatisticDTO> countOneColumnByYear(String affirmBatch, String column);

    List<IdentificationStatisticDTO> countTwoColumnsByYear(String affirmBatch, String column1, String column2);

    List<IdentificationStatisticDTO> countColumnByYears(String affirmBatchStart, String affirmBatchEnd, String column);
}
