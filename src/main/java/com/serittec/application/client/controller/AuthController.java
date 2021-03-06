package com.serittec.application.client.controller;

import com.serittec.application.client.constants.Path;
import com.serittec.application.client.domain.User;
import com.serittec.application.client.service.AuthService;
import com.serittec.application.client.vo.LoginVo;
import com.serittec.application.client.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping(Path.AUTH)
@RequestScope
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    @CrossOrigin
    public ResponseEntity<TokenVo> authenticateUser(@RequestBody LoginVo user) {
       return ResponseEntity.ok(authService.login(user.getUsername(), user.getPassword()));
    }
}
