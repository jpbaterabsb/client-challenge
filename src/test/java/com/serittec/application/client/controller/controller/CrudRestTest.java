package com.serittec.application.client.controller.controller;

import org.hamcrest.beans.PropertyUtil;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.BeanUtils;
import org.springframework.security.util.FieldUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class CrudRestTest<T,ID> extends AbstractRestTest {

    private UriComponentsBuilder uriComponentsBuilder;
    private T instance;

    @Before
    public void setUp(){
        uriComponentsBuilder = UriComponentsBuilder.newInstance().path(getPath());
        instance = createObject();
    }


    public void create() throws Exception {
            T postObject = (T) post(uriComponentsBuilder.toUriString(), createObject(), instance.getClass());
            Assert.assertNotNull(postObject);
            Assert.assertNotNull(getId(postObject));
    }


    public void read() throws Exception {

             T[] all = (T[]) get(uriComponentsBuilder.toUriString(),Stream.of(instance).toArray().getClass());
             Assert.assertNotNull(all);
    }

    public void update() throws Exception {

            T createdObject = (T)  post(uriComponentsBuilder.toUriString(),createObject(),instance.getClass());

            T updateObject = setObject(createdObject);

            String uri = uriComponentsBuilder.path("/{id}").buildAndExpand(getId(updateObject)).toUriString();

            T putObject = (T) put(uri, updateObject, updateObject.getClass());

            Assert.assertEquals(updateObject,putObject);
    }

    public void delete() throws Exception {

            T createdObject = (T)  post(uriComponentsBuilder.toUriString(),createObject(),instance.getClass());
            String uri = uriComponentsBuilder.path("/{id}").buildAndExpand(getId(createdObject).toString()).toUriString();
            delete(uri,createdObject.getClass());
            T t = (T) get(uri, createdObject.getClass());
            Assert.assertNull(getId(t));

    }

    public void findById() throws Exception {

            T createdObject = (T)  post(uriComponentsBuilder.toUriString(),createObject(),instance.getClass());
            T t = (T) get(uriComponentsBuilder.path("/{id}").buildAndExpand(getId(createdObject)).toUriString(), createdObject.getClass());
            Assert.assertNotNull(t);
    }


    protected abstract   T createObject();

    protected abstract   T setObject(T t);

    private ID getId(T t) throws IllegalAccessException {
          return (ID) FieldUtils.getFieldValue(t,"id");
    }

    protected abstract String getPath();

}
