package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.data.GraphQlQueryByExampleAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.coderyjc.certificate.model.entity.Certification;
import top.coderyjc.certificate.model.entity.Identification;
import top.coderyjc.certificate.model.entity.InterviewScore;
import top.coderyjc.certificate.model.entity.WrittenScore;
import top.coderyjc.certificate.service.ICertificationService;
import top.coderyjc.certificate.service.IIdentificationService;
import top.coderyjc.certificate.service.IInterviewScoreService;
import top.coderyjc.certificate.service.IWrittenScoreService;
import top.coderyjc.certificate.util.Msg;

import javax.management.Query;
import java.util.*;

/**
 * ClassName: DashboardController
 * Package: top.coderyjc.certificate.controller
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/15/2023 8:22 PM
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

    @Autowired
    IIdentificationService identificationService;

    @Autowired
    ICertificationService certificationService;

    @Autowired
    IInterviewScoreService interviewScoreService;

    @Autowired
    IWrittenScoreService writtenScoreService;

    /**
     * 各种数据的总条数
     * @return
     */
    @RequestMapping(value = "/total_data", method = RequestMethod.GET)
    public Msg totalData(){
        JSONObject data = new JSONObject();
//      总数统计
        data.put("total_identification", identificationService.count());
        data.put("total_certification", certificationService.count());
        data.put("total_written", writtenScoreService.count());
        data.put("total_interview", interviewScoreService.count());
        return Msg.success().add("data", data);
    }


    /**
     * 5年之内的面试数据变化趋势
     * @return
     */
    @RequestMapping(value = "/interviewScore_trend", method = RequestMethod.GET)
    public Msg interviewScoreData(){
        JSONObject data = new JSONObject();
//      --面试数据
        List<String> interviewExamDate = interviewScoreService.listExamDate();
        List<Integer> interviewScoreList = new ArrayList<>();
        Collections.sort(interviewExamDate);
        JSONObject interviewScoreJson = new JSONObject();
        interviewScoreJson.put("category", interviewExamDate);
        for (String date: interviewExamDate) {
            QueryWrapper<InterviewScore> interviewScoreQueryWrapper = new QueryWrapper<>();
            interviewScoreQueryWrapper.eq("exam_date", date);
            interviewScoreList.add((int) interviewScoreService.count(interviewScoreQueryWrapper));
        }
        interviewScoreJson.put("data", interviewScoreList);
        data.put("interview_trend", interviewScoreJson);
        return Msg.success().add("data", data);
    }

    /**
     * 5次之内的认定信息变化趋势
     * @return
     */
    @RequestMapping(value = "/identification_trend", method = RequestMethod.GET)
    public Msg getIdentificationData(){
        JSONObject data = new JSONObject();
        //      --认定数据
        List<String> affirmBatch = identificationService.listAffirmBatch(5);
        List<Integer> identificationList = new ArrayList<>();
        Collections.sort(affirmBatch);
        JSONObject identificationJson = new JSONObject();
        identificationJson.put("category", affirmBatch);
        for (String batch : affirmBatch){
            QueryWrapper<Identification> identificationQueryWrapper = new QueryWrapper<>();
            identificationQueryWrapper.eq("affirm_batch", batch);
            identificationList.add((int) identificationService.count(identificationQueryWrapper));
        }
        identificationJson.put("data", identificationList);
        data.put("identification_trend", identificationJson);
        return Msg.success().add("data", data);
    }

    /**
     * 5年之内证书数据的变化趋势
     * @return
     */
    @RequestMapping(value = "/certification_trend", method = RequestMethod.GET)
    public Msg getCertificationData(){
        JSONObject data = new JSONObject();
//      --证书数据
        List<String> interviewYear = certificationService.listInterviewYear();
        List<Integer> certificationList = new ArrayList<>();
        Collections.sort(interviewYear);
        JSONObject certificationJson = new JSONObject();
        certificationJson.put("category", interviewYear);
        for (String year: interviewYear) {
            QueryWrapper<Certification> certificationQueryWrapper = new QueryWrapper<>();
            certificationQueryWrapper.eq("interview_year", year);
            certificationList.add((int) certificationService.count(certificationQueryWrapper));
        }
        certificationJson.put("data", certificationList);
        data.put("certification_trend", certificationJson);
        return Msg.success().add("data", data);
    }

    /**
     * 5年之内的笔试信息的数据变化趋势
     * @return
     */
    @RequestMapping(value = "/writtenScore_trend", method = RequestMethod.GET)
    public Msg getWrittenScoreData(){
        JSONObject data = new JSONObject();
//      --笔试数据
        List<String> writtenExamDate = writtenScoreService.listExamDate();
        List<Integer> writtenScoreList = new ArrayList<>();
        Collections.sort(writtenExamDate);
        JSONObject writtenScoreJson = new JSONObject();
        writtenScoreJson.put("category", writtenExamDate);
        for (String date: writtenExamDate){
            QueryWrapper<WrittenScore> writtenScoreQueryWrapper = new QueryWrapper<>();
            writtenScoreQueryWrapper.eq("exam_date", date);
            writtenScoreList.add((int) writtenScoreService.count(writtenScoreQueryWrapper));
        }
        writtenScoreJson.put("data", writtenScoreList);
        data.put("writtenScore_trend", writtenScoreJson);
        return Msg.success().add("data", data);
    }

}
