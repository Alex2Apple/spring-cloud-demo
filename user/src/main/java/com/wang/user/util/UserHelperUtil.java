package com.wang.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangju
 *
 */
public class UserHelperUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserHelperUtil.class);
	private static final String salt = "^4&*$%2_6-7+1#";

	public static String MD5(String text) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] b = md5.digest((salt + text).getBytes());
			StringBuffer md5str = new StringBuffer();
			int digital;
			for (int i = 0; i < b.length; i++) {
				digital = b[i];

				if (digital < 0) {
					digital += 256;
				}
				if (digital < 16) {
					md5str.append("0");
				}
				md5str.append(Integer.toHexString(digital));
			}
			return md5str.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("message digest error by md5", e);
		}

		return text;
	}
}
