package com.kaikeba.mvc;

import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.mvc.annotation.ResponseView;
import com.kaikeba.mvc.bean.MVCMapping;
import com.kaikeba.mvc.bean.ResponseType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 映射器 一个目录，方法的目录，请求何种网址就找到对应的方法
 */
public class HandleMapping {
    //映射器本质是HashMap
    private static Map<String, MVCMapping> data = new HashMap<>();

    /**
     * 传入uri,从hashmap返回uri
     * @param uri
     * @return
     */
    public static MVCMapping get(String uri) {
        return data.get(uri);
    }

    /**
     * 加载输入流，实例化HandleMapping类
     * @param inputStream
     */
    public static void load(InputStream inputStream) {
        //配置文件是用properties写的，所以新建Properties对象加载输入流
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取properties配置文件的信息，既类名
        //1、读取里面的所有值
        Collection<Object> values = properties.values();
        //2、遍历
        for (Object value : values) {
            String className = (String) value;
            try {
                //3、加载每一个类
                Class aClass = Class.forName(className);
                //4、创建这个类的对象
                Object object = aClass.getConstructor().newInstance();
                //5、获取这个类的所有方法
                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    //6、获得方法的注解
                    Annotation[] annotations = method.getAnnotations();
                    //7、遍历注解，根据注解的不同，做不同的处理
                    if (annotations != null) {
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof ResponseBody) {
                                //此方法用于返回字符串给客户端
                                MVCMapping mvcMapping = new MVCMapping(object, method, ResponseType.TEXT);
                                Object o = data.put(((ResponseBody) annotation).value(), mvcMapping);
                                if (o != null) {
                                    //o 不为空说明存在重复的请求地址
                                    throw new RuntimeException("请求地址重复：" + ((ResponseBody) annotation).value());
                                }
                            } else if (annotation instanceof ResponseView) {
                                //此方法用于返回界面给客户端
                                MVCMapping mvcMapping = new MVCMapping(object, method, ResponseType.VIEW);
                                Object o = data.put(((ResponseView) annotation).value(), mvcMapping);
                                if (o != null) {
                                    throw new RuntimeException("请求地址重复：" + ((ResponseView) annotation).value());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
