package top.coderyjc.certificate.service.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.coderyjc.certificate.mapper.WrittenStatisticMapper;
import top.coderyjc.certificate.model.entity.WrittenStatistic;
import top.coderyjc.certificate.service.IWrittenStatisticService;
import top.coderyjc.certificate.util.DownloadUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-23
 */
@Service
public class WrittenStatisticServiceImpl extends ServiceImpl<WrittenStatisticMapper, WrittenStatistic> implements IWrittenStatisticService {

    @Override
    public void export(String start, String end, HttpServletResponse response) {

        QueryWrapper<WrittenStatistic> wrapper = new QueryWrapper<>();
        if(start.equals("") || end.equals("")) wrapper = null;
        else if(start.equals(end)) wrapper.eq("year", start);
        else wrapper.between("year", start, end);
        List<WrittenStatistic> list = this.list(wrapper);

//        添加导出列
        List<ExcelExportEntity> exportEntityList = new ArrayList<>();
        ExcelExportEntity yearEntity = new ExcelExportEntity("年份", "year");
        yearEntity.setWidth(10);
        exportEntityList.add(yearEntity);
        ExcelExportEntity educationAverageScoreEntity = new ExcelExportEntity("教育学平均成绩", "educationAverageScore");
        educationAverageScoreEntity.setWidth(15);
        exportEntityList.add(educationAverageScoreEntity);
        ExcelExportEntity educationPassRateEntity = new ExcelExportEntity("教育学通过率", "educationPassRate");
        educationPassRateEntity.setWidth(15);
        educationPassRateEntity.setSuffix("%");
        exportEntityList.add(educationPassRateEntity);
        ExcelExportEntity psychologyAverageScoreEntity = new ExcelExportEntity("教育心理学平均成绩", "psychologyAverageScore");
        psychologyAverageScoreEntity.setWidth(20);
        exportEntityList.add(psychologyAverageScoreEntity);
        ExcelExportEntity psychologyPassRateEntity = new ExcelExportEntity("教育心理学通过率", "psychologyPassRate");
        psychologyPassRateEntity.setWidth(20);
        psychologyPassRateEntity.setSuffix("%");
        exportEntityList.add(psychologyPassRateEntity);
        ExcelExportEntity ethicAverageScoreEntity = new ExcelExportEntity("职业道德平均分数", "ethicAverageScore");
        ethicAverageScoreEntity.setWidth(20);
        exportEntityList.add(ethicAverageScoreEntity);
        ExcelExportEntity ethicPassRateEntity = new ExcelExportEntity("职业道德通过率", "ethicPassRate");
        ethicPassRateEntity.setWidth(20);
        ethicPassRateEntity.setSuffix("%");
        exportEntityList.add(ethicPassRateEntity);
        ExcelExportEntity passRateEntity = new ExcelExportEntity("三科都通过的通过率", "passRate");
        passRateEntity.setSuffix("%");
        passRateEntity.setWidth(20);
        exportEntityList.add(passRateEntity);

        DownloadUtil.downloadExcel(response, new ExportParams(), exportEntityList, WrittenStatistic.class, list);
    }

    @Override
    public void updateAll(String year, String educationPassScore, String psychologyPassScore, String ethicPassScore) {
        if(!educationPassScore.equals("")) baseMapper.updateEducation(year, educationPassScore);
        if(!psychologyPassScore.equals("")) baseMapper.updatePsychology(year, psychologyPassScore);
        if(!ethicPassScore.equals("")) baseMapper.updateEthic(year, ethicPassScore);
    }

    @Override
    public void calculateAverage() {
        baseMapper.calculateEducationAverage();
        baseMapper.calculatePsychologyAverage();
        baseMapper.calculateEthicAverage();
    }
}
