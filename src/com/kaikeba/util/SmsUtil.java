package com.kaikeba.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @description: 阿里云短信发送工具类
 * @author: southwindow
 * @create: 2020-11-29 20:26
 **/
public class SmsUtil {
    /**
     * 阿里云短信发送
     * @param phoneNumber 要发送的目标手机号
     * @param code 取件码
     * @return
     */
    public static boolean send(String phoneNumber,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G4MPXGa7CSAQKDyiiU3", "K1NrOWmVsfCK058Pswi7Z6BvEOFC6x");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "南窗木心快递e栈");
        request.putQueryParameter("TemplateCode", "SMS_205876831");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if ("OK".equals(result.get("Message"))) {
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 微信端用户登陆验证码
     * @param phoneNumber
     * @param code
     * @return
     */

    public static boolean login(String phoneNumber,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G4MPXGa7CSAQKDyiiU3", "K1NrOWmVsfCK058Pswi7Z6BvEOFC6x");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "南窗木心快递e栈");
        request.putQueryParameter("TemplateCode", "SMS_206430069");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if ("OK".equals(result.get("Message"))) {
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
