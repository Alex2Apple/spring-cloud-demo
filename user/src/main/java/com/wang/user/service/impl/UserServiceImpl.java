package com.wang.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.user.model.dao.UserDao;
import com.wang.user.model.domain.User;
import com.wang.user.model.domain.UserExample;
import com.wang.user.service.UserFilterVo;
import com.wang.user.service.UserService;
import com.wang.user.service.UserUpdateVo;
import com.wang.user.service.UserVo;
import com.wang.user.util.UserHelperUtil;

/**
 * @author wangju
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Map<String, Object> filter(UserFilterVo vo) {
		UserExample example = new UserExample();
		BeanUtils.copyProperties(vo, example);

		PageHelper.startPage(vo.getPage(), vo.getPageSize());
		PageHelper.orderBy("create_time desc");
		List<User> res = userDao.filter(example);

		PageInfo<User> page = new PageInfo<>(res);
		Map<String, Object> result = new HashMap<>();
		result.put("total", page.getTotal());
		result.put("page", page.getPageNum());
		result.put("data", page.getList());

		return result;
	}

	@Override
	public List<User> query(int uid) {
		List<User> list = new ArrayList<>();
		User user = userDao.query(uid);
		if (user != null) {
			list.add(user);
		}

		return list;
	}

	@Override
	public long count(UserFilterVo vo) {
		UserExample example = new UserExample();
		BeanUtils.copyProperties(vo, example);

		return userDao.count(example);
	}

	@Override
	public int insert(UserVo vo) {
		User user = new User();
		BeanUtils.copyProperties(vo, user);
		user.setPassword(UserHelperUtil.MD5(user.getPassword()));
		return userDao.insert(user);
	}

	@Override
	public int update(UserUpdateVo vo) {
		User user = new User();
		BeanUtils.copyProperties(vo, user);
		if (user.getPassword() != null) {
			user.setPassword(UserHelperUtil.MD5(user.getPassword()));
		}
		return userDao.update(user);
	}

	@Override
	public int delete(int uid) {
		return userDao.delete(uid);
	}

	@Override
	public boolean verifyPasswd(int uid, String passwd) {
		User user = userDao.query(uid);
		if (user != null) {
			return user.getPassword().equals(UserHelperUtil.MD5(passwd));
		}
		return false;
	}

}
