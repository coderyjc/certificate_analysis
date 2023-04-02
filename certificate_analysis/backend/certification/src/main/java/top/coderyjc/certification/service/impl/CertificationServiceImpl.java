package top.coderyjc.certification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.coderyjc.certification.entity.Certification;
import top.coderyjc.certification.mapper.CertificationMapper;
import top.coderyjc.certification.service.ICertificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class CertificationServiceImpl extends ServiceImpl<CertificationMapper, Certification> implements ICertificationService {

    @Autowired
    CertificationMapper mapper;

}
