package com.kaikeba.wx.util;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 与微信交互的工具类,copy
 * 2020.12.3
 */
public class TokenUtil {

	private static String token;
	private static long oldTime = 0;

	public static String getToken() {
		long newTime = System.currentTimeMillis();
		if (newTime - oldTime >= 7100000) {
			oldTime = newTime;
			try {
				setToken();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return token;
	}


	private static void setToken() throws Exception {
		//TODO:必须要进行替换，进入mp.weixin.qq.com开发——基本配置,填入appid和secret
		String appid = "wx2f59ecbe7e76ac68";
		String secret = "58af29fba2092574426dd64f2aaad07d";
		URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String text = null;
		while((text = br.readLine())!=null) {
			sb.append(text);
		}
		br.close();
		JSONObject obj = new JSONObject(sb.toString());
		token = obj.getString("access_token");
	}
}
