/**
 * 
 */
package com.ctn.entity;

import java.io.Serializable;
import java.util.List;

import com.ctn.entity.type.ButtonType;


/**
 * @author Zale
 *
 */
public class Button implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4599967355730731145L;
	
	private ButtonType type;
	private String name;
	private String key;
	private String url;
	private List<Button> sub_button;
	public ButtonType getType() {
		return type;
	}
	public void setType(ButtonType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Button> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}
	
	
	
	
}
