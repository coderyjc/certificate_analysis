package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.entity.InterviewScore;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.service.IWrittenScoreService;
import top.coderyjc.certificate.util.Msg;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 笔试成绩 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/writtenScore")
public class WrittenScoreController {

    @Autowired
    IWrittenScoreService service;

    /**
     * 查询所有的笔试成绩
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
        IPage<WrittenScore> iPage = service.listAll(pn, limit, condition);
        return Msg.success().add("data", iPage);
    }

    /**
     * 添加笔试成绩
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg addWrittenScore(@RequestParam(value = "param", defaultValue = "{}") String data){
//        插入
        WrittenScore writtenScore = new WrittenScore();
        JSONObject jsonObject = JSONObject.parseObject(data);
        System.out.println(jsonObject);

        writtenScore.setName(jsonObject.getString("name"));
        writtenScore.setExamId(jsonObject.getString("examId"));
        writtenScore.setIdentificationId(jsonObject.getString("identificationId"));
        writtenScore.setEducationScore(jsonObject.getString("educationScore"));
        writtenScore.setEducationPsychologyScore(jsonObject.getString("educationPsychologyScore"));
        writtenScore.setProfessionalEthicScore(jsonObject.getString("professionalEthicScore"));
        writtenScore.setEducationStatus(jsonObject.getString("educationStatus"));
        writtenScore.setEducationPsychologyStatus(jsonObject.getString("educationPsychologyStatus"));
        writtenScore.setProfessionalEthicStatus(jsonObject.getString("professionalEthicStatus"));
        writtenScore.setWorkAddress(jsonObject.getString("workAddress"));
        writtenScore.setExamDate(jsonObject.getString("examDate"));
        writtenScore.setGender(Integer.parseInt(String.valueOf(jsonObject.getString("identificationId").charAt(16))) % 2);

        boolean result = service.save(writtenScore);

        return Msg.success().add("data", result ? "添加成功" : "添加失败");
    }

    /**
     * 修改笔试成绩
     * @param param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Msg updateWrittenScore(@RequestParam(value = "param", defaultValue = "{}") String param){
        System.out.println(param);
//      插入
        JSONObject jsonObject = JSONObject.parseObject(param);
        WrittenScore writtenScore = service.getById(Integer.parseInt(jsonObject.getString("id")));
//      赋值
        writtenScore.setName(jsonObject.getString("name"));
        writtenScore.setExamId(jsonObject.getString("examId"));
        writtenScore.setIdentificationId(jsonObject.getString("identificationId"));
        writtenScore.setEducationScore(jsonObject.getString("educationScore"));
        writtenScore.setEducationPsychologyScore(jsonObject.getString("educationPsychologyScore"));
        writtenScore.setProfessionalEthicScore(jsonObject.getString("professionalEthicScore"));
        writtenScore.setEducationStatus(jsonObject.getString("educationStatus"));
        writtenScore.setEducationPsychologyStatus(jsonObject.getString("educationPsychologyStatus"));
        writtenScore.setProfessionalEthicStatus(jsonObject.getString("professionalEthicStatus"));
        writtenScore.setWorkAddress(jsonObject.getString("workAddress"));
        writtenScore.setExamDate(jsonObject.getString("examDate"));
        writtenScore.setGender(Integer.parseInt(((String) jsonObject.get("identificationId")).substring(17, 18)) % 2);
//      插入
        service.updateById(writtenScore);
        return Msg.success().add("data", true ? "修改成功" : "修改失败");
    }

    /**
     * 删除单个的数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Msg deleteWrittenScore(@RequestParam(value = "id", defaultValue = "") String id){
//        id是主键，必然不可能为空，因此可以直接删除
        boolean result = service.removeById(id);
        String msg = result ? "删除成功" : "删除失败";
        return Msg.success().add("data", msg);
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
            @RequestParam(value = "exportColumn[]", defaultValue = "null") List<String> exportColumn,
            @RequestParam(value = "exportId[]", defaultValue = "null") List<String> exportId,
            @RequestParam(value = "searchCondition", defaultValue = "{}") String searchCondition
    ) {
        JSONObject condition = JSONObject.parseObject(searchCondition);
        service.exportExcel(response, exportId, exportColumn, condition);
    }

    /**
     * 导入Excel
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

        //验证导入工时的标题头是否合法
//        Workbook wb = null;
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//        InputStream fin;
//        try {
//            fin = file.getInputStream();
//            if ("xls".equals(suffix)) {
//                wb = new HSSFWorkbook(fin);
//            } else if ("xlsx".equals(suffix)) {
//                wb = new XSSFWorkbook(fin);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        // 表头
//        String[] columnName = {"姓名", "性别", "准考证号", "身份证号", "教育学成绩", "教育心理学成绩", "职业道德修养和高等教育法规成绩", "教育学考试状态", "教育心理学考试状态", "职业道德修养和高等教育法规状态", "工作单位", "考试时间"};
//        boolean resultVali = VerificationUtil.verificationWrittenScoreExcelHeadLine(wb, columnName);
//        if (!resultVali){
//            return Msg.fail().add("msg", "导入Excel表头与模板不一致，请核对文件表头");
//        }

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
        List<Integer> yearList = service.listExamDate(10);
        return Msg.success().add("data", yearList);
    }

    /**
     * 获取所有工作单位
     * @return
     */
    @RequestMapping(value = "/work_address", method = RequestMethod.GET)
    public Msg listAllWorkAddress(){
        List<String> workList = service.listWorkAddress();
        return Msg.success().add("data", workList);
    }

}
