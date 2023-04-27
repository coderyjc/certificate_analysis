package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.dto.IdentificationStatisticDTO;
import top.coderyjc.certificate.model.entity.Identification;
import top.coderyjc.certificate.model.vo.ProvinceCountVO;
import top.coderyjc.certificate.service.IIdentificationService;
import top.coderyjc.certificate.util.Msg;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 认定表 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/identification")
public class IdentificationController {

    @Autowired
    private IIdentificationService service;

    /**
     * 分页获取所有的数据，条件用json传输
     * @param pn
     * @param limit
     * @param searchCondition
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(
            @RequestParam(value = "current", defaultValue = "1")Integer pn,
            @RequestParam(value = "size", defaultValue = "30")Integer limit,
            @RequestParam(value = "searchCondition", defaultValue = "{}") String searchCondition
    ){
        JSONObject condition = JSONObject.parseObject(searchCondition);
        IPage<Identification> iPage = service.listAll(pn, limit, condition);
        return Msg.success().add("data", iPage);
    }

    /**
     * 添加认定数据
     * @param data
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg addCertification(@RequestParam(value = "param", defaultValue = "{}") String data){
//        插入
        Identification identification = new Identification();
        JSONObject jsonObject = JSONObject.parseObject(data);
//        赋值
        identification.setName(jsonObject.getString("name"));
        identification.setMajorType(jsonObject.getString("majorType"));
        identification.setGraduationSchool(jsonObject.getString("graduationSchool"));
        identification.setMajor(jsonObject.getString("major"));
        identification.setHighestEducationBackground(jsonObject.getString("highestEducationBackground"));
        identification.setQualificationType(jsonObject.getString("qualificationType"));
        identification.setIdentificationId(jsonObject.getString("identificationId"));
        identification.setHighestDegree(jsonObject.getString("highestDegree"));
        identification.setAffirmBatch(jsonObject.getString("affirmBatch"));
        identification.setConfirmAddress(jsonObject.getString("confirmAddress"));
        identification.setAffirmInstitution(jsonObject.getString("affirmInstitution"));
        identification.setExamType(jsonObject.getString("examType"));
        identification.setOrganizationType(jsonObject.getString("organizationType"));
        identification.setSubject(jsonObject.getString("subject"));
        identification.setCertificationId(jsonObject.getString("certificationId"));
        identification.setCity(jsonObject.getString("city"));
        identification.setGender(Integer.parseInt(String.valueOf(jsonObject.getString("identificationId").charAt(16))) % 2);

        boolean result = service.save(identification);
        return Msg.success().add("data", result ? "添加成功" : "添加失败");
    }



    /**
     * 修改认定数据
     * @param data
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Msg updateCertification(@RequestParam(value = "param", defaultValue = "{}") String data){
//        插入
        JSONObject jsonObject = JSONObject.parseObject(data);
        Identification identification = service.getById(jsonObject.getString("id"));
//        赋值
        identification.setName(jsonObject.getString("name"));
        identification.setMajorType(jsonObject.getString("majorType"));
        identification.setGraduationSchool(jsonObject.getString("graduationSchool"));
        identification.setMajor(jsonObject.getString("major"));
        identification.setHighestEducationBackground(jsonObject.getString("highestEducationBackground"));
        identification.setQualificationType(jsonObject.getString("qualificationType"));
        identification.setIdentificationId(jsonObject.getString("identificationId"));
        identification.setHighestDegree(jsonObject.getString("highestDegree"));
        identification.setAffirmBatch(jsonObject.getString("affirmBatch"));
        identification.setConfirmAddress(jsonObject.getString("confirmAddress"));
        identification.setAffirmInstitution(jsonObject.getString("affirmInstitution"));
        identification.setExamType(jsonObject.getString("examType"));
        identification.setOrganizationType(jsonObject.getString("organizationType"));
        identification.setSubject(jsonObject.getString("subject"));
        identification.setCertificationId(jsonObject.getString("certificationId"));
        identification.setCity(jsonObject.getString("city"));
        identification.setGender(Integer.parseInt(String.valueOf(jsonObject.getString("identificationId").charAt(16))) % 2);

        boolean result = service.updateById(identification);
        return Msg.success().add("data", result ? "添加成功" : "添加失败");
    }


    /**
     * 删除单个的数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Msg deleteCertification(@RequestParam(value = "id", defaultValue = "") String id){
//        id是主键，必然不可能为空，因此可以直接删除
        boolean result = service.removeById(id);
        String msg = result ? "删除成功" : "删除失败";
        return Msg.success().add("data", msg);
    }

    /**
     * 删除多个的数据
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public Msg batchDeleteCertification(@RequestParam(value = "ids[]", defaultValue = "null") List<String> ids){
//        id是主键，必然不可能为空，因此可以直接删除
        boolean result = service.removeBatchByIds(ids);
        return Msg.success().add("data", result ? "删除成功" : "删除失败");
    }

    /**
     * 导出excel
     * @param response
     * @param exportColumn
     * @param exportId
     * @param searchCondition
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(
            HttpServletResponse response,
            @RequestParam(value = "exportColumn[]", defaultValue = "") List<String> exportColumn,
            @RequestParam(value = "exportId[]", defaultValue = "") List<String> exportId,
            @RequestParam(value = "searchCondition", defaultValue = "{}") String searchCondition
    ) {
        JSONObject condition = JSONObject.parseObject(searchCondition);
        service.exportExcel(response, exportId, exportColumn, condition);
    }

    /**
     * 导入面试成绩的Excel
     * @param file
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public Msg importExcel(@RequestParam("file") MultipartFile file) {

        //文件为空校验
        if (file == null) {
            return Msg.fail().add("msg", "文件为空");
        }

        //文件后缀名校验
        String fileName = file.getOriginalFilename();
        if (!(fileName.endsWith("xls") || fileName.endsWith("xlsx"))) {
            return Msg.fail().add("msg", "上传文件格式不正确，请传入正确的Excel文件");
        }

        String msg;
        try {
            msg = service.importExcel(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Msg.success().add("msg", msg);
    }

    /**
     * 获取所有的年份
     * @return
     */
    @RequestMapping(value = "/batchs", method = RequestMethod.GET)
    public Msg listAllAffirmBatch(){
        List<String> batchList = service.listAffirmBatch(10);
        return Msg.success().add("data", batchList);
    }

    /**
     * 获取认定表中的所有的省份的统计
     * @return
     */
//    @RequestMapping(value = "/count_province", method = RequestMethod.GET)
//    public Msg listProvinceCount(){
//        List<ProvinceCountVO> list = service.listProvinceCount();
//        return Msg.success().add("data", list);
//    }
//
    /**
     * 获取认定表中的所有的省份的统计
     * @return
     */
    @RequestMapping(value = "/count_hebei", method = RequestMethod.GET)
    public Msg listHebeiCount(){
        List<ProvinceCountVO> list = service.listCityCount();
        return Msg.success().add("data", list);
    }


}
