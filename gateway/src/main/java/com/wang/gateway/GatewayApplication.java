package com.wang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.wang.gateway.filters.pre.DefaultFilter;

@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {

	@Bean
	public DefaultFilter defaultFilter() {
		return new DefaultFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
