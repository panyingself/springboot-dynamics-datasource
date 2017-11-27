package com.py;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by py on 2017/11/24.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class AppEntrance {
    public static void main(String[] args) throws  Exception{
        SpringApplication.run(AppEntrance.class, args);
    }
}
