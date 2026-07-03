package com.javaee.clientinfomanagesystem;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javaee.clientinfomanagesystem.mapper")
public class ClientInfoManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientInfoManageSystemApplication.class, args);
    }

}
