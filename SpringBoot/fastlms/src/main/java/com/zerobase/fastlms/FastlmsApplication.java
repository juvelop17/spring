package com.zerobase.fastlms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class FastlmsApplication {
    /*
    @PostConstruct
    public void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Spring boot application running in UTC timezone :" + new Date());

    }*/
    

    public static void main(String[] args) {
        SpringApplication.run(FastlmsApplication.class, args);
    }

}
