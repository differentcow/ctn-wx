package com.ctn.entity;

/**
 * 微信返回二维码信息
 * 
 * @author Barry.LJ.Huang
 *
 */
public class QRCode extends WeChatJSONObject{

	private String expire_seconds;
	private String ticket;

	public String getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
