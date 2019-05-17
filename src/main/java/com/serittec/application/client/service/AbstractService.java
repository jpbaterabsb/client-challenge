package com.serittec.application.client.service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractService<T, ID> implements IService<T, ID> {

    @Autowired
    private JpaRepository<T, ID> repository;

    @Override
    public T findById(ID id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void deleteById(ID id) {
        this.repository.deleteById(id);
    }

    @Override
    public T save(T t) {
       return this.repository.save(t);
    }


    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Override
    public T update(T t) {
       return this.repository.save(t);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public List<T> findByObject(Map<String, Object> params) {

        Example<T> example;
        try {
            example = getExample(params);
            List<T> entidades = (List<T>) this.repository.findAll(example);
            return entidades;

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    protected Example<T> getExample(Map<String, Object> params) throws IntrospectionException {
        T filtros = getObjectWithParams(params);
        return (Example<T>) Example.of(filtros,
                ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
    }

    @SuppressWarnings("unchecked")
    private T getClassObject() {
        try {
            return (T) ((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private T getObjectWithParams(Map<String, Object> params) {
        try {
            T object = getClassObject();
            BeanInfo bi = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor pds[] = bi.getPropertyDescriptors();


            params.forEach((key, value) -> {
                for (PropertyDescriptor property : pds) {
                    if (property.getName().equals(key)) {
                        try {
                            property.getWriteMethod().invoke(object, new Object[] { value });
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return object;
        } catch (IntrospectionException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}

