package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.coderyjc.certificate.model.dto.CertificationStatisticDTO;
import top.coderyjc.certificate.service.ICertificationService;
import top.coderyjc.certificate.util.Msg;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: CertificationVisualize
 * Package: top.coderyjc.certificate.controller
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/27/2023 8:42 AM
 * @Version 1.0
 */

@RestController
@RequestMapping("/certification/statistic")
public class CertificationVisualizeController {

    @Autowired
    private ICertificationService service;

    /**
     * 数据统计查询
     * @param searchCondition
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
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
    @RequestMapping(value = "/export", method = RequestMethod.GET)
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
