package com.study.lywspel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luohongxiao
 */
@SpringBootApplication
public class LywSpelApplication {

    public static void main(String[] args) {
        //除
        int i = 5 / 2;
        //取模（取余数）
        int j = 5 % 2;

        SpringApplication.run(LywSpelApplication.class, args);
    }

}
