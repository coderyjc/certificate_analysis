package top.coderyjc.certificate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.coderyjc.certificate.mapper")
public class CertificateApplication {
    public static void main(String[] args) {
        SpringApplication.run(CertificateApplication.class, args);
    }

}
