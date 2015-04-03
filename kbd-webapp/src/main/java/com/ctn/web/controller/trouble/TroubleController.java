package com.ctn.web.controller.trouble;

import com.ctn.util.SendMail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2015/4/3.
 */
@RestController
@RequestMapping("/trouble")
public class TroubleController {

    private Map<String,Object> map;

    @Value("${mail.server.host}")
    private String host;

    @Value("${mail.server.username}")
    private String username;

    @Value("${mail.server.password}")
    private String password;

    @Value("${mail.server.address}")
    private String address;

    @Value("${store.text.path}")
    private String path;

    @PostConstruct
    public void init(){
        map = new HashMap<String,Object>();
    }

    public String readTxtFile(String filePath){
        StringBuffer sb = new StringBuffer("");
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append(lineTxt);
                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return sb.toString();
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public Object viewArticle(HttpServletRequest request,@RequestParam(value = "user",required = false)String user){
        if (StringUtils.isNotBlank(user)){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        return map;
    }

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public Object postConsult(HttpServletRequest request,@RequestBody Map<String,String> values){
        String content = values.get("content");
        String user =  values.get("user");
        if(StringUtils.isBlank(content)){
            map.put("state",0);
        }else{
            SendMail sm = new SendMail(host,username,password);
            sm.setAddress(address,"different_cow@163.com","Trouble House Consult");
            sm.send("Dear barry:\n\n\t\t"+content+"\n\nBest Regards,\n"+user);
//            try {
//                saveFileFromInputStream(content,path);
                map.put("state", 1);
//            } catch (IOException e) {
//                e.printStackTrace();
//                map.put("state",0);
//            }
        }
        return map;
    }


    public void saveFileFromInputStream(String content,String filename) throws IOException {
        FileWriter fs = new FileWriter(new File(filename),true);
        StringBuffer sb = new StringBuffer("--------------------------------");
        sb.append(new Date()).append("--------------------------------\n");
        sb.append(content).append("\n");
        sb.append("--------------------------------").append(new Date()).append("--------------------------------\n");
        fs.write(sb.toString());
        fs.flush();
        fs.close();
    }

}
