package top.coderyjc.certificate.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: MybatisPlusConfig
 * Package: top.coderyjc.certificate.config
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/5/2023 9:03 AM
 * @Version 1.0
 */

@EnableTransactionManagement
@Configuration
@MapperScan("top.coderyjc.certificate.mapper")
public class MybatisPlusConfig {
    // 分页拦截器对象，目前分页不支持表连接，只能单表查询
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}