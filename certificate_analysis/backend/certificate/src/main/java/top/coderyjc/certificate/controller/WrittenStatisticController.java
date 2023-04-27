package top.coderyjc.certificate.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.coderyjc.certificate.model.entity.WrittenStatistic;
import top.coderyjc.certificate.service.IWrittenStatisticService;
import top.coderyjc.certificate.util.Msg;

import javax.management.Query;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-23
 */
@RestController
@RequestMapping("/writtenStatistic")
public class WrittenStatisticController {

    @Autowired
    IWrittenStatisticService service;


    /**
     * 全部列表
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(@RequestParam(value = "start", defaultValue = "") String start, @RequestParam(value = "end", defaultValue = "") String end){
        QueryWrapper<WrittenStatistic> wrapper = new QueryWrapper<>();
        if(start.equals("") || end.equals("")) wrapper = null;
        else if(start.equals(end)) wrapper.eq("year", start);
        else wrapper.between("year", start, end);
        List<WrittenStatistic> list = service.list(wrapper);
        return Msg.success().add("data", list);
    }

    /**
     * 修改数据项
     * @param year
     * @param educationPassScore
     * @param psychologyPassScore
     * @param ethicPassScore
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Msg update(
            @RequestParam(value = "year")String year,
            @RequestParam(value = "educationPassScore")String educationPassScore,
            @RequestParam(value = "psychologyPassScore")String psychologyPassScore,
            @RequestParam(value = "ethicPassScore")String ethicPassScore
    ){
        if(year.equals("")) return Msg.fail();

        QueryWrapper<WrittenStatistic> wrapper = new QueryWrapper<>();
        wrapper.eq("year", year);
        WrittenStatistic writtenStatistic = service.getOne(wrapper);
        boolean exist = true;
        if(writtenStatistic == null){
            writtenStatistic = new WrittenStatistic();
            exist = false;
        }

        if(!educationPassScore.equals("")){
            writtenStatistic.setEducationPassScore(Double.valueOf(educationPassScore));
        }
        if(!psychologyPassScore.equals("")){
            writtenStatistic.setPsychologyPassScore(Double.valueOf(psychologyPassScore));
        }
        if(!ethicPassScore.equals("")){
            writtenStatistic.setEthicPassScore(Double.valueOf(ethicPassScore));
        }

        if(!exist) service.save(writtenStatistic);
        else service.updateById(writtenStatistic);

        service.updateAll(year, educationPassScore, psychologyPassScore, ethicPassScore);

        return Msg.success().add("data", true);
    }


    /**
     * 数据导出
     * @param start
     * @param end
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(
            @RequestParam(value = "start", defaultValue = "") String start,
            @RequestParam(value = "end", defaultValue = "") String end,
            HttpServletResponse response
    ){
        service.export(start, end, response);
    }

}
