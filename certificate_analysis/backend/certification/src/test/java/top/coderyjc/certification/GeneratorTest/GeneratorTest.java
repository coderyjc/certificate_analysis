package top.coderyjc.certification.GeneratorTest;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.junit.jupiter.api.Test;

/**
 * ClassName: GeneratorTest
 * Package: top.coderyjc.certification
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/2/2023 12:07 PM
 * @Version 1.0
 */
public class GeneratorTest extends BaseGeneratorTest{

    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/certificate_analysis", "root", "333")
            .build();


    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);

        generator.strategy(strategyConfig().build());
        generator.global(globalConfig().build());
        generator.packageInfo(packageConfig().build());

        generator.execute();
    }
}
