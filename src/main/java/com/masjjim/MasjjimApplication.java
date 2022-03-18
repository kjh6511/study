package com.masjjim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {
		"com.masjjim.member.mapper"
		,"com.masjjim.board.mapper"
		,"com.masjjim.file.mapper"
		,"com.masjjim.store.mapper"})
public class MasjjimApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasjjimApplication.class, args);
	}

}