package top.coderyjc.certificate.GeneratorTest;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

/**
 * ClassName: BaseGenerator
 * Package: top.coderyjc.certification.GeneratorTest
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/2/2023 12:10 PM
 * @Version 1.0
 */
public class BaseGeneratorTest {

    /**
     * 全局配置
     */
    protected static GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder()
                .outputDir("D:\\code\\github\\certificate_analysis\\certificate_analysis\\backend\\certificate\\src\\main\\java")
                .author("Yan Jingcun");
    }

    /**
     * 策略配置
     */
    protected static StrategyConfig.Builder strategyConfig() {
        StrategyConfig.Builder strategyConfig = new StrategyConfig.Builder();
        strategyConfig.addTablePrefix("tbl_");
        strategyConfig.entityBuilder()
                .enableLombok()
                .enableTableFieldAnnotation();
        strategyConfig.mapperBuilder()
                .enableBaseColumnList()
                .enableBaseResultMap();
//        指定生成名
        strategyConfig.addInclude("tbl_backup");

        return strategyConfig;
    }

    /**
     * 包配置
     */
    protected static PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder()
                .parent("top.coderyjc.certificate");
    }

}
