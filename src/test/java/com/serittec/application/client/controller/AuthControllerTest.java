package com.serittec.application.client.controller;

import com.serittec.application.client.controller.controller.AbstractRestTest;
import com.serittec.application.client.domain.User;
import com.serittec.application.client.vo.LoginVo;
import com.serittec.application.client.vo.TokenVo;
import io.jsonwebtoken.Jwt;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthControllerTest extends AbstractRestTest{

    @Test
   public void login() throws Exception {
        TokenVo tokenVoo = post("/login", new LoginVo("admin", "123456"), TokenVo.class);
        Assert.assertNotNull(tokenVoo);

    }

}