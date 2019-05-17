package com.serittec.application.client.controller.controller;

import java.io.IOException;

import com.serittec.application.client.constants.Path;
import com.serittec.application.client.controller.AbstractTest;
import com.serittec.application.client.vo.LoginVo;
import com.serittec.application.client.vo.TokenVo;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;



public abstract class AbstractRestTest extends AbstractTest {

	@Autowired
	private TestRestTemplate template;
	protected HttpHeaders headers;
	protected HttpEntity<?> entity;
	protected RestUtil restUtils;
	protected StringBuilder uriBuilder;
	
	@Before
	public void abstractRestTestSetUp() throws Exception, JsonParseException, JsonMappingException, JsonProcessingException, IOException {	
		this.entity = new HttpEntity<String>(headers);
		this.restUtils = new RestUtil(template.getRestTemplate());
		configure();		
	}
	
	private void configure() throws Exception {
		this.headers = new HttpHeaders();

		TokenVo tokenVo = post(Path.AUTH, new LoginVo("admin", "123456"), TokenVo.class);

		headers.add("Authorization","Bearer "+tokenVo.getToken());

		this.restUtils = new RestUtil(template.getRestTemplate(),headers);
	}
	
	protected <T> T get(String uri, Class<T> clazz) throws Exception {
		return restUtils.get(uri, clazz);
	}
	
	protected <T> T get(String uri, Class<T> clazz, Object... uriVariables) throws Exception {
		return restUtils.get(uri, clazz, uriVariables);
	}
	
	protected <T> T post(String uri, Object body, Class<T> clazz) throws Exception {
		return restUtils.post(uri, body, clazz);
	}

	protected <T> T put(String uri, Object body, Class<T> clazz) throws Exception {
		return restUtils.put(uri, body, clazz);
	}

	protected <T> T delete(String uri, Class<T> clazz) throws Exception {
		return restUtils.delete(uri, clazz);
	}

}

