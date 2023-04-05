package top.coderyjc.certificate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.coderyjc.certificate.model.dto.WrittenScoreDTO;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.mapper.WrittenScoreMapper;
import top.coderyjc.certificate.service.IWrittenScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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


    @Override
    public IPage<WrittenScore> listAll(Integer pn,Integer limit,String gender,String name,String examId,String identificationId,
                                       String educationStatus,String educationPsychologyStatus,String professionalEthicStatus,
                                       Integer educationSort,Integer educationPsychologySort,Integer professionalEthicSort,
                                       String workAddress,String examDate) {

        IPage<WrittenScore> iPage = new Page<>(pn, limit);

        QueryWrapper<WrittenScore> wrapper = new QueryWrapper<>();

//        if(!gender.equals("")) wrapper.eq("sex", gender);
        if(!name.equals("")) wrapper.eq("name", name);
        if(!examId.equals("")) wrapper.eq("exam_id", examId);
        if(!examDate.equals("")) wrapper.like("exam_date", examDate);
        if(!identificationId.equals("")) wrapper.eq("identification_id", identificationId);
        if(!workAddress.equals("")) wrapper.eq("work_address", workAddress);

        if(!educationStatus.equals("")) wrapper.eq("education_status", educationStatus);
        if(!educationPsychologyStatus.equals("")) wrapper.eq("education_psychology_status", educationPsychologyStatus);
        if(!professionalEthicStatus.equals("")) wrapper.eq("professional_ethic_status", professionalEthicStatus);

        if(null != educationSort){
            if(educationSort == 1)
                wrapper.orderByAsc("education_score");
            else
                wrapper.orderByDesc("education_score");
        }
        if(null != educationPsychologySort){
            if(educationPsychologySort == 1)
                wrapper.orderByAsc("education_psychology_score");
            else
                wrapper.orderByDesc("education_psychology_score");
        }
        if(null != professionalEthicSort){
            if(professionalEthicSort == 1)
                wrapper.orderByAsc("professional_ethic_score");
            else
                wrapper.orderByDesc("professional_ethic_score");
        }

        return baseMapper.selectPage(iPage, wrapper);
    }

    @Override
    public List<String> listWorkAddress() {
        return baseMapper.listWorkAddress();
    }

    @Override
    public List<String> listExamYear() {
        return baseMapper.listExamYear();
    }
}
