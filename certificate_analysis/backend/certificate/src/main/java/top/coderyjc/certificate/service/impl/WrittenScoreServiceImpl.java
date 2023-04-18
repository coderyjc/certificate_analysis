package top.coderyjc.certificate.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.dto.InterviewScoreStatisticDTO;
import top.coderyjc.certificate.model.dto.WrittenScoreImportDTO;
import top.coderyjc.certificate.model.dto.WrittenScoreStatisticDTO;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.mapper.WrittenScoreMapper;
import top.coderyjc.certificate.service.IWrittenScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.util.DownloadUtil;
import top.coderyjc.certificate.util.LineHumpUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            if(!condition.get("name").equals("")) wrapper.eq("name", condition.get("name"));
            if(!condition.get("examId").equals("")) wrapper.eq("exam_id", condition.get("examId"));
            if(!condition.get("gender").equals("")) wrapper.eq("gender", condition.get("gender"));
            if(!condition.get("identificationId").equals("")) wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("educationStatus").equals("")) wrapper.eq("education_score", condition.get("educationStatus"));
            if(!condition.get("educationPsychologyStatus").equals("")) wrapper.eq("education_psychology_status", condition.get("educationPsychologyStatus"));
            if(!condition.get("professionalEthicStatus").equals("")) wrapper.eq("professional_ethic_status", condition.get("professionalEthicStatus"));
            if(!condition.get("educationSort").equals("")){
                if ((Integer) condition.get("educationSort") == 1) {
                    wrapper.orderByAsc("education_score");
                } else {
                    wrapper.orderByDesc("education_score");
                }
            }
            if(!condition.get("educationPsychologySort").equals("")){
                if ((Integer) condition.get("educationPsychologySort") == 1) {
                    wrapper.orderByAsc("education_psychology_score");
                } else {
                    wrapper.orderByDesc("education_psychology_score");
                }
            }
            if(!condition.get("professionalEthicSort").equals("")){
                if ((Integer) condition.get("professionalEthicSort") == 1) {
                    wrapper.orderByAsc("professional_ethic_score");
                } else {
                    wrapper.orderByDesc("professional_ethic_score");
                }
            }
            if(!condition.get("workAddress").equals(""))  wrapper.eq("work_address", condition.get("workAddress"));
            if(!condition.get("examDate").equals("")) wrapper.eq("exam_date", condition.get("examDate"));
            list = baseMapper.selectList(wrapper);
        }

