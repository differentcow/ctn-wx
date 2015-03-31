package com.ctn.web.controller;

import com.ctn.entity.model.Esl;
import com.ctn.entity.query.GenericQueryParam;
import com.ctn.entity.query.QueryKey;
import com.ctn.entity.query.SortCond;
import com.ctn.service.esl.EslService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hp on 2015/3/30.
 */

@RestController
@RequestMapping("/esl")
public class ESLController {

    @Autowired
    private EslService<Esl> srv;

    @RequestMapping(value = "/lst", method = RequestMethod.GET)
    public Object getEsl(@RequestParam(value = "isCustomer",required = false)Integer isCustomer,
                                  HttpServletRequest request,HttpServletResponse response){
        GenericQueryParam param = new GenericQueryParam();
        param.put(new QueryKey("isCustomer", QueryKey.Operators.EQ),isCustomer);
        param.addSortCond(new SortCond("name", SortCond.Order.ASC));
        java.util.List<Esl> list = srv.findAll(param);
        Map map =  new HashMap();
        map.put("state",1);
        map.put("data",list);
        return map;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public Object showTemperature(@RequestParam(value = "id",required = false)Integer id,
                             HttpServletRequest request,HttpServletResponse response){
        System.out.println(id);
        Map<String,String> map = new HashMap<String,String>();
        map.put("state","1");
        return map;
    }

    @RequestMapping(value = "/content", method = RequestMethod.POST)
    public Object eslContent(@RequestParam(value = "uploadImg",required = false)MultipartFile file,
                          @RequestParam(value = "x1",required = false)String x1,
                          @RequestParam(value = "y1",required = false)String y1,
                          @RequestParam(value = "w",required = false)Integer w,
                          @RequestParam(value = "h",required = false)Integer h,
                          @RequestParam(value = "content",required = false)String content,
                          HttpServletRequest request,HttpServletResponse response){

        Map<String,String> map = new HashMap<String,String>();
        try {
            String name = file.getOriginalFilename();
            if (StringUtils.isNotBlank(name)){
                String type = "";
                if (name.lastIndexOf(".") != -1){
                    type = name.substring(name.lastIndexOf(".")+1,name.length());
                }
                byte[] test = cutImage(file.getInputStream(),"C:/Users/hp/Desktop/GitHub/"+System.currentTimeMillis()+".bmp",new BigDecimal(x1).intValue(),new BigDecimal(y1).intValue(),w,h,type);
                for (byte t : test){
                    System.out.print(" "+t);
                }
            }
            System.out.println(content);
            map.put("status","ok");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status","failed");
        }
        /*try {
            response.getWriter().write("{'state':'ok'");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return map;
    }

    private byte[] cutImage(InputStream src,String dest,int x,int y,int w,int h,String type) throws IOException{
        Iterator iterator = ImageIO.getImageReadersByFormatName(type.toLowerCase());
        ImageReader reader = (ImageReader)iterator.next();
        ImageInputStream iis = ImageIO.createImageInputStream(src);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x, y, w,h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0,param);
        BufferedImage bmp = new BufferedImage(w, h,BufferedImage.TYPE_BYTE_BINARY);
        Graphics g = bmp.getGraphics();
        g.drawImage(bi, 0, 0, null);
        g.dispose();
        File des = new File(dest);
        ImageIO.write(bmp, "BMP", des);
        return image2byte(des);
    }

    private byte[] image2byte(File file){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

}
