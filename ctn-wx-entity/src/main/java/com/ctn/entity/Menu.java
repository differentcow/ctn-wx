/**
 * 
 */
package com.ctn.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zale
 *
 */
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8029656340197051495L;
	
	private List<Button> button;

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}
	
	

}
