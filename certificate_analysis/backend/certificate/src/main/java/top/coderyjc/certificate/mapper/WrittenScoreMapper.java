package top.coderyjc.certificate.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    List<String> listExamYear();
}
