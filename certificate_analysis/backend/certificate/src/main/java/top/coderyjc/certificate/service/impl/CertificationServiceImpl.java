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
import top.coderyjc.certificate.model.dto.CertificationImportDTO;
import top.coderyjc.certificate.model.entity.Certification;
import top.coderyjc.certificate.mapper.CertificationMapper;
import top.coderyjc.certificate.service.ICertificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.util.DownloadUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 证书表哦 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class CertificationServiceImpl extends ServiceImpl<CertificationMapper, Certification> implements ICertificationService {

    @Override
    public IPage<Certification> listAll(Integer pn, Integer limit, JSONObject condition) {

        IPage<Certification> iPage = new Page<>(pn, limit);
        QueryWrapper<Certification> wrapper = new QueryWrapper<>();

        if(condition.size() > 0){
            if(!condition.get("name").equals(""))  wrapper.eq("name", condition.get("name"));
            if(!condition.get("identificationId").equals(""))  wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("interviewYear").equals(""))  wrapper.like("interview_year", condition.get("interviewYear"));
            if(!condition.get("qualificationId").equals(""))  wrapper.eq("qualification_id", condition.get("qualificationId"));
            if(!condition.get("major").equals(""))  wrapper.eq("major", condition.get("major"));
            if(!condition.get("validateDate").equals(""))  wrapper.like("validate_date", condition.get("validateDate"));
            if(!condition.get("gender").equals(""))  wrapper.eq("gender", condition.get("gender"));
        }

        return baseMapper.selectPage(iPage, wrapper);
    }

    @Override
    public void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition) {

        List<Certification> list = null;
        QueryWrapper<Certification> wrapper = new QueryWrapper<>();

//      查找数据
        if(exportId.size() > 0){
//            按照id导出
            list = baseMapper.selectBatchIds(exportId);
        }
        else if(condition.size() > 0){
            if(!condition.get("name").equals(""))  wrapper.eq("name", condition.get("name"));
            if(!condition.get("identificationId").equals(""))  wrapper.eq("identification_id", condition.get("identificationId"));
            if(!condition.get("interviewYear").equals(""))  wrapper.eq("interview_year", condition.get("interviewYear"));
            if(!condition.get("qualificationId").equals(""))  wrapper.eq("qualification_id", condition.get("qualificationId"));
            if(!condition.get("major").equals(""))  wrapper.eq("major", condition.get("major"));
            if(!condition.get("validateDate").equals(""))  wrapper.eq("validate_date", condition.get("validateDate"));
            if(!condition.get("gender").equals(""))  wrapper.eq("gender", condition.get("gender"));
            list = baseMapper.selectList(wrapper);
        }

//        导出的列名
        List<ExcelExportEntity> exportEntityList = new ArrayList<>();
        if(exportColumn.contains("姓名")){
            ExcelExportEntity nameEntity = new ExcelExportEntity("姓名", "name");
            nameEntity.setWidth(10);
            exportEntityList.add(nameEntity);
        }
        if(exportColumn.contains("身份证号")){
            ExcelExportEntity identificationIdEntity = new ExcelExportEntity("身份证号", "identificationId");
            identificationIdEntity.setWidth(20);
            exportEntityList.add(identificationIdEntity);
        }
        if(exportColumn.contains("面试年份")){
            ExcelExportEntity interviewYearEntity = new ExcelExportEntity("面试年份", "interviewYear");
            interviewYearEntity.setWidth(8);
            exportEntityList.add(interviewYearEntity);
        }
        if(exportColumn.contains("合格证号")){
            ExcelExportEntity qualificationIdEntity = new ExcelExportEntity("合格证号", "qualificationId");
            qualificationIdEntity.setWidth(15);
            exportEntityList.add(qualificationIdEntity);
        }
        if(exportColumn.contains("专业")){
            ExcelExportEntity majorEntity = new ExcelExportEntity("专业", "major");
            majorEntity.setWidth(15);
            exportEntityList.add(majorEntity);
        }
        if(exportColumn.contains("有效期")){
            ExcelExportEntity validateDateEntity = new ExcelExportEntity("有效期", "validateDate");
            validateDateEntity.setWidth(15);
            validateDateEntity.setFormat("yyyy-MM-dd");
            exportEntityList.add(validateDateEntity);
        }
        if(exportColumn.contains("性别")){
            ExcelExportEntity genderEntity = new ExcelExportEntity("性别", "gender");
            genderEntity.setWidth(8);
            genderEntity.setReplace(new String[]{ "男_1", "女_0" });
            exportEntityList.add(genderEntity);
        }

        DownloadUtil.downloadExcel(response, new ExportParams(), exportEntityList, Certification.class, list);
    }

    @Override
    public String importExcel(MultipartFile file) throws Exception {
        //需保存到数据库的记录
        List<Certification> resultData = new ArrayList<>();
        ImportParams params = new ImportParams();
        // 表头设置为首行
        params.setHeadRows(1);
        params.setTitleRows(0);

        ExcelImportResult<CertificationImportDTO> result;
        result = ExcelImportUtil.importExcelMore(file.getInputStream(), CertificationImportDTO.class, params);

        List<CertificationImportDTO> correctResultList = result.getList();

        for (CertificationImportDTO certificationImportDTO : correctResultList) {
            Certification certification = new Certification();
            BeanUtils.copyProperties(certificationImportDTO, certification);
            certification.setGender(Integer.parseInt(certification.getIdentificationId().substring(16,17)) % 2);
            resultData.add(certification);
        }

        saveBatch(resultData);
        return "导入成功";
    }
}
