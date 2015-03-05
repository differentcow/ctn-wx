package com.ctn.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 
 * HTTP 连接工具
 * 
 * @author Barry.LJ.Huang
 *
 */
public class HttpRequestUtil {

	/**
	 * Get 方式请求
	 * 
	 * @param url
	 * @return
	 */
	public static String getRequest(String url){
		/** 创建HttpClientBuilder */  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        
        /** HttpClient */
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build(); 
        
        HttpGet httpGet = new HttpGet(url);
        
        String returnMsg = "";	/** 返回的信息 */
        //System.out.println(httpGet.getRequestLine());		/** 打印请求方式以及URL */
        try {
        	/** 设置请求和传输超时时间 */
        	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();  
        	httpGet.setConfig(requestConfig);
        	
        	/** 执行get请求 */  
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);  
            /** 获取响应消息实体 */  
            HttpEntity entity = httpResponse.getEntity();  

            //System.out.println("status:" + httpResponse.getStatusLine());	//响应状态 */  
            
            /** 判断响应实体是否为空 */  
            if (entity != null) {  
                //System.out.println("contentEncoding:" + entity.getContentEncoding());	//获取编码 */  
                //System.out.println("response content:" + EntityUtils.toString(entity));  
            	returnMsg = EntityUtils.toString(entity);	/**  返回的结果 */
            }
		} catch (Exception e) {
			e.printStackTrace();
			return returnMsg;
		}finally{
			try {
				//关闭流并释放资源  
				closeableHttpClient.close();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}
		}
        
		return returnMsg;
	}
	
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"button\":[{\"name\":\"介绍\",\"sub_button\":[{\"type\":\"click\",\"name\":\"文本介绍\",\"key\":\"text_click\"},{\"type\":\"view\",\"name\":\"图文介绍\",\"key\":\"pic_click\"}],{\"type\":\"view\",\"name\":\"百度一下\",\"url\":\"http://www.baidu.com\"}]}";
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		String returnMsg = HttpRequestUtil.postRequest("",json);
		System.out.println(returnMsg);
	}
	
	/**
	 * Post 方式请求
	 * 
	 * @param url
	 * @return
	 */
	public static String postRequest(String url,String jsonParam){
		/** 创建HttpClientBuilder */  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        
        /** HttpClient */
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build(); 
        
        HttpPost httpPost = new HttpPost(url);  
        
        String returnMsg = "";	/** 返回的信息 */
	    try {
	        /** 设置请求和传输超时时间 */
	    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();  
	        httpPost.setConfig(requestConfig);
	        /** 设置参数（JSON） */
	        httpPost.setEntity(new StringEntity("{\"button\":[{\"type\":\"click\",\"name\":\"button1\",\"key\":\"btn_1\"},{\"type\":\"click\",\"name\":\"button2\",\"key\":\"btn_2\"}]}"));
	        /** 执行Post请求 */
	        HttpResponse response = closeableHttpClient.execute(httpPost);
	        /** 获取响应消息实体 */
	        HttpEntity entity = response.getEntity();
	        /** 判断响应实体是否为空 */
	        if (entity != null) {  
	        	returnMsg = EntityUtils.toString(entity, "UTF-8");  
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return returnMsg;
	    } finally{  
	        try {  
	        	closeableHttpClient.close();  
	        } catch (IOException e) {  
	        	e.printStackTrace();
	        }
	    }   
	    return returnMsg;
	}
	
}
