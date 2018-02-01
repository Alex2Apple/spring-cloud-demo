package com.wang.user.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author wangju
 *
 */
public class UserUpdateVo {

	@NotNull(message = "uid不能为空")
	@Pattern(regexp = "\\d+", message = "uid格式非法")
	private Integer id;

	private String name;

	private String email;

	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
