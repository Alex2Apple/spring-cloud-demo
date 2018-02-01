package com.wang.user.model.mapper;

import java.util.List;

import com.wang.user.model.domain.User;
import com.wang.user.model.domain.UserExample;

/**
 * @author wangju
 *
 */
public interface UserMapper {
	int delete(Integer id);

	int insert(User record);

	User query(Integer id);

	int update(User record);

	long count(UserExample userExample);

	List<User> filter(UserExample userExample);
}