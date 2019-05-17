package com.serittec.application.client.service;

import java.util.List;
import java.util.Map;

public interface IService<T, ID> {

    public T findById(ID id);

    public List<T> findAll();

    public void deleteById(ID id);

    public T save(T t);

    public T update(T t);

    public void deleteAll();

    public List<T> findByObject(Map<String, Object> params);

}
