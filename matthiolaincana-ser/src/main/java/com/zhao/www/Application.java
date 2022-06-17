package com.zhao.www;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Matthiola incana
 * @create 2021/10/13 9:45
 */
@Log4j2
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zhao.www.mapper")
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled = true)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
