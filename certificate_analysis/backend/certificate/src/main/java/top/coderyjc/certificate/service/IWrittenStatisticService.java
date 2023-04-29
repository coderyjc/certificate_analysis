package top.coderyjc.certificate.service;

import top.coderyjc.certificate.model.entity.WrittenStatistic;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-23
 */
public interface IWrittenStatisticService extends IService<WrittenStatistic> {

    void export(String start, String end, HttpServletResponse response);

    void updateAll(String year, String educationPassScore, String psychologyPassScore, String ethicPassScore);

    void calculateAverage();
}
