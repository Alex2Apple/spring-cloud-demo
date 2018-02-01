package com.wang.store.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class StoreService {

	private final RestTemplate restTemplate;

	@Autowired
	public StoreService(RestTemplate template) {
		this.restTemplate = template;
	}

	@HystrixCommand(fallbackMethod = "reliable")
	public Object recommendedList() {
		URI uri = URI.create("http://book/book/list");
		return this.restTemplate.getForEntity(uri, List.class);
	}

	@SuppressWarnings("serial")
	public Object reliable() {
		List<Map<String, String>> bs = new ArrayList<>();
		bs.add(new HashMap<String, String>() {
			{
				put("id", "2000");
				put("name", "Go with the wind");
			}
		});
		bs.add(new HashMap<String, String>() {
			{
				put("id", "2001");
				put("name", "Times 2017");
			}
		});
		bs.add(new HashMap<String, String>() {
			{
				put("id", "2002");
				put("name", "Spring Boot manual");
			}
		});

		return bs;
	}
}
