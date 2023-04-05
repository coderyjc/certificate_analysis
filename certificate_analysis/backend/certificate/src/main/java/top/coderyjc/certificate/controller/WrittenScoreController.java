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
