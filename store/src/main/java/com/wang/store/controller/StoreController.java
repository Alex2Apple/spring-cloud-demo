package com.wang.store.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wang.store.service.StoreService;

@RestController
public class StoreController {

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/recommended", method = GET)
	public Object recommended() {
		return storeService.recommendedList();
	}
}
