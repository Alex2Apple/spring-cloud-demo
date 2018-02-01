package com.wang.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangju
 *
 */
@RestController
public class HelloController {

	@RequestMapping(value = "/", method = GET)
	public String index() {
		return "Welcome to Spring Boot";
	}
}
