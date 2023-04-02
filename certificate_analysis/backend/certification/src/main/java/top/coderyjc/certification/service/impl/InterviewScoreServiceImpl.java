package top.coderyjc.certification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.coderyjc.certification.entity.InterviewScore;
import top.coderyjc.certification.mapper.InterviewScoreMapper;
import top.coderyjc.certification.service.IInterviewScoreService;
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
public class InterviewScoreServiceImpl extends ServiceImpl<InterviewScoreMapper, InterviewScore> implements IInterviewScoreService {


    @Autowired
    InterviewScoreMapper mapper;

}