//        筛选列名
        List<ExcelExportEntity> beanList = new ArrayList<>();
        if(exportColumn.contains("姓名")) {
            ExcelExportEntity nameEntity = new ExcelExportEntity("姓名", "name");
            beanList.add(nameEntity);
        }
        if(exportColumn.contains("性别")) {
            ExcelExportEntity genderEntity = new ExcelExportEntity("性别", "gender");
            genderEntity.setWidth(8);
            genderEntity.setReplace(new String[]{ "男_1", "女_0" });
            beanList.add(genderEntity);
        }
        if(exportColumn.contains("准考证号")) {
            ExcelExportEntity examIdEntity = new ExcelExportEntity("准考证号", "examId");
            examIdEntity.setWidth(12);
            beanList.add(examIdEntity);
        }
        if(exportColumn.contains("身份证号")) {
            ExcelExportEntity identificationIdEntity = new ExcelExportEntity("身份证号", "identificationId");
            identificationIdEntity.setWidth(20);
            beanList.add(identificationIdEntity);
        }
        if(exportColumn.contains("教育学成绩")) {
            ExcelExportEntity educationScoreEntity = new ExcelExportEntity("教育学成绩", "educationScore");
            educationScoreEntity.setWidth(12);
            educationScoreEntity.setReplace(new String[]{"未报名考试_-1"});
            beanList.add(educationScoreEntity);
        }
        if(exportColumn.contains("教育心理学成绩")) {
            ExcelExportEntity educationPsychologyScoreEntity = new ExcelExportEntity("教育心理学成绩", "educationPsychologyScore");
            educationPsychologyScoreEntity.setWidth(15);
            educationPsychologyScoreEntity.setReplace(new String[]{"未报名考试_-1"});
            beanList.add(educationPsychologyScoreEntity);
        }
        if(exportColumn.contains("职业道德修养和高等教育法规成绩")) {
            ExcelExportEntity professionalEthicScoreEntity = new ExcelExportEntity("职业道德修养和高等教育法规成绩", "professionalEthicScore");
            professionalEthicScoreEntity.setWidth(30);
            professionalEthicScoreEntity.setReplace(new String[]{"未报名考试_-1"});
            beanList.add(professionalEthicScoreEntity);
        }
        if(exportColumn.contains("教育学考试状态")) {
            ExcelExportEntity educationStatusEntity = new ExcelExportEntity("教育学考试状态", "educationStatus");
            educationStatusEntity.setWidth(15);
            beanList.add(educationStatusEntity);
        }
        if(exportColumn.contains("教育心理学考试状态")) {
            ExcelExportEntity educationPsychologyStatusEntity = new ExcelExportEntity("教育心理学考试状态", "educationPsychologyStatus");
            educationPsychologyStatusEntity.setWidth(20);
            beanList.add(educationPsychologyStatusEntity);
        }
        if(exportColumn.contains("职业道德修养和高等教育法规状态")) {
            ExcelExportEntity professionalEthicStatusEntity = new ExcelExportEntity("职业道德修养和高等教育法规状态", "professionalEthicStatus");
            professionalEthicStatusEntity.setWidth(30);
            beanList.add(professionalEthicStatusEntity);
        }
        if(exportColumn.contains("工作单位")) {
            ExcelExportEntity workAddressEntity = new ExcelExportEntity("工作单位", "workAddress");
            workAddressEntity.setWidth(20);
            beanList.add(workAddressEntity);
        }
        if(exportColumn.contains("考试时间")) {
            ExcelExportEntity examDateEntity = new ExcelExportEntity("考试时间", "examDate");
            examDateEntity.setWidth(10);
            examDateEntity.setSuffix("年");
            beanList.add(examDateEntity);
        }
        DownloadUtil.downloadExcel(response, new ExportParams(), beanList, WrittenScore.class, list);
    }

    @Override
    public String importExcel(MultipartFile file) throws Exception {

        //需保存到数据库的记录
        List<WrittenScore> resultData = new ArrayList<>();
        ImportParams params = new ImportParams();
        // 表头设置为首行
        params.setHeadRows(1);
        params.setTitleRows(0);

        ExcelImportResult<WrittenScoreImportDTO> result;
        result = ExcelImportUtil.importExcelMore(file.getInputStream(), WrittenScoreImportDTO.class, params);

//        List<WrittenScoreImportDTO> failList = result.getFailList();
        List<WrittenScoreImportDTO> correctResultList = result.getList();

        for (WrittenScoreImportDTO writtenScoreImportDTO : correctResultList) {
            WrittenScore writtenScore = new WrittenScore();
            BeanUtils.copyProperties(writtenScoreImportDTO, writtenScore);
            writtenScore.setGender(Integer.parseInt(writtenScore.getIdentificationId().substring(16,17)) % 2);
            resultData.add(writtenScore);
        }

        saveBatch(resultData);
        return "导入成功";
    }

