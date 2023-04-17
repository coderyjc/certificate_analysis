package top.coderyjc.certificate.mapper;

import top.coderyjc.certificate.model.dto.InterviewScoreStatisticDTO;
import top.coderyjc.certificate.model.entity.InterviewScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.coderyjc.certificate.model.vo.ColumnCountVO;

import java.util.List;

/**
 * <p>
 * 面试成绩 Mapper 接口
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface InterviewScoreMapper extends BaseMapper<InterviewScore> {

    List<String> listExamDate();

    List<Integer> listAllYears(Integer limit);

    List<ColumnCountVO> countOneColumnCountByYear(String year, String column);

    List<ColumnCountVO> countTwoColumnsCountByYear(String year, String column1, String column2);

    List<InterviewScoreStatisticDTO> countColumnByYears(String startYear, String endYear, String column);

    List<InterviewScoreStatisticDTO> countOneColumnByYear(String year, String column);

    List<InterviewScoreStatisticDTO> countTwoColumnsByYear(String year, String column1, String column2);
}
