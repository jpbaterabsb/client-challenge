package com.serittec.application.client.service;

import com.serittec.application.client.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractService<User,Long> {
}
