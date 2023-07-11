package com.ershouserver.ershou_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ershouserver.ershou_server.mapper")
public class ErshouServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErshouServerApplication.class, args);
    }

}
