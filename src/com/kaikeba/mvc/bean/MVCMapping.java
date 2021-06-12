package com.kaikeba.mvc.bean;

import java.lang.reflect.Method;

/**
 * MVC 映射表的类 映射对象
 */
public class MVCMapping {
    private Object object;
    private Method method;
    private ResponseType type;

    public MVCMapping() {
    }

    public MVCMapping(Object object, Method method, ResponseType type) {
        this.object = object;
        this.method = method;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
