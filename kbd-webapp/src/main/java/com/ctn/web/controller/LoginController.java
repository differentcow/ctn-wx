package com.ctn.web.controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/login")
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());
	
    private Map<String,Object> map;

    @PostConstruct
    public void init(){
        map = new HashMap<String,Object>();
    }


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(HttpServletRequest req,@RequestBody Map<String,String> values) {
        String account = values.get("account");
        String password = values.get("password");
        if("barry".equals(account) && "19870415".equals(password)){
            map.put("state",1);
            map.put("url","../mobile/test.html");
            req.getSession().setAttribute("username",account);
        }else if("test".equals(account) && "123456".equals(password)){
            map.put("state",1);
            map.put("url","../mobile/test.html");
            req.getSession().setAttribute("username",account);
        }else{
            map.put("state",0);
        }
		return map;
	}

}
