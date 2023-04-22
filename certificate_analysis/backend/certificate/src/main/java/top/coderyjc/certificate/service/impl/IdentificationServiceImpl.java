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
import top.coderyjc.certificate.model.dto.IdentificationDTO;
import top.coderyjc.certificate.model.dto.IdentificationStatisticDTO;
import top.coderyjc.certificate.model.entity.Certification;
import top.coderyjc.certificate.model.entity.Identification;
import top.coderyjc.certificate.mapper.IdentificationMapper;
import top.coderyjc.certificate.model.vo.ProvinceCountVO;
import top.coderyjc.certificate.service.IIdentificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.util.DownloadUtil;
import top.coderyjc.certificate.util.LineHumpUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 认定表 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class IdentificationServiceImpl extends ServiceImpl<IdentificationMapper, Identification> implements IIdentificationService {

    @Override
    public IPage<Identification> listAll(Integer pn, Integer limit, JSONObject condition) {

        IPage<Identification> iPage = new Page<>(pn, limit);
        QueryWrapper<Identification> wrapper = new QueryWrapper<>();

        if(condition.size() > 0){
            if(!condition.get("name").equals("")) wrapper.eq("name", condition.get("name"));
            if(!condition.get("gender").equals("")) wrapper.eq("gender", condition.get("gender"));
            if(!condition.get("majorType").equals("")) wrapper.eq("major_type", condition.get("majorType"));
            if(!condition.get("graduationSchool").equals("")) wrapper.eq("graduation_school", condition.get("graduationSchool"));
            if(!condition.get("major").equals("")) wrapper.eq("major", condition.get("major"));
            if(!condition.get("highestEducationBackground").equals("")) wrapper.eq("highest_education_background", condition.get("highestEducationBackground"));
            if(!condition.get("qualificationType").equals("")) wrapper.eq("qualification_type", condition.get("qualificationType"));
            if(!condition.get("identificationId").equals("")) wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("highestDegree").equals("")) wrapper.eq("highest_degree", condition.get("highestDegree"));
            if(!condition.get("affirmBatch").equals("")) wrapper.eq("affirm_batch", condition.get("affirmBatch"));
            if(!condition.get("confirmAddress").equals("")) wrapper.eq("confirm_address", condition.get("confirmAddress"));
            if(!condition.get("affirmInstitution").equals("")) wrapper.eq("affirm_institution", condition.get("affirmInstitution"));
            if(!condition.get("examType").equals("")) wrapper.eq("exam_type", condition.get("examType"));
            if(!condition.get("organizationType").equals("")) wrapper.eq("organization_type", condition.get("organizationType"));
            if(!condition.get("subject").equals("")) wrapper.eq("subject", condition.get("subject"));
            if(!condition.get("certificationId").equals("")) wrapper.eq("certification_id", condition.get("certificationId"));
            if(!condition.get("city").equals("")) wrapper.eq("city", condition.get("city"));
        }

        return baseMapper.selectPage(iPage, wrapper);
    }

    @Override
    public void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition) {

        List<Identification> list = null;
        QueryWrapper<Identification> wrapper = new QueryWrapper<>();

//      查找数据
        if(exportId.size() > 0){
//            按照id导出
            list = baseMapper.selectBatchIds(exportId);
        }
        else if(condition.size() > 0){
            if(!condition.get("name").equals("")) wrapper.eq("name", condition.get("name"));
            if(!condition.get("gender").equals("")) wrapper.eq("gender", condition.get("gender"));
            if(!condition.get("majorType").equals("")) wrapper.eq("major_type", condition.get("majorType"));
            if(!condition.get("graduationSchool").equals("")) wrapper.eq("graduation_school", condition.get("graduationSchool"));
            if(!condition.get("major").equals("")) wrapper.eq("major", condition.get("major"));
            if(!condition.get("highestEducationBackground").equals("")) wrapper.eq("highest_education_background", condition.get("highestEducationBackground"));
            if(!condition.get("qualificationType").equals("")) wrapper.eq("qualification_type", condition.get("qualificationType"));
            if(!condition.get("identificationId").equals("")) wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("highestDegree").equals("")) wrapper.eq("highest_degree", condition.get("highestDegree"));
            if(!condition.get("affirmBatch").equals("")) wrapper.eq("affirm_batch", condition.get("affirmBatch"));
            if(!condition.get("confirmAddress").equals("")) wrapper.eq("confirm_address", condition.get("confirmAddress"));
            if(!condition.get("affirmInstitution").equals("")) wrapper.eq("affirm_institution", condition.get("affirmInstitution"));
            if(!condition.get("examType").equals("")) wrapper.eq("exam_type", condition.get("examType"));
            if(!condition.get("organizationType").equals("")) wrapper.eq("organization_type", condition.get("organizationType"));
            if(!condition.get("subject").equals("")) wrapper.eq("subject", condition.get("subject"));
            if(!condition.get("certificationId").equals("")) wrapper.eq("certification_id", condition.get("certificationId"));
            if(!condition.get("city").equals("")) wrapper.eq("city", condition.get("city"));
            list = baseMapper.selectList(wrapper);
        }

