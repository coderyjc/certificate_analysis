package top.coderyjc.certificate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MvcConfig
 * Package: top.coderyjc.certificate.config
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/4/2023 8:20 PM
 * @Version 1.0
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginIntercepter());
    }


}
