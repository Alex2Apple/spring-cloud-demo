package com.wang.book.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@SuppressWarnings("serial")
	@RequestMapping(value = "/book/list", method = GET)
	public Object books() {
		List<Map<String, String>> bs = new ArrayList<>();
		bs.add(new HashMap<String, String>() {
			{
				put("id", "1000");
				put("name", "Go in Action");
			}
		});
		bs.add(new HashMap<String, String>() {
			{
				put("id", "1001");
				put("name", "Homo Deus");
			}
		});
		bs.add(new HashMap<String, String>() {
			{
				put("id", "1002");
				put("name", "God Father");
			}
		});
		bs.add(new HashMap<String, String>() {
			{
				put("id", "1003");
				put("name", "Spring Cloud manual");
			}
		});

		return bs;
	}

}
