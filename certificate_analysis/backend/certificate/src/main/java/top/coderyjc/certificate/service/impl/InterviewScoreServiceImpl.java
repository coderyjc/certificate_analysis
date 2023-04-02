package top.coderyjc.certificate.service.impl;

import top.coderyjc.certificate.model.entity.InterviewScore;
import top.coderyjc.certificate.mapper.InterviewScoreMapper;
import top.coderyjc.certificate.service.IInterviewScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 面试成绩 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class InterviewScoreServiceImpl extends ServiceImpl<InterviewScoreMapper, InterviewScore> implements IInterviewScoreService {

}
