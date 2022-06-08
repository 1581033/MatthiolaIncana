package com.zhao.www.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zhao.www.utils.jwt.JwtTokenUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * @author Matthiola incana
 * @create 2022/1/5 11:32
 */
@Component
public class MyBatisPlusConfig implements MetaObjectHandler {

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

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
        String token = httpServletResponse.getHeader(JwtTokenUtil.TOKENNAME);
        Optional<Object> optional = Optional.ofNullable(redisTemplate.opsForValue().get(token));
        this.setFieldValByName("createTime",date,metaObject);
        this.setFieldValByName("creatorId",optional,metaObject);
        this.setFieldValByName("createUnit","1",metaObject);
        this.setFieldValByName("updateTime",date,metaObject);
        this.setFieldValByName("updateId",optional,metaObject);
        this.setFieldValByName("updateUnit","1",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        String token = httpServletResponse.getHeader(JwtTokenUtil.TOKENNAME);
        Optional<Object> optional = Optional.ofNullable(redisTemplate.opsForValue().get(token));
        this.setFieldValByName("updateTime",date,metaObject);
        this.setFieldValByName("updateId",optional,metaObject);
        this.setFieldValByName("updateUnit","1",metaObject);
    }
}
