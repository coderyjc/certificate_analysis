package top.coderyjc.certificate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import top.coderyjc.certificate.model.entity.Certification;
import top.coderyjc.certificate.mapper.CertificationMapper;
import top.coderyjc.certificate.service.ICertificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 证书表哦 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class CertificationServiceImpl extends ServiceImpl<CertificationMapper, Certification> implements ICertificationService {

    @Override
    public IPage<Certification> listAll(Integer pn, Integer limit, JSONObject condition) {
        return null;
    }

    @Override
    public void exportExcel(HttpServletResponse response, List<String> exportId, List<String> exportColumn, JSONObject condition) {

    }

    @Override
    public String importExcel(MultipartFile file) {
        return null;
    }
}
