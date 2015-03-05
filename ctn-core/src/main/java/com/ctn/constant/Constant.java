package com.ctn.constant;

public class Constant {
	/**
	 * 微信验证参数值
	 */
	public static final String WE_CHAT_CHECK_PARAM_SIGNATURE = "signature";
	public static final String WE_CHAT_CHECK_PARAM_NONCE = "nonce";
	public static final String WE_CHAT_CHECK_PARAM_TIMESTAMP = "timestamp";
	public static final String WE_CHAT_CHECK_PARAM_ECHOSTR = "echostr";
	
	/**
	 * 编码
	 */
	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String ENCODE_GBK = "GBK";
	
	
	/**
	 * 缓存参数
	 */
	public static final String CACHE_ACCESS_TOKEN_NAME = "AccessTokenCache";
	public static final String CACHE_AUTH_NAME = "AuthCache";
	public static final String CACHE_AUTH_KEY="AuthKey";
	public static final String CACHE_ACCESS_TOKEN_KEY = "AccessTokenKey";
	public static final String CACHE_MANAGER_BEAN_NAME = "cacheManager";
	
	
	/**
	 * Schedule time
	 */
	public static final int REFRESH_ACCESS_TOKEN=2*60*60*1000;
	
	/**
	 * verify Condition
	 */
	public static final int MENU_MAX_NUMBER = 3;
	public static final int SUB_MENU_MAX_NUMBER=5;
	
}
