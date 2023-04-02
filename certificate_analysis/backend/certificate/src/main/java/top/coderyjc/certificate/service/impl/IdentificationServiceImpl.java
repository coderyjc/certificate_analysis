package top.coderyjc.certificate.service.impl;

import top.coderyjc.certificate.model.entity.Identification;
import top.coderyjc.certificate.mapper.IdentificationMapper;
import top.coderyjc.certificate.service.IIdentificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认定表 服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
@Service
public class IdentificationServiceImpl extends ServiceImpl<IdentificationMapper, Identification> implements IIdentificationService {

}
