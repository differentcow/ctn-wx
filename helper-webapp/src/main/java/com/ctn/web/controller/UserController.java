/**
 * 
 */
package com.ctn.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TR
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@RequestMapping(method=RequestMethod.PUT)
	public Object login(HttpServletRequest request){
		return null;
	}
	@RequestMapping(method=RequestMethod.POST)
	public Object add(HttpServletRequest request){
		return null;
	}
}
