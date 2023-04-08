package top.coderyjc.certificate.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * ClassName: MultipartConfig
 * Package: top.coderyjc.certificate.config
 * Description:
 *
 * @Author Yan Jingcun
 * @Create 4/8/2023 12:02 PM
 * @Version 1.0
 */

@Configuration
public class MultipartConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = "D:\\code\\github\\certificate_analysis\\certificate_analysis\\backend\\certificate\\cache";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}
