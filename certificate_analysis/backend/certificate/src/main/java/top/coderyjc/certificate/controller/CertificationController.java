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
import top.coderyjc.certificate.model.dto.CertificationStatisticDTO;
import top.coderyjc.certificate.model.dto.InterviewScoreStatisticDTO;
import top.coderyjc.certificate.model.entity.Certification;
import top.coderyjc.certificate.service.ICertificationService;
import top.coderyjc.certificate.util.DateUtil;
import top.coderyjc.certificate.util.Msg;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 证书表 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/certification")
public class CertificationController {

    @Autowired
    ICertificationService service;


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
        IPage<Certification> iPage = service.listAll(pn, limit, condition);
        return Msg.success().add("data", iPage);
    }

    /**
     * 添加面试成绩
     * @param data
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg addCertification(@RequestParam(value = "param", defaultValue = "{}") String data){
//        插入
        Certification certification = new Certification();
        JSONObject jsonObject = JSONObject.parseObject(data);
//        赋值
        certification.setName((String) jsonObject.get("name"));
        certification.setIdentificationId((String) jsonObject.get("identificationId"));
        certification.setInterviewYear((String) jsonObject.get("interviewYear"));
        certification.setQualificationId((String) jsonObject.get("qualificationId"));
        certification.setMajor((String) jsonObject.get("major"));
        certification.setValidateDate(DateUtil.toDate(jsonObject.getString("validateDate")));
        certification.setGender(Integer.parseInt(String.valueOf(jsonObject.getString("identificationId").charAt(16))) % 2);
        boolean result = service.save(certification);
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
    @RequestMapping(value = "/years", method = RequestMethod.GET)
    public Msg listAllYears(){
        List<Integer> yearList = service.listAllInterviewYears(10);
        return Msg.success().add("data", yearList);
    }

    /**
     * 获取所有有效期
     * @return
     */
    @RequestMapping(value = "/validate_years", method = RequestMethod.GET)
    public Msg listAllValidateYears(){
        List<String> yearList = service.listAllValidateYears(10);
        return Msg.success().add("data", yearList);
    }

    /**
     * 数据统计查询
     * @param searchCondition
     * @return
     */
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public Msg statisticInterviewScore(@RequestParam(value = "condition", defaultValue = "{}") String searchCondition){
        JSONObject condition = JSONObject.parseObject(searchCondition);
        String interviewYear = condition.getString("interviewYear");
        String interviewStartYear = condition.getString("interviewStartYear");
        String interviewEndYear = condition.getString("interviewEndYear");
        JSONArray statisticsList = condition.getJSONArray("statisticItem");

        List<CertificationStatisticDTO> list = service.statisticCertification(interviewYear, interviewStartYear, interviewEndYear,statisticsList.toJavaList(String.class));

        return Msg.success().add("data", list);
    }

    /**
     * 数据统计表格导出
     * @param response
     * @param searchCondition
     */
    @RequestMapping(value = "/statistic/export", method = RequestMethod.GET)
    public void  exportStatisticExcel(
            HttpServletResponse response,
            @RequestParam(value = "condition", defaultValue = "{}") String searchCondition
    ){
        JSONObject condition = JSONObject.parseObject(searchCondition);
        String interviewYear = condition.getString("interviewYear");
        String interviewStartYear = condition.getString("interviewStartYear");
        String interviewEndYear = condition.getString("interviewEndYear");
        JSONArray statisticsList = condition.getJSONArray("statisticItem");

        service.exportStatisticExcel(response,interviewYear, interviewStartYear, interviewEndYear, statisticsList.toJavaList(String.class));
    }

}
