package com.spf;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

//@ImportResource({"classpath:/META-INF/start-config.xml"})
@SpringBootApplication
@MapperScan("com.spf.mapper")
@EnableCaching
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootDemoApplication.class, args);

        System.out.println("****************************************");
        System.out.println("** Current Service Start Success!!!! **");
        System.out.println("****************************************");
    }
}
