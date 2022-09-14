package com.codemari.timezonefollowerrest;

import com.codemari.timezonefollowerrest.db.LoadDataBase;
import com.codemari.timezonefollowerrest.rest.LocationController;
import com.codemari.timezonefollowerrest.rest.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TimezonefollowerRestApplication extends SpringBootServletInitializer {

	private final static Logger log = LoggerFactory.getLogger(LoadDataBase.class);
	public static void main(String[] args) {
		SpringApplication.run(TimezonefollowerRestApplication.class, args);
	}

}
