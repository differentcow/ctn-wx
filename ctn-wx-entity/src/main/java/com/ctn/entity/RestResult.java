/**
 * 
 */
package com.ctn.entity;

import java.io.Serializable;

/**
 * @author Zale
 *
 */
public class RestResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6186126100600416135L;
	private int code;
	private String msg;
	private Object result;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
}
