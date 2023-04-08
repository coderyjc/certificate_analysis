package top.coderyjc.certificate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.entity.InterviewScore;
import top.coderyjc.certificate.mapper.InterviewScoreMapper;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.service.IInterviewScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Override
    public IPage<InterviewScore> listAll(Integer pn, Integer limit, JSONObject condition) {
        IPage<InterviewScore> iPage = new Page<>(pn, limit);
        QueryWrapper<InterviewScore> wrapper = new QueryWrapper<>();

        if(condition.size() > 0){
            if(!condition.get("name").equals("")) wrapper.eq("name", condition.get("name"));
            if(!condition.get("examAddress").equals("")) wrapper.like("exam_address", condition.get("examAddress"));
            if(!condition.get("identificationId").equals("")) wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("workAddress").equals("")) wrapper.eq("work_address", condition.get("workAddress"));
            if(!condition.get("applyMajor").equals("")) wrapper.eq("apply_major", condition.get("applyMajor"));
            if(!condition.get("level").equals("")) wrapper.eq("level", condition.get("level"));
            if(!condition.get("examDate").equals("")) wrapper.like("exam_date", condition.get("examDate"));
            if(!condition.get("gender").equals("")) wrapper.eq("gender", condition.get("gender"));
        }

        return baseMapper.selectPage(iPage, wrapper);
    }

    @Override
    public void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition) {

    }

    @Override
    public String importExcel(MultipartFile file) {
        return null;
    }
}
