package top.coderyjc.certificate.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.usermodel.Workbook;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.mapper.WrittenScoreMapper;
import top.coderyjc.certificate.service.IWrittenScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.util.DownloadUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

        if(!gender.equals("")) wrapper.eq("gender", gender);
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
    public void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition) {
        List<WrittenScore> list = null;
        QueryWrapper<WrittenScore> wrapper = new QueryWrapper<>();

//        查找数据
        if(exportId.size() > 0 && !exportId.get(0).equals("null")){
            list = baseMapper.selectBatchIds(exportId);
        }
        else if(condition.size() > 0) {
//        搜索
            if(condition.containsKey("name")) wrapper.eq("name", condition.get("name"));
            if(condition.containsKey("examId")) wrapper.eq("exam_id", condition.get("examId"));
            if(condition.containsKey("gender")) wrapper.eq("gender", condition.get("gender"));
            if(condition.containsKey("identificationId")) wrapper.eq("identification_id", condition.get("identificationId"));
            if(condition.containsKey("educationStatus")) wrapper.eq("education_score", condition.get("educationStatus"));
            if(condition.containsKey("educationPsychologyStatus")) wrapper.eq("education_psychology_status", condition.get("educationPsychologyStatus"));
            if(condition.containsKey("professionalEthicStatus")) wrapper.eq("professional_ethic_status", condition.get("professionalEthicStatus"));
            if(condition.containsKey("educationSort")){
                if ((Integer) condition.get("educationSort") == 1) {
                    wrapper.orderByAsc("education_score");
                } else {
                    wrapper.orderByDesc("education_score");
                }
            }
            if(condition.containsKey("educationPsychologySort")){
                if ((Integer) condition.get("educationPsychologySort") == 1) {
                    wrapper.orderByAsc("education_psychology_score");
                } else {
                    wrapper.orderByDesc("education_psychology_score");
                }
            }
            if(condition.containsKey("professionalEthicSort")){
                if ((Integer) condition.get("professionalEthicSort") == 1) {
                    wrapper.orderByAsc("professional_ethic_score");
                } else {
                    wrapper.orderByDesc("professional_ethic_score");
                }
            }
            if(condition.containsKey("workAddress"))  wrapper.eq("work_address", condition.get("workAddress"));
            if(condition.containsKey("examDate")) wrapper.eq("exam_date", condition.get("examDate"));
            list = baseMapper.selectList(wrapper);
        }

//        筛选列名
        List<ExcelExportEntity> beanList = new ArrayList<>();
        if(exportColumn.contains("姓名")) beanList.add(new ExcelExportEntity("姓名", "name"));
        if(exportColumn.contains("性别")) beanList.add(new ExcelExportEntity("性别", "gender"));
        if(exportColumn.contains("准考证号")) beanList.add(new ExcelExportEntity("准考证号", "examId"));
        if(exportColumn.contains("身份证号")) beanList.add(new ExcelExportEntity("身份证号", "identificationId"));
        if(exportColumn.contains("教育学成绩")) beanList.add(new ExcelExportEntity("教育学成绩", "educationScore"));
        if(exportColumn.contains("教育心理学成绩")) beanList.add(new ExcelExportEntity("教育心理学成绩", "educationPsychologyScore"));
        if(exportColumn.contains("职业道德修养和高等教育法规成绩")) beanList.add(new ExcelExportEntity("职业道德修养和高等教育法规成绩", "professionalEthicScore"));
        if(exportColumn.contains("教育学考试状态")) beanList.add(new ExcelExportEntity("教育学考试状态", "educationStatus"));
        if(exportColumn.contains("教育心理学考试状态")) beanList.add(new ExcelExportEntity("教育心理学考试状态", "educationPsychologyStatus"));
        if(exportColumn.contains("职业道德修养和高等教育法规状态")) beanList.add(new ExcelExportEntity("职业道德修养和高等教育法规状态", "professionalEthicStatus"));
        if(exportColumn.contains("工作单位")) beanList.add(new ExcelExportEntity("工作单位", "workAddress"));
        if(exportColumn.contains("考试时间")) beanList.add(new ExcelExportEntity("考试时间", "examDate"));

        DownloadUtil.downloadExcel(response, new ExportParams(), beanList, WrittenScore.class, list);
    }



    //    @Override
//    public List<String> listWorkAddress() {
//        return baseMapper.listWorkAddress();
//    }
//
//    @Override
//    public List<String> listExamYear() {
//        return baseMapper.listExamYear();
//    }
}
