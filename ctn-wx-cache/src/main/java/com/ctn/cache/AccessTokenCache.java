
package com.ctn.cache;

import org.springframework.stereotype.Service;

import com.ctn.entity.AccessToken;
@Service
public class AccessTokenCache extends EhCacheManager{
	/**
	 * 存入Access Token缓存数据
	 * 
	 * @param key
	 * @param token
	 */
	public void setAccessTokenCache(String key, AccessToken token){
		save(key, token);
	}
	
	/**
	 * 获取Access Token缓存数据
	 * 
	 * @param key
	 * @return
	 */
	public AccessToken getAccessToken(String key){
		//TODO 如果缓存中没有相应的数据，请重新获取
		return (AccessToken)get(key);
	}

	@Override
	protected String getCacheName() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}
	
}
