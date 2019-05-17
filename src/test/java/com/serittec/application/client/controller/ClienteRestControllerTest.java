package com.serittec.application.client.controller;

import com.serittec.application.client.constants.Path;
import com.serittec.application.client.controller.controller.CrudRestTest;
import com.serittec.application.client.domain.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ClienteRestControllerTest extends CrudRestTest<Cliente,Long> {

    @Override
    protected Cliente createObject() {
        return Cliente.builder()
                .cpf("000.000.000-00")
                .email(Arrays.asList(Email.builder().email("lala@gmail.com").build()))
                .endereco(Endereco.builder().logradouro("BH").bairro("SAVASSI").localidade("A").complemento("APT").build())
                .telefone(Arrays.asList(Telefone.builder().numero("0000-0000").tipo(Tipo.CELULAR).build()))
                .responsavel(1L)
                .nome("EDUARDO")
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    protected Cliente setObject(Cliente cliente) {
        cliente.setNome("TAVARES");
        cliente.setUpdatedAt(LocalDateTime.now());
        return cliente;
    }

    @Override
    protected String getPath() {
        return Path.CLIENTE;
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
