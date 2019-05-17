package com.serittec.application.client.controller;

import com.serittec.application.client.constants.Path;
import com.serittec.application.client.domain.Cliente;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Path.CLIENTE)
public class ClienteRestController extends AbstractRestController<Cliente,Long> {
}
