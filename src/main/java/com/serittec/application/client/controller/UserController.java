package com.serittec.application.client.controller;

import com.serittec.application.client.constants.Path;
import com.serittec.application.client.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Path.USER)
public class UserController extends AbstractRestController<User,Long> {
}
