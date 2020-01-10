package cn.edu.bdu.carmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.edu.bdu.carmanage.mapper")
public class CarManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManageApplication.class, args);
    }

}
