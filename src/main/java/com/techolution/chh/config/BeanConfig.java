package com.techolution.chh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author naveenkumar300@gmail.com
 *
 */
@Configuration
public class BeanConfig {
	/**
	 * This method is used to return the restTemplate instance
	 * 
	 * @return an instance of RestTemplate
	 */
	@Bean

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
