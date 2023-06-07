package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//配置类
@Configuration
public class AlphaConfig {

    //定义第三方Bean,方法名就是Bean的名字
    //这个方法返回的对象将会被装配到容器里
    @Bean
    public SimpleDateFormat simpleDateFormat(){
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    }
}
