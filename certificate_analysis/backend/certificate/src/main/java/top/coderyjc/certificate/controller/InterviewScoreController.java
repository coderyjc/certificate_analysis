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
import top.coderyjc.certificate.service.IInterviewScoreService;
import top.coderyjc.certificate.util.Msg;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 面试成绩 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/interviewScore")
public class InterviewScoreController {

    @Autowired
    IInterviewScoreService service;

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
        IPage<InterviewScore> iPage = service.listAll(pn, limit, condition);
        return Msg.success().add("data", iPage);
    }

    /**
     * 添加面试成绩
     * @param data
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg addInterviewScore(@RequestParam(value = "param", defaultValue = "{}") String data){
//        插入
        InterviewScore interviewScore = new InterviewScore();
        JSONObject jsonObject = JSONObject.parseObject(data);
//        赋值
        interviewScore.setName((String) jsonObject.get("name"));
        interviewScore.setExamAddress((String) jsonObject.get("examAddress"));
        interviewScore.setIdentificationId((String) jsonObject.get("identificationId"));
        interviewScore.setWorkAddress((String) jsonObject.get("workAddress"));
        interviewScore.setApplyMajor((String) jsonObject.get("applyMajor"));
        interviewScore.setLevel((String) jsonObject.get("level"));
        interviewScore.setExamDate((String) jsonObject.get("examDate"));
        boolean result = false;
        if(7 == jsonObject.size()){
            result = service.save(interviewScore);
        }
        return Msg.success().add("data", result ? "添加成功" : "添加失败");
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
     * 删除多个的数据
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public Msg batchDeleteWrittenScore(@RequestParam(value = "ids[]", defaultValue = "null") List<String> ids){
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


}