//        导出的列名
        List<ExcelExportEntity> exportEntityList = new ArrayList<>();

        if(exportColumn.contains("姓名")){
            ExcelExportEntity nameEntity = new ExcelExportEntity("姓名", "name");
            nameEntity.setWidth(8);
            exportEntityList.add(nameEntity);
        }
        if(exportColumn.contains("性别")){
            ExcelExportEntity genderEntity = new ExcelExportEntity("性别", "gender");
            genderEntity.setWidth(8);
            genderEntity.setReplace(new String[]{ "男_1", "女_0" });
            exportEntityList.add(genderEntity);
        }
        if(exportColumn.contains("专业类别")){
            ExcelExportEntity majorTypeEntity = new ExcelExportEntity("专业类别", "majorType");
            majorTypeEntity.setWidth(8);
            exportEntityList.add(majorTypeEntity);
        }
        if(exportColumn.contains("毕业学校")){
            ExcelExportEntity graduationSchoolEntity = new ExcelExportEntity("毕业学校", "graduationSchool");
            graduationSchoolEntity.setWidth(25);
            exportEntityList.add(graduationSchoolEntity);
        }
        if(exportColumn.contains("所学专业")){
            ExcelExportEntity majorEntity = new ExcelExportEntity("所学专业", "major");
            majorEntity.setWidth(35);
            exportEntityList.add(majorEntity);
        }
        if(exportColumn.contains("最高学历")){
            ExcelExportEntity highestEducationBackgroundEntity = new ExcelExportEntity("最高学历", "highestEducationBackground");
            highestEducationBackgroundEntity.setWidth(10);
            exportEntityList.add(highestEducationBackgroundEntity);
        }
        if(exportColumn.contains("资格种类")){
            ExcelExportEntity qualificationTypeEntity = new ExcelExportEntity("资格种类", "qualificationType");
            qualificationTypeEntity.setWidth(10);
            exportEntityList.add(qualificationTypeEntity);
        }
        if(exportColumn.contains("证件号码")){
            ExcelExportEntity identificationIdEntity = new ExcelExportEntity("证件号码", "identificationId");
            identificationIdEntity.setWidth(25);
            exportEntityList.add(identificationIdEntity);
        }
        if(exportColumn.contains("最高学位")){
            ExcelExportEntity highestDegreeEntity = new ExcelExportEntity("最高学位", "highestDegree");
            highestDegreeEntity.setWidth(10);
            exportEntityList.add(highestDegreeEntity);
        }
        if(exportColumn.contains("认定批次")){
            ExcelExportEntity affirmBatchEntity = new ExcelExportEntity("认定批次", "affirmBatch");
            affirmBatchEntity.setWidth(12);
            exportEntityList.add(affirmBatchEntity);
        }
        if(exportColumn.contains("确认点")){
            ExcelExportEntity confirmAddressEntity = new ExcelExportEntity("确认点", "confirmAddress");
            confirmAddressEntity.setWidth(35);
            exportEntityList.add(confirmAddressEntity);
        }
        if(exportColumn.contains("认定机构")){
            ExcelExportEntity affirmInstitutionEntity = new ExcelExportEntity("认定机构", "affirmInstitution");
            affirmInstitutionEntity.setWidth(15);
            exportEntityList.add(affirmInstitutionEntity);
        }
        if(exportColumn.contains("考试类型")){
            ExcelExportEntity examTypeEntity = new ExcelExportEntity("考试类型", "examType");
            examTypeEntity.setWidth(10);
            exportEntityList.add(examTypeEntity);
        }
        if(exportColumn.contains("机构类型")){
            ExcelExportEntity organizationTypeEntity = new ExcelExportEntity("机构类型", "organizationType");
            organizationTypeEntity.setWidth(10);
            exportEntityList.add(organizationTypeEntity);
        }
        if(exportColumn.contains("任教学科")){
            ExcelExportEntity subjectEntity = new ExcelExportEntity("任教学科", "subject");
            subjectEntity.setWidth(20);
            exportEntityList.add(subjectEntity);
        }
        if(exportColumn.contains("教师资格证书号码")){
            ExcelExportEntity certificationIdEntity = new ExcelExportEntity("教师资格证书号码", "certificationId");
            certificationIdEntity.setWidth(20);
            exportEntityList.add(certificationIdEntity);
        }
        if(exportColumn.contains("市")){
            ExcelExportEntity cityEntity = new ExcelExportEntity("市", "city");
            cityEntity.setWidth(15);
            exportEntityList.add(cityEntity);
        }

        DownloadUtil.downloadExcel(response, new ExportParams(), exportEntityList, Certification.class, list);
}

    @Override
    public String importExcel(MultipartFile file) throws Exception {
        //需保存到数据库的记录
        List<Identification> resultData = new ArrayList<>();
        ImportParams params = new ImportParams();
        // 表头设置为首行
        params.setHeadRows(1);
        params.setTitleRows(0);

        ExcelImportResult<IdentificationDTO> result;
        result = ExcelImportUtil.importExcelMore(file.getInputStream(), IdentificationDTO.class, params);

        List<IdentificationDTO> correctResultList = result.getList();

        for (IdentificationDTO identificationDTO : correctResultList) {
            Identification identification = new Identification();
            BeanUtils.copyProperties(identificationDTO, identification);
            identification.setGender(Integer.parseInt(identification.getIdentificationId().substring(16,17)) % 2);
            resultData.add(identification);
        }

        saveBatch(resultData);
        return "导入成功";
    }

    @Override
    public List<String> listAffirmBatch(Integer limit) {
        return baseMapper.listAffirmBatch(limit);
    }

    @Override
    public List<IdentificationStatisticDTO> statisticIdentification(String affirmBatch, String affirmBatchStart, String affirmBatchEnd, List<String> statisticItemList) {
        List<IdentificationStatisticDTO> list = null;
        for (int i = 0; i < statisticItemList.size(); i++){
            statisticItemList.set(i, LineHumpUtil.humpToLine(statisticItemList.get(i)));
        }

        if(!affirmBatch.equals("")){
            if(statisticItemList.size() == 1) list = baseMapper.countOneColumnByYear(affirmBatch, statisticItemList.get(0));
            else list = baseMapper.countTwoColumnsByYear(affirmBatch, statisticItemList.get(0), statisticItemList.get(1));
        } else {
            String column = statisticItemList.get(0).equals("affirm_batch") ? statisticItemList.get(1) : statisticItemList.get(0);
            list = baseMapper.countColumnByYears(affirmBatchStart, affirmBatchEnd, column);
        }

        return list;
    }

    @Override
    public void exportStatisticExcel(HttpServletResponse response, String affirmBatch, String affirmBatchStart, String affirmBatchEnd, List<String> statisticItemList) {
        //      直接复用拿到数据
        List<IdentificationStatisticDTO> list = statisticIdentification(affirmBatch, affirmBatchStart, affirmBatchEnd,statisticItemList);

//      导出数据
        List<ExcelExportEntity> exportEntityList = new ArrayList<>();

        if(statisticItemList.contains("gender")){
            ExcelExportEntity genderEntity = new ExcelExportEntity("性别", "gender");
            genderEntity.setWidth(8);
            genderEntity.setReplace(new String[]{ "男_1", "女_0" });
            exportEntityList.add(genderEntity);
        }
        if(statisticItemList.contains("major_type")){
            ExcelExportEntity majorTypeEntity = new ExcelExportEntity("专业类别", "majorType");
            majorTypeEntity.setWidth(8);
            exportEntityList.add(majorTypeEntity);
        }
        if(statisticItemList.contains("graduation_school")){
            ExcelExportEntity graduationSchoolEntity = new ExcelExportEntity("毕业学校", "graduationSchool");
            graduationSchoolEntity.setWidth(25);
            exportEntityList.add(graduationSchoolEntity);
        }
        if(statisticItemList.contains("major")){
            ExcelExportEntity majorEntity = new ExcelExportEntity("所学专业", "major");
            majorEntity.setWidth(35);
            exportEntityList.add(majorEntity);
        }
        if(statisticItemList.contains("highest_education_background")){
            ExcelExportEntity highestEducationBackgroundEntity = new ExcelExportEntity("最高学历", "highestEducationBackground");
            highestEducationBackgroundEntity.setWidth(10);
            exportEntityList.add(highestEducationBackgroundEntity);
        }
        if(statisticItemList.contains("qualification_type")){
            ExcelExportEntity qualificationTypeEntity = new ExcelExportEntity("资格种类", "qualificationType");
            qualificationTypeEntity.setWidth(10);
            exportEntityList.add(qualificationTypeEntity);
        }
        if(statisticItemList.contains("highest_degree")){
            ExcelExportEntity highestDegreeEntity = new ExcelExportEntity("最高学位", "highestDegree");
            highestDegreeEntity.setWidth(10);
            exportEntityList.add(highestDegreeEntity);
        }
        if(statisticItemList.contains("affirm_batch")){
            ExcelExportEntity affirmBatchEntity = new ExcelExportEntity("认定批次", "affirmBatch");
            affirmBatchEntity.setWidth(12);
            exportEntityList.add(affirmBatchEntity);
        }
        if(statisticItemList.contains("confirm_address")){
            ExcelExportEntity confirmAddressEntity = new ExcelExportEntity("确认点", "confirmAddress");
            confirmAddressEntity.setWidth(35);
            exportEntityList.add(confirmAddressEntity);
        }
        if(statisticItemList.contains("affirm_institution")){
            ExcelExportEntity affirmInstitutionEntity = new ExcelExportEntity("认定机构", "affirmInstitution");
            affirmInstitutionEntity.setWidth(15);
            exportEntityList.add(affirmInstitutionEntity);
        }
        if(statisticItemList.contains("exam_type")){
            ExcelExportEntity examTypeEntity = new ExcelExportEntity("考试类型", "examType");
            examTypeEntity.setWidth(10);
            exportEntityList.add(examTypeEntity);
        }
        if(statisticItemList.contains("organization_type")){
            ExcelExportEntity organizationTypeEntity = new ExcelExportEntity("机构类型", "organizationType");
            organizationTypeEntity.setWidth(10);
            exportEntityList.add(organizationTypeEntity);
        }
        if(statisticItemList.contains("subject")){
            ExcelExportEntity subjectEntity = new ExcelExportEntity("任教学科", "subject");
            subjectEntity.setWidth(20);
            exportEntityList.add(subjectEntity);
        }
        if(statisticItemList.contains("city")){
            ExcelExportEntity cityEntity = new ExcelExportEntity("市", "city");
            cityEntity.setWidth(15);
            exportEntityList.add(cityEntity);
        }

//        数量
        ExcelExportEntity countEntity = new ExcelExportEntity("数量", "count");
        exportEntityList.add(countEntity);

        DownloadUtil.downloadExcel(response, new ExportParams(), exportEntityList, IdentificationStatisticDTO.class, list);

    }

    @Override
    public List<ProvinceCountVO> listCityCount() {
        return baseMapper.listCityCount();
    }

}
