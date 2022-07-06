package com.matth.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.matth.entity.model.User;
import com.matth.handler.SecurityDetailsHolder;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Matthiola incana
 * @create 2022/1/5 11:32
 */
@Component
@MapperScan("com.matth.mapper")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyBatisPlusConfig implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public GlobalConfig globalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(this);
        return globalConfig;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        User details = SecurityDetailsHolder.details();
        this.setFieldValByName("createTime",date,metaObject);
        this.setFieldValByName("creatorId",details.getId(),metaObject);
        this.setFieldValByName("createUnit","1",metaObject);
        this.setFieldValByName("updateTime",date,metaObject);
        this.setFieldValByName("updateId",details.getId(),metaObject);
        this.setFieldValByName("updateUnit","1",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User details = SecurityDetailsHolder.details();
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updateId",details.getId(),metaObject);
        this.setFieldValByName("updateUnit","1",metaObject);
    }
}
