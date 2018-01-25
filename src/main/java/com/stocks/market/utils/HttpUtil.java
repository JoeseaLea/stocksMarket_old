package com.stocks.market.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Http请求工具类
 * @author ljxiang9
 * @version 2017年3月23日
 * @Copyright (C)2017 , GRGBanking Equipment Co., Ltd
 */
public class HttpUtil {
	/**
	 * 向指定url发送Get请求，默认返回结果编码为gbk
	 * @param url 请求地址
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, "gbk");
	}
	
	/**
	 * 向指定url发送Get请求，默认不带参数
	 * @param url 请求地址
	 * @param charset 返回结果编码格式
	 * @return
	 */
	public static String sendGet(String url, String charset) {
		return sendGet(url, null, charset);
	}
	
	/**
	 * 向指定url发送Get请求
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param charset 返回结果编码格式
	 * @return
	 */
	public static String sendGet(String url, Map<String, Object> params, String charset) {
		/*
		 * 构建请求参数
		 */
		StringBuffer reqParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> entry : params.entrySet()) {
				reqParams.append(entry.getKey());
				reqParams.append("=");
				reqParams.append(entry.getValue());
				reqParams.append("&");
			}
		}
		
		HttpURLConnection connection = null;
		BufferedReader bufferedReader = null;
		StringBuffer resultBuffer = new StringBuffer();
		
		try {
			/*
			 * 构建完整请求URL
			 */
			URL urlGet = null;
			if (reqParams != null && reqParams.length() > 0) {
				urlGet = new URL(url + "?" + reqParams.substring(0, reqParams.length() - 1));
			} else {
				urlGet = new URL(url);
			}
			
			connection = (HttpURLConnection) urlGet.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String temp;
			while ((temp = bufferedReader.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			/*
			 * 断开连接
			 */
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					bufferedReader = null;
					throw new RuntimeException(e);
				} finally {
					if (connection != null) {
						connection.disconnect();
						connection = null;
					}
				}
			}
		}
		return resultBuffer.toString();
	}
}
