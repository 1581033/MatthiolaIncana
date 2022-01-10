package com.zhao.www;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Matthiola incana
 * @create 2021/10/13 9:45
 */
@Log4j2
@MapperScan("com.zhao.www.mapper")
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled = true)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
