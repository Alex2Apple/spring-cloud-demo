package com.wang.user.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wang.user.model.domain.User;
import com.wang.user.model.domain.UserExample;
import com.wang.user.model.mapper.UserMapper;

/**
 * @author wangju
 *
 */
@Component
public class UserDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4096984996078359534L;

	@Autowired
	private UserMapper userMapper;

	public User query(Integer id) {
		return userMapper.query(id);
	}

	public List<User> filter(UserExample example) {
		if (example.getName() != null && StringUtils.hasText(example.getName())) {
			example.setName("%" + example.getName() + "%");
		} else {
			example.setName(null);
		}
		return userMapper.filter(example);
	}

	public long count(UserExample example) {
		if (example.getName() != null && StringUtils.hasText(example.getName())) {
			example.setName("%" + example.getName() + "%");
		} else {
			example.setName(null);
		}
		return userMapper.count(example);
	}

	public int insert(User record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		return userMapper.insert(record);
	}

	public int update(User record) {
		record.setUpdateTime(new Date());
		return userMapper.update(record);
	}

	public int delete(Integer id) {
		return userMapper.delete(id);
	}
}
