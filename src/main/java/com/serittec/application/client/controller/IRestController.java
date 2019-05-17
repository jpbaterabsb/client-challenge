package com.serittec.application.client.controller;

import java.util.List;

public interface IRestController<T, ID> {

    public T findById(ID id);

    public List<T> findAll();


    public void deleteById(ID id);

    public T save(T t);

    public T update(T t);


}