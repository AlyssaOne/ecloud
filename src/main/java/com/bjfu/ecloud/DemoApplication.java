package com.bjfu.ecloud;

import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("com.bjfu.ecloud.mapper")
public class DemoApplication {

	public static void main(String[] args) {
//		configure();
		SpringApplication.run(DemoApplication.class, args);
	}

	public static void configure(){
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
	}

}
