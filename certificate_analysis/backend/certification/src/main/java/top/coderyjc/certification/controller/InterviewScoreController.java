package top.coderyjc.certification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import top.coderyjc.certification.service.IInterviewScoreService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Controller
@RequestMapping("/interviewScore")
public class InterviewScoreController {

    @Autowired
    IInterviewScoreService service;


}
