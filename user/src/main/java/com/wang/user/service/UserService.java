package com.wang.user.service;

import java.util.List;
import java.util.Map;

import com.wang.user.model.domain.User;

/**
 * @author wangju
 *
 */
public interface UserService {

	Map<String, Object> filter(UserFilterVo vo);

	List<User> query(int uid);

	long count(UserFilterVo vo);

	int insert(UserVo vo);

	int update(UserUpdateVo vo);

	int delete(int uid);

	boolean verifyPasswd(int uid, String passwd);
}
