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
import top.coderyjc.certificate.model.dto.InterviewScoreImportDTO;
import top.coderyjc.certificate.model.dto.WrittenScoreImportDTO;
import top.coderyjc.certificate.model.entity.InterviewScore;
import top.coderyjc.certificate.mapper.InterviewScoreMapper;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.service.IInterviewScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.util.DownloadUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
            if(!condition.get("examAddress").equals("")) wrapper.eq("exam_address", condition.get("examAddress"));
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
        List<InterviewScore> list = null;
        QueryWrapper<InterviewScore> wrapper = new QueryWrapper<>();

//      查找数据
        if(exportId.size() > 0){
//            按照id导出
            list = baseMapper.selectBatchIds(exportId);
        }
        else if(condition.size() > 0){
            if(!condition.get("examAddress").equals("")) wrapper.like("exam_address", condition.get("examAddress"));
            if(!condition.get("name").equals("")) wrapper.eq("name", condition.get("name"));
            if(!condition.get("gender").equals("")) wrapper.eq("gender", condition.get("gender"));
            if(!condition.get("identificationId").equals("")) wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("workAddress").equals("")) wrapper.eq("work_address", condition.get("workAddress"));
            if(!condition.get("applyMajor").equals("")) wrapper.eq("apply_major", condition.get("applyMajor"));
            if(!condition.get("level").equals("")) wrapper.eq("level", condition.get("level"));
            if(!condition.get("examDate").equals("")) wrapper.like("exam_date", condition.get("examDate"));
            list = baseMapper.selectList(wrapper);
        }

//        导出的列名
        List<ExcelExportEntity> exportEntityList = new ArrayList<>();
        if(exportColumn.contains("测试点")){
            ExcelExportEntity examAddressEntity = new ExcelExportEntity("测试点", "examAddress");
            examAddressEntity.setWidth(9);
            exportEntityList.add(examAddressEntity);
        }
        if(exportColumn.contains("姓名")){
            ExcelExportEntity nameEntity = new ExcelExportEntity("姓名", "name");
            nameEntity.setWidth(10);
            exportEntityList.add(nameEntity);
        }
        if(exportColumn.contains("性别")){
            ExcelExportEntity genderEntity = new ExcelExportEntity("性别", "gender");
            genderEntity.setReplace(new String[]{ "男_1", "女_0" });
            genderEntity.setWidth(8);
            exportEntityList.add(genderEntity);
        }
        if(exportColumn.contains("身份证号")){
            ExcelExportEntity identificationIdEntity = new ExcelExportEntity("身份证号", "identificationId");
            identificationIdEntity.setWidth(25);
            exportEntityList.add(identificationIdEntity);
        }
        if(exportColumn.contains("工作单位")){
            ExcelExportEntity workAddressEntity = new ExcelExportEntity("工作单位", "workAddress");
            workAddressEntity.setWidth(35);
            exportEntityList.add(workAddressEntity);
        }
        if(exportColumn.contains("申请专业")){
            ExcelExportEntity applyMajorEntity = new ExcelExportEntity("申请专业", "applyMajor");
            applyMajorEntity.setWidth(20);
            exportEntityList.add(applyMajorEntity);
        }
        if(exportColumn.contains("等级")){
            ExcelExportEntity levelEntity = new ExcelExportEntity("等级", "level");
            levelEntity.setWidth(5);
            exportEntityList.add(levelEntity);
        }
        if(exportColumn.contains("考试时间")){
            ExcelExportEntity examDateEntity = new ExcelExportEntity("考试时间", "examDate");
            examDateEntity.setWidth(10);
            examDateEntity.setSuffix("年");
            exportEntityList.add(examDateEntity);
        }
        DownloadUtil.downloadExcel(response, new ExportParams(), exportEntityList, InterviewScore.class, list);
    }

    @Override
    public String importExcel(MultipartFile file) throws Exception {

        //需保存到数据库的工时记录
        List<InterviewScore> resultData = new ArrayList<>();
        ImportParams params = new ImportParams();
        // 表头设置为首行
        params.setHeadRows(1);
        params.setTitleRows(0);

        ExcelImportResult<InterviewScoreImportDTO> result;
        result = ExcelImportUtil.importExcelMore(file.getInputStream(), InterviewScoreImportDTO.class, params);

        List<InterviewScoreImportDTO> correctResultList = result.getList();

        for (InterviewScoreImportDTO interviewScoreImportDTO : correctResultList) {
            InterviewScore interviewScore = new InterviewScore();
            BeanUtils.copyProperties(interviewScoreImportDTO, interviewScore);
            interviewScore.setGender(Integer.parseInt(interviewScore.getIdentificationId().substring(16,17)) % 2);
            resultData.add(interviewScore);
        }

        saveBatch(resultData);
        return "导入成功";
    }
}
