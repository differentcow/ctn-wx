/**
 * 
 */
package com.ctn.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

/**
 * @author Zale
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
	private static Logger logger = Logger.getLogger(MyExceptionHandler.class.getName());
	 @ExceptionHandler(value = { Exception.class, Throwable.class })
	    @ResponseBody
	    public Object handleException(Exception ex) {
         ex.printStackTrace();
	        logger.warning(ex.getMessage());
	       
	        return ex.getMessage();
	    }
}
