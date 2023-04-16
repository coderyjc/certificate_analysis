package top.coderyjc.certificate.mapper;

import top.coderyjc.certificate.model.entity.Certification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 证书表哦 Mapper 接口
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-02
 */
public interface CertificationMapper extends BaseMapper<Certification> {

    List<String> getInterviewYearList();

}
