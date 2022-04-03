package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

//该类主要用于通过JAVA代码的形式来修改日志级别
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
