/**
 * 
 */
package com.ctn.entity;

import java.io.Serializable;

/**
 * @author Zale
 *
 */
public class WxResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -669638217547845548L;
	
	private int errorCode;
	private String errmsg;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
