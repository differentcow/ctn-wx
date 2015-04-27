package com.ctn.web.controller;

import com.ctn.util.SendMail;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${mail.server.host}")
    private String host;

    @Value("${mail.server.username}")
    private String username;

    @Value("${mail.server.password}")
    private String password;

    @Value("${mail.server.address}")
    private String address;

    @PostConstruct
    public void init(){
        map = new HashMap<String,Object>();
    }


    @RequestMapping(value = "/hide", method = RequestMethod.POST)
    public Object hideLogin(HttpServletRequest req,@RequestBody Map<String,String> values) {
        String account = values.get("account");
        map.put("code",0);
        if ("zhengdan".equals(account)){
            req.getSession().setAttribute("barry",account);
            SendMail sm = new SendMail(host,username,password);
            sm.setAddress(address,"different_cow@163.com","Trouble House Consult");
            sm.send("Dear barry:\n\n\t\tEnter the hide mode!\n\nBest Regards,\n"+account);
            map.put("url","../mobile/me.html");
            map.put("code",1);
        }
        return map;
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(HttpServletRequest req,@RequestBody Map<String,String> values) {
        String account = values.get("account");
        String password = values.get("password");
        if("barry".equals(account) && "19870415".equals(password)){
            map.put("state",1);
            map.put("url","hide.html");
            req.getSession().setAttribute("username",account);
        }else if("joanna".equals(account) && "19890701".equals(password)){
            map.put("state",1);
            map.put("url","../mobile/screen.html");
            req.getSession().setAttribute("username",account);
        }else{
            map.put("state",0);
        }
		return map;
	}

}
