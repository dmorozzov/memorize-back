package com.dmore.memorize;

import com.dmore.memorize.config.AuditingConfig;
import com.dmore.memorize.config.JpaConfig;
import com.dmore.memorize.config.SecurityConfig;
import com.dmore.memorize.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({JpaConfig.class, AuditingConfig.class, WebMvcConfig.class, SecurityConfig.class})
@SpringBootApplication(scanBasePackages={"com.dmore.memorize"})
public class MemorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemorizeApplication.class, args);
    }
}
