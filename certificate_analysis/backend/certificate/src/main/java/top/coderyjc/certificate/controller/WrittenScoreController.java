package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.reflection.invoker.GetFieldInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.coderyjc.certificate.model.dto.WrittenScoreDTO;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.service.IWrittenScoreService;
import top.coderyjc.certificate.util.Msg;

import java.util.List;
import java.util.Map;

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
     * @param gender
     * @param name
     * @param examId
     * @param identificationId
     * @param educationStatus
     * @param educationPsychologyStatus
     * @param professionalEthicStatus
     * @param educationSort
     * @param educationPsychologySort
     * @param professionalEthicSort
     * @param workAddress
     * @param examDate
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(
            @RequestParam(value = "current", defaultValue = "1")Integer pn,
            @RequestParam(value = "size", defaultValue = "20")Integer limit,
            @RequestParam(value = "gender", defaultValue = "") String gender,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "examId", defaultValue = "") String examId,
            @RequestParam(value = "identificationId", defaultValue = "") String identificationId,
            @RequestParam(value = "educationStatus", defaultValue = "") String educationStatus,
            @RequestParam(value = "educationPsychologyStatus", defaultValue = "") String educationPsychologyStatus,
            @RequestParam(value = "professionalEthicStatus", defaultValue = "") String professionalEthicStatus,
            @RequestParam(value = "educationSort", defaultValue = "") Integer educationSort,
            @RequestParam(value = "educationPsychologySort", defaultValue = "") Integer educationPsychologySort,
            @RequestParam(value = "professionalEthicSort", defaultValue = "") Integer professionalEthicSort,
            @RequestParam(value = "workAddress", defaultValue = "") String workAddress,
            @RequestParam(value = "examDate", defaultValue = "") String examDate
    ){
//        处理参数
        gender = gender.equals("") ? "" : gender.trim();
        name = name.equals("") ? "" : name.trim();
        examId = examId.equals("") ? "" : examId.trim();
        identificationId = identificationId.equals("") ? "" : identificationId.trim();
        educationStatus = educationStatus.equals("") ? "" : educationStatus.trim();
        educationPsychologyStatus = educationPsychologyStatus.equals("") ? "" : educationPsychologyStatus.trim();
        professionalEthicStatus = professionalEthicStatus.equals("") ? "" : professionalEthicStatus.trim();
        workAddress = workAddress.equals("") ? "" : workAddress.trim();
        examDate = examDate.equals("") ? "" : examDate.trim();

//        获取数据
        IPage<WrittenScore> iPage = service.listAll(
                pn,
                limit,
                gender,
                name,
                examId,
                identificationId,
                educationStatus,
                educationPsychologyStatus,
                professionalEthicStatus,
                educationSort,
                educationPsychologySort,
                professionalEthicSort,
                workAddress,
                examDate
        );
        return Msg.success().add("data", iPage);
    }


    /**
     * 添加笔试成绩
     * @param name
     * @param examId
     * @param identificationId
     * @param educationScore
     * @param educationPsychologyScore
     * @param professionalEthicScore
     * @param educationStatus
     * @param educationPsychologyStatus
     * @param professionalEthicStatus
     * @param workAddress
     * @param examDate
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg addWrittenScore(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "examId", defaultValue = "") String examId,
            @RequestParam(value = "identificationId", defaultValue = "") String identificationId,
            @RequestParam(value = "educationScore", defaultValue = "") String educationScore,
            @RequestParam(value = "educationPsychologyScore", defaultValue = "") String educationPsychologyScore,
            @RequestParam(value = "professionalEthicScore", defaultValue = "") String professionalEthicScore,
            @RequestParam(value = "educationStatus", defaultValue = "") String educationStatus,
            @RequestParam(value = "educationPsychologyStatus", defaultValue = "") String educationPsychologyStatus,
            @RequestParam(value = "professionalEthicStatus", defaultValue = "") String professionalEthicStatus,
            @RequestParam(value = "workAddress", defaultValue = "") String workAddress,
            @RequestParam(value = "examDate", defaultValue = "") String examDate
    ){

//        校验
        if(name.trim().equals("")){
            return Msg.fail().add("msg","姓名不能为空");
        }
        if(examId.trim().equals("")){
            return Msg.fail().add("msg","准考证号不能为空");
        }
        if(identificationId.trim().equals("")){
            return Msg.fail().add("msg","身份证号不能为空");
        }
        if(educationScore.trim().equals("") && educationStatus.equals("正常")){
            return Msg.fail().add("msg","教育学成绩不能为空");
        }
        if(educationPsychologyScore.trim().equals("") && educationPsychologyStatus.equals("正常")){
            return Msg.fail().add("msg","教育心理学成绩不能为空");
        }
        if(professionalEthicScore.trim().equals("") && professionalEthicStatus.equals("正常")){
            return Msg.fail().add("msg","职业道德修养和高等教育法规成绩不能为空");
        }
        if(educationStatus.trim().equals("")){
            return Msg.fail().add("msg","教育学考试状态不能为空");
        }
        if(educationPsychologyStatus.trim().equals("")){
            return Msg.fail().add("msg","教育心理学考试状态不能为空");
        }
        if(professionalEthicStatus.trim().equals("")){
            return Msg.fail().add("msg","职业道德修养和高等教育法规状态不能为空");
        }
        if(workAddress.trim().equals("")){
            return Msg.fail().add("msg","工作单位不能为空");
        }
        if(examDate.trim().equals("")){
            return Msg.fail().add("msg","考试时间不能为空");
        }

//        插入
        WrittenScore writtenScore = new WrittenScore();
        writtenScore.setName(name);
        writtenScore.setExamId(examId);
        writtenScore.setIdentificationId(identificationId);
        writtenScore.setEducationScore(educationScore);
        writtenScore.setEducationPsychologyScore(educationPsychologyScore);
        writtenScore.setProfessionalEthicScore(professionalEthicScore);
        writtenScore.setEducationStatus(educationStatus);
        writtenScore.setEducationPsychologyStatus(educationPsychologyStatus);
        writtenScore.setProfessionalEthicStatus(professionalEthicStatus);
        writtenScore.setWorkAddress(workAddress);
        writtenScore.setExamDate(examDate);
        writtenScore.setGender(Integer.parseInt(identificationId.substring(17, 18)) % 2);

        boolean result = service.save(writtenScore);

        return Msg.success().add("data", result ? "添加成功" : "添加失败");
    }

    /**
     * 删除单个的数据数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Msg deleteWrittenScore(
            @RequestParam(value = "id", defaultValue = "") String id
    ){
//        id是主键，必然不可能为空，因此可以直接删除
        boolean result = service.removeById(id);
        String msg = result ? "删除成功" : "删除失败";
        return Msg.success().add("data", msg);
    }


//    @RequestMapping(value = "/work_address", method = RequestMethod.GET)
//    public Msg listWorkAddress(){
//        List<String> list = service.listWorkAddress();
//        return Msg.success().add("data", list);
//    }
//
//    @RequestMapping(value = "/exam_year", method = RequestMethod.GET)
//    public Msg listExamYear(){
//        List<String> list = service.listExamYear();
//        return Msg.success().add("data", list);
//    }

}
