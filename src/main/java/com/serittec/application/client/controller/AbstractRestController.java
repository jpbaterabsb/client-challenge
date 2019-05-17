package com.serittec.application.client.controller;

import java.util.List;

import com.serittec.application.client.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;



public abstract class AbstractRestController<T, ID> implements IRestController<T, ID> {

    @Autowired
    private IService<T, ID> service;

    @GetMapping
    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<T> findAll() {
        return this.service.findAll();
    }


    @GetMapping("/{id}")
    @CrossOrigin
    @Override
    @ResponseStatus(HttpStatus.OK)
    public T findById(@PathVariable ID id) {
        return this.service.findById(id);
    }


    @Override
    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable ID id) {
        this.service.deleteById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T t) {
        return this.service.save(t);
    }

    @Override
    @CrossOrigin
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T update(@RequestBody T t) {
        return this.service.update(t);
    }

}
