package com.serittec.application.client.controller;

import com.serittec.application.client.constants.Path;
import com.serittec.application.client.controller.controller.CrudRestTest;
import com.serittec.application.client.domain.TipoUsuario;
import com.serittec.application.client.domain.User;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

public class UserControllerTest extends CrudRestTest<User,Long> {


    @Override
    protected User createObject() {
        return User.builder()
                .id(2L)
                .password(new BCryptPasswordEncoder(12).encode("123456"))
                .authorities(Arrays.asList(new SimpleGrantedAuthority(TipoUsuario.ADMIN.toString())))
                .tipoUsuario(TipoUsuario.ADMIN)
                .username("admin123")
                .build();
    }

    @Override
    protected User setObject(User user) {
        user.setUsername("Eduardo");
        user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(TipoUsuario.USER.toString())));
        user.setTipoUsuario(TipoUsuario.USER);
        return user;
    }

    @Override
    protected String getPath() {
        return Path.USER;
    }

    @Test
    public void create() throws Exception {
        super.create();
    }

    @Test
    public void update() throws Exception {
        super.update();
    }

    @Test
    public void delete() throws Exception {
        super.delete();
    }

    @Test
    public void read() throws Exception {
        super.read();
    }

    @Test
    public void findById() throws Exception {
        super.findById();
    }
}