package com.wang.user;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.wang.user.util.UserHelperUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		base = new URL("http://localhost:" + port + "/");
	}

	// @Test
	public void insert() {
		String url = base.toString() + "user/add";

		Map<String, Object> body = new HashMap<>();
		body.put("name", "smile");
		body.put("email", "smile@example.com");
		body.put("password", UserHelperUtil.MD5("smile_password"));

		ResponseEntity<Object> response = template.postForEntity(url, body, Object.class, new Object[] {});
		System.out.println(response.getBody());
	}

	// @Test
	public void update() {
		String url = base.toString() + "user/update";

		Map<String, Object> body = new HashMap<>();
		body.put("id", 120209);
		body.put("name", "spring_boot");
		body.put("email", "spring_boot@example.com");
		body.put("password", UserHelperUtil.MD5("spring_boot_password"));

		ResponseEntity<Object> response = template.postForEntity(url, body, Object.class, new Object[] {});
		System.out.println(response.getBody());
	}

	// @Test
	public void filter() throws RestClientException, UnsupportedEncodingException {
		String url = base.toString() + "user/filter";
		Map<String, Object> params = new HashMap<>();
		params.put("page", 1);
		params.put("pageSize", 10);
		params.put("beginCreateTime", "2018-01-31 17:40:00");

		ResponseEntity<Object> response = template.getForEntity(getRequestUrl(url, params), Object.class,
				new Object[] {});

		System.out.println(response.getBody());
	}

	@Test
	public void count() throws RestClientException, UnsupportedEncodingException {
		String url = base.toString() + "user/count";
		Map<String, Object> params = new HashMap<>();

		ResponseEntity<Object> response = template.getForEntity(getRequestUrl(url, params), Object.class,
				new Object[] {});

		System.out.println(response.getBody());
	}

	// @Test
	public void query() {
		String url = base.toString() + "user/%s";
		ResponseEntity<Object> response = template.getForEntity(String.format(url, 120208), Object.class,
				new Object[] {});

		System.out.println(response.getBody());
	}

	// @Test
	public void delete() {
		String url = base.toString() + "user/delete/%s";
		ResponseEntity<Object> response = template.postForEntity(String.format(url, 120209), null, Object.class,
				new Object[] {});

		System.out.println(response.getBody());
	}

	public static String getRequestUrl(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder(url);
		boolean isFirst = true;
		for (String key : params.keySet()) {
			if (key != null && params.get(key) != null) {
				if (isFirst) {
					isFirst = false;
					builder.append("?");
				} else {
					builder.append("&");
				}
				builder.append(key).append("=")
						.append(URLEncoder.encode(params.get(key).toString(), Charset.forName("utf-8").name()));
			}
		}
		return builder.toString();
	}
}
