package top.coderyjc.certificate.service.impl;

import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.mapper.WrittenScoreMapper;
import top.coderyjc.certificate.service.IWrittenScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 笔试成绩 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class WrittenScoreServiceImpl extends ServiceImpl<WrittenScoreMapper, WrittenScore> implements IWrittenScoreService {

}
