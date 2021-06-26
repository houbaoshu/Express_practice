package com.kaikeba.wx.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 与微信交互的工具类,copy
 * 2020.12.3
 *
 * 微信端部署流程：
 * 1.下载ngrok，网页端配置隧道，取得域名，打开ngrok客户端，确认域名能够访问系统
 * 2.申请自己的微信公众号 mp.weixin.qq.com
 * 3.到公众号平台查找自己的appid和secret，填入下方的工具类相应位置
 * 4.配置公众号的JS安全域名，公众号设置——功能设置
 * 5.添加白名单ip：（1）首先需要ngrok远程服务器的ip，可以通过部署上ngrok的网址抓包获得
 * 				（2）本机ip，可以百度ip得知(注意，更换本机地址或网络，需重新配置)
 * 6.设置自动回复，嵌入a标签
 *
 *
 *
 */
public class SignatureUtil {
	public static class Config {

		private String appid;
		// 生成签名的时间戳
		private String timestamp;
		// 生成签名的随机串
		private String nonceStr;
		// 签名
		private String signature;
		private String url;

		public String getAppid() {
			return appid;
		}

		public void setAppid(String appid) {
			this.appid = appid;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getNonceStr() {
			return nonceStr;
		}

		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String toJSON() {
			Map<String, String> data = new HashMap();
			data.put("appid", appid);
			data.put("timestamp", timestamp);
			data.put("nonceStr", nonceStr);
			data.put("signature", signature);
			JSONObject obj = new JSONObject(data);
			return obj.toString();
		}

	}

	// 获取config初始化参数
	public static Config getConfig(String url) throws Exception {
		Config config = new Config();
		//TODO 这里一定要设置, 否则无法使用微信SDK，进入mp.weixin.qq.com开发——基本配置,填入appid
		config.setAppid("wx2f59ecbe7e76ac68");
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		config.setTimestamp(timestamp);
		String nonceStr = String.valueOf(ThreadLocalRandom.current().nextInt(89999999) + 10000000);
		config.setNonceStr(nonceStr);
		String signature = getSignature(TicketUtil.getTicket(), nonceStr, timestamp, url);
		config.setSignature(signature);
		config.setUrl(url);
		return config;
	}

	public static String getSignature(String jsapi_ticket, String noncestr, String timestamp, String url) {
		Map<String, String> params = new HashMap();
		params.put("jsapi_ticket", jsapi_ticket);
		params.put("noncestr", noncestr);
		params.put("timestamp", timestamp);
		params.put("url", url);
		Map<String, String> sortParams = sortAsc(params);
		String string1 = mapJoin(sortParams, false);
		System.out.println(params);
		System.out.println("STRING1:"+string1);
		try {
			return DigestUtils.sha1Hex(string1.getBytes("UTF-8"));
		} catch (IOException var8) {
			return "";
		}
	}

	// 对参数进行排序，得到一个有序的参数列表
	public static Map<String, String> sortAsc(Map<String, String> map) {
		HashMap<String, String> tempMap = new LinkedHashMap();
		List<Entry<String, String>> infoIds = new ArrayList(map.entrySet());
		Collections.sort(infoIds, new Comparator<Entry<String, String>>() {
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return ((String) o1.getKey()).compareTo((String) o2.getKey());
			}
		});
		for (int i = 0; i < infoIds.size(); ++i) {
			Entry<String, String> item = (Entry) infoIds.get(i);
			tempMap.put(item.getKey(), item.getValue());
		}

		return tempMap;
	}

	// 组装参数，获得string1

	public static String mapJoin(Map<String, String> map, boolean valueUrlEncode) {
		StringBuilder sb = new StringBuilder();
		Iterator i$ = map.keySet().iterator();

		while (true) {
			String key;
			do {
				do {
					if (!i$.hasNext()) {
						if (sb.length() > 0) {
							sb.deleteCharAt(sb.length() - 1);
						}

						return sb.toString();
					}

					key = (String) i$.next();
				} while (map.get(key) == null);
			} while ("".equals(map.get(key)));

			try {
				String temp = key.endsWith("_") && key.length() > 1 ? key.substring(0, key.length() - 1) : key;
				sb.append(temp);
				sb.append("=");
				String value = (String) map.get(key);
				if (valueUrlEncode) {
					value = URLEncoder.encode((String) map.get(key), "utf-8").replace("+", "%20");
				}

				sb.append(value);
				sb.append("&");
			} catch (UnsupportedEncodingException var7) {
				var7.printStackTrace();
			}
		}
	}

}
