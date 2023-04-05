package top.coderyjc.certificate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import top.coderyjc.certificate.model.entity.InterviewScore;
import top.coderyjc.certificate.service.IInterviewScoreService;
import top.coderyjc.certificate.util.Msg;

import java.util.List;

/**
 * <p>
 * 面试成绩 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Controller
@RequestMapping("/interviewScore")
public class InterviewScoreController {
}
