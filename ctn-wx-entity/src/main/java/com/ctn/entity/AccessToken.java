package com.ctn.entity;

import java.io.Serializable;

/**
 * 微信Access Token
 * 
 * @author Barry.LJ.Huang
 *
 */
public class AccessToken extends WeChatJSONObject implements Serializable{

	private static final long serialVersionUID = -7925292177959586074L;
	
	private String access_token;
	private String expires_in;

	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
}
