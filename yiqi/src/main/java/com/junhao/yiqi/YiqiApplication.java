package com.junhao.yiqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class YiqiApplication extends SpringBootServletInitializer {

    @Override  //extends SpringBootServletInitializer
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(YiqiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(YiqiApplication.class, args);
    }
}
