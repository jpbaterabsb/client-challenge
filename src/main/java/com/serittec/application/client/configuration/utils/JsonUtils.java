package com.serittec.application.client.configuration.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {

    private static final Gson mGson;


    static {
        mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }


    public static  String translateToJson(Object object) {
        return mGson.toJson(object);
    }

    public static  String toJson(Object o) {
        return translateToJson(o);
    }

    public static <T>  T toObject(Class<T> cla, String json) {

        if (json.isEmpty())
            return null;
        Gson gson = mGson;
        T t = null;
        try {

            t = gson.fromJson(json, cla);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T>  List<T> listJson(String json, Class<T> cla) {


        if (json.isEmpty())
            return null;
        List<T> list = new ArrayList<>();
        Gson gson = mGson;
        try {

            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
            T t = null;
            for (JsonElement jsonElement : jsonArray) {
                t = gson.fromJson(jsonElement, cla);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T>  List<T> listParseAllJson(String json, Class<T> cla) {
        if (json.isEmpty())
            return null;
        List<T> list = null;
        try {
            list = JsonUtils.mGson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static  Map<String, Object> mapJson(String json) {

        if (json.isEmpty())
            return null;
        Map<String, Object> mMap = null;
        try {
            Gson mGson = JsonUtils.mGson;
            mMap = mGson.fromJson(json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mMap;
    }


    public static <T>  ArrayList<T> fromJsonList(String json, Class<T> cls) {
        if (json.isEmpty())
            return null;
        Gson mGson = JsonUtils.mGson;
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    public static  Gson getGson() {
        return JsonUtils.mGson;
    }
    
}