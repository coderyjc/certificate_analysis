package top.coderyjc.certificate.mapper;

import top.coderyjc.certificate.model.entity.WrittenStatistic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-23
 */
public interface WrittenStatisticMapper extends BaseMapper<WrittenStatistic> {

    void updateEducation(String year, String educationPassScore);

    void updatePsychology(String year, String psychologyPassScore);

    void updateEthic(String year, String ethicPassScore);
}
