package top.coderyjc.certificate.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.coderyjc.certificate.model.dto.WrittenScoreStatisticDTO;
import top.coderyjc.certificate.model.entity.WrittenScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 笔试成绩 Mapper 接口
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface WrittenScoreMapper extends BaseMapper<WrittenScore> {
    List<String> listWorkAddress();

    List<Integer> listExamDate(Integer limit);

    List<WrittenScoreStatisticDTO> countOneColumnByYear(String year, String column);

    List<WrittenScoreStatisticDTO> countTwoColumnsByYear(String year, String column1, String column2);

    List<WrittenScoreStatisticDTO> countColumnByYears(String startYear, String endYear, String column);
}
