package top.coderyjc.certification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.coderyjc.certification.entity.WrittenScore;
import top.coderyjc.certification.mapper.WrittenScoreMapper;
import top.coderyjc.certification.service.IWrittenScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class WrittenScoreServiceImpl extends ServiceImpl<WrittenScoreMapper, WrittenScore> implements IWrittenScoreService {

    @Autowired
    WrittenScoreMapper mapper;

}
