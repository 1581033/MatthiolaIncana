package com.zhao.www.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/1/5 11:32
 */
@Component
public class MyBatisPlusConfig implements MetaObjectHandler {

    @Bean
    public GlobalConfig globalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(this);
        return globalConfig;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.setFieldValByName("createTime",format,metaObject);
        this.setFieldValByName("updateTime",format,metaObject);
        this.setFieldValByName("updateId","1",metaObject);
        this.setFieldValByName("creatorId","1",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.setFieldValByName("updateTime",format,metaObject);
        this.setFieldValByName("updateId","1",metaObject);
    }
}
