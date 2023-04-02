package top.coderyjc.certificate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.coderyjc.certificate.model.entity.Certification;
import top.coderyjc.certificate.service.ICertificationService;
import top.coderyjc.certificate.util.Msg;

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
     * success
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(){
        QueryWrapper<Certification> wrapper = new QueryWrapper<Certification>();
        wrapper.eq("interview_year", "2013年");
        List<Certification> data = service.list(wrapper);
        return Msg.success().add("data", data);
    }

}
