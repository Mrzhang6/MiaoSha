package com.zd.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zd.seckill.mapper")
public class MiaoShaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiaoShaApplication.class, args);
	}

}
