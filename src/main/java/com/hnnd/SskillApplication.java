package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * public class SskillApplication extends SpringBootServletInitializer  打war
 */
@SpringBootApplication
public class SskillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SskillApplication.class, args);
	}

	/**
	 * 打war包
	 * @param builder bulider
	 * @return
	 */
/*	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SskillApplication.class);
	}*/

}