//    @Override
//    public List<String> listWorkAddress() {
//        return baseMapper.listWorkAddress();
//    }
//
    @Override
    public List<Integer> listExamDate(Integer limit) {
        return baseMapper.listExamDate(limit);
    }

    @Override
    public List<WrittenScoreStatisticDTO> statisticWrittenScore(String year, String startYear, String endYear, List<String> statisticItemList) {
        List<WrittenScoreStatisticDTO> list = null;
        for (int i = 0; i < statisticItemList.size(); i++){
            statisticItemList.set(i, LineHumpUtil.humpToLine(statisticItemList.get(i)));
        }
        if(!year.equals("")){
            if(statisticItemList.size() == 1) list = baseMapper.countOneColumnByYear(year, statisticItemList.get(0));
            else list = baseMapper.countTwoColumnsByYear(year, statisticItemList.get(0), statisticItemList.get(1));
        } else {
            String column = statisticItemList.get(0).equals("exam_date") ? statisticItemList.get(1) : statisticItemList.get(0);
            list = baseMapper.countColumnByYears(startYear, endYear, column);
        }
        return list;
    }

    @Override
    public void exportStatisticExcel(HttpServletResponse response, String year, String startYear, String endYear, List<String> statisticItemList) {

        //      直接复用拿到数据
        List<WrittenScoreStatisticDTO> list = statisticWrittenScore(year, startYear, endYear, statisticItemList);

//      导出数据
        List<ExcelExportEntity> exportEntityList = new ArrayList<>();

        if(statisticItemList.contains("exam_date")){
            ExcelExportEntity examDateEntity = new ExcelExportEntity("考试时间", "examDate");
            examDateEntity.setWidth(10);
            exportEntityList.add(examDateEntity);
        }
        if(statisticItemList.contains("gender")){
            ExcelExportEntity genderEntity = new ExcelExportEntity("性别", "gender");
            genderEntity.setWidth(8);
            genderEntity.setReplace(new String[]{ "男_1", "女_0" });
            exportEntityList.add(genderEntity);
        }
        if(statisticItemList.contains("education_score")) {
            ExcelExportEntity educationScoreEntity = new ExcelExportEntity("教育学成绩", "educationScore");
            educationScoreEntity.setWidth(12);
            educationScoreEntity.setReplace(new String[]{"未报名考试_-1"});
            exportEntityList.add(educationScoreEntity);
        }
        if(statisticItemList.contains("education_psychology_score")) {
            ExcelExportEntity educationPsychologyScoreEntity = new ExcelExportEntity("教育心理学成绩", "educationPsychologyScore");
            educationPsychologyScoreEntity.setWidth(15);
            educationPsychologyScoreEntity.setReplace(new String[]{"未报名考试_-1"});
            exportEntityList.add(educationPsychologyScoreEntity);
        }
        if(statisticItemList.contains("professional_ethic_score")) {
            ExcelExportEntity professionalEthicScoreEntity = new ExcelExportEntity("职业道德修养和高等教育法规成绩", "professionalEthicScore");
            professionalEthicScoreEntity.setWidth(30);
            professionalEthicScoreEntity.setReplace(new String[]{"未报名考试_-1"});
            exportEntityList.add(professionalEthicScoreEntity);
        }
        if(statisticItemList.contains("education_status")) {
            ExcelExportEntity educationStatusEntity = new ExcelExportEntity("教育学考试状态", "educationStatus");
            educationStatusEntity.setWidth(15);
            exportEntityList.add(educationStatusEntity);
        }
        if(statisticItemList.contains("education_psychology_status")) {
            ExcelExportEntity educationPsychologyStatusEntity = new ExcelExportEntity("教育心理学考试状态", "educationPsychologyStatus");
            educationPsychologyStatusEntity.setWidth(20);
            exportEntityList.add(educationPsychologyStatusEntity);
        }
        if(statisticItemList.contains("professional_ethic_status")) {
            ExcelExportEntity professionalEthicStatusEntity = new ExcelExportEntity("职业道德修养和高等教育法规状态", "professionalEthicStatus");
            professionalEthicStatusEntity.setWidth(30);
            exportEntityList.add(professionalEthicStatusEntity);
        }
        if(statisticItemList.contains("work_address")) {
            ExcelExportEntity workAddressEntity = new ExcelExportEntity("工作单位", "workAddress");
            workAddressEntity.setWidth(20);
            exportEntityList.add(workAddressEntity);
        }

//        数量
        ExcelExportEntity countEntity = new ExcelExportEntity("数量", "count");
        exportEntityList.add(countEntity);

        DownloadUtil.downloadExcel(response, new ExportParams(), exportEntityList, InterviewScoreStatisticDTO.class, list);

    }

}
