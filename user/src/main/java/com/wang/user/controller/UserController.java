package com.wang.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wang.user.service.UserFilterVo;
import com.wang.user.service.UserService;
import com.wang.user.service.UserUpdateVo;
import com.wang.user.service.UserVo;

/**
 * @author wangju
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{uid}", method = GET)
	public Object queryUser(@PathVariable("uid") Integer uid) {
		return userService.query(uid);
	}

	@RequestMapping(value = "/user/filter", method = GET)
	public Object filterUser(UserFilterVo vo) {
		if (vo.getPage() == null || vo.getPage() <= 0) {
			vo.setPage(1);
		}
		if (vo.getPageSize() == null || vo.getPageSize() <= 0) {
			vo.setPageSize(10);
		} else if (vo.getPageSize() > 100) {
			vo.setPageSize(100);
		}
		return userService.filter(vo);
	}

	@RequestMapping(value = "/user/count", method = GET)
	public Object countUser(UserFilterVo vo) {
		vo.setPage(1);
		vo.setPageSize(1);
		return userService.count(vo);
	}

	@RequestMapping(value = "/user/add", method = POST)
	public Object addUser(@RequestBody UserVo vo) {
		userService.insert(vo);
		Map<String, Object> res = new HashMap<>();
		res.put("code", 0);
		res.put("message", "ok");
		return res;
	}

	@RequestMapping(value = "/user/update", method = POST)
	public Object updateUser(@RequestBody UserUpdateVo vo) {
		userService.update(vo);
		Map<String, Object> res = new HashMap<>();
		res.put("code", 0);
		res.put("message", "ok");
		return res;
	}

	@RequestMapping(value = "/user/delete/{uid}", method = POST)
	public Object deleteUser(@PathVariable("uid") Integer uid) {
		userService.delete(uid);
		Map<String, Object> res = new HashMap<>();
		res.put("code", 0);
		res.put("message", "ok");
		return res;
	}

	@RequestMapping(value = "/user/verify/passwd", method = GET)
	public Object verifyPasswd(@RequestParam("uid") Integer uid, @RequestParam("passwd") String passwd) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", userService.verifyPasswd(uid, passwd));
		return res;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	public class DateEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = simpleDateFormat.parse(URLDecoder.decode(text, Charset.forName("utf-8").name()));
				setValue(date);
			} catch (ParseException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
