package com.ctn.tcp;

import com.ctn.entity.tcp.*;
import com.ctn.util.HexUtil;
import org.apache.commons.lang.StringUtils;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by barry on 2015/3/13.
 */
public class FrameManager {

    private Map<String,Object> map;
    private Frame frame;

    public FrameManager(){
    }

    public FrameManager(String path){
        load(path);
    }

    public Frame getFrame() {
        return frame;
    }

    public void load(String path){
        if (map == null){
            map = new HashMap<String,Object>();
        }
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        try {
            XMLEventReader reader = factory.createXMLEventReader(in);
            frame = new Frame();
            Segment segment = null;
            Head head = null;
            Validate validate = null;
            boolean isCheck = false;
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement start = event.asStartElement();
                    String name = start.getName().toString();
                    if(ELEMENT_NAME_FRAME.equals(name)){
                        frame.setSegment(new ArrayList<Segment>());
                        map.put(name,frame);
                    }
                    if(ELEMENT_NAME_FRAME_HEAD.equals(name)){
                        head = new Head();
                        map.put(name,head);
                    }
                    if(ELEMENT_NAME_SEGMENT.equals(name)){
                        segment = new Segment();
                        map.put(name,segment);
                    }
                    if(ELEMENT_NAME_VALIDATE.equals(name)){
                        validate = new Validate();
                        validate.setExpress(new ArrayList<Express>());
                        if (isCheck){
                            map.put(ELEMENT_NAME_CHECK,validate);
                        }else{
                            map.put(ELEMENT_NAME_EXPRESS,validate);
                        }
                        map.put(name,validate);
                    }
                    if (ELEMENT_NAME_EXPRESS.equals(name)){
                        map.put(name,new Express());
                    }
                    if (ELEMENT_NAME_CHECK.equals(name)){
                        isCheck = true;
                    }
                    Iterator<Attribute> it =  start.getAttributes();
                    while (it.hasNext()){
                        Attribute attr = it.next();
                        Method method = map.get(name).getClass().getMethod(getMethodName(attr.getName().toString()),String.class);
                        method.invoke(map.get(name),attr.getValue());
                        if (ELEMENT_NAME_EXPRESS.equals(name)){
                            ((Validate)map.get(ELEMENT_NAME_VALIDATE)).getExpress().add((Express)map.get(name));
                        }
                    }
                }
                if (event.isEndElement()){
                    EndElement end = event.asEndElement();
                    String name = end.getName().toString();
                    if(ELEMENT_NAME_FRAME_HEAD.equals(name)){
                        map.put(name,head);
                        ((Frame)map.get(ELEMENT_NAME_FRAME)).setHead((Head) map.get(name));
                    }
                    if(ELEMENT_NAME_SEGMENT.equals(name)){
                        ((Frame)map.get(ELEMENT_NAME_FRAME)).getSegment().add((Segment)map.get(name));
                    }
                    if(ELEMENT_NAME_VALIDATE.equals(name)){
                        ((Segment)map.get(ELEMENT_NAME_SEGMENT)).setValidate((Validate)map.get(name));
                    }
                    if (ELEMENT_NAME_CHECK.equals(name)){
                        isCheck = false;
                        frame.setValidate((Validate)map.get(name));
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String getMethodName(String name){
        return "set"+name.substring(0,1).toUpperCase()+name.substring(1);
    }

    public void searchFrame(byte[] bytes){
        Head h = frame.getHead();
        byte[] b = null;
        if ("16".equals(h.getType())){
            b = HexUtil.hexStr2Byte(h.getValue());
        }else if("10".equals(h.getType())){

        }
        int count = 0;
        Integer result = -1;
        label : for (int i = 0; i < bytes.length;i++) {
            for(int j = 0; j < b.length;j++){
                if(b[j] == bytes[i+j]){
                    count++;
                }else{
                    continue label;
                }
                if(count == b.length){
                    result = i;
                    break label;
                }
            }
            count = 0;
        }
        h.setHeadIndex(result);
    }

    private boolean test(byte[] bytes){
        if (frame.getHead() == null){
            searchFrame(bytes);
        }
        if (frame.getHead().getHeadIndex() == -1){
            return false;
        }
        Validate v1 = frame.getValidate();
        //检验Frame
        if(v1 != null){
            List<Express> list = v1.getExpress();
            for (Express e : list){

            }
        }
        return false;
    }
    private List<String> bs1 = Arrays.asList("*", "/", "%","+","-");
    private List<String> bs2 = Arrays.asList("v10", "this", "v16");

    private Integer cal(String t1,String t2,String oper){
        if ("+".equals(oper)){
            return new BigDecimal(t1).add(new BigDecimal(t2)).intValue();
        }
        if ("-".equals(oper)){
            return new BigDecimal(t1).subtract(new BigDecimal(t2)).intValue();
        }
        if ("*".equals(oper)){
            return new BigDecimal(t1).multiply(new BigDecimal(t2)).intValue();
        }
        if ("/".equals(oper)){
            return new BigDecimal(t1).divide(new BigDecimal(t2)).intValue();
        }
        if ("%".equals(oper)){
            return new BigDecimal(t1).divide(new BigDecimal(t2)).intValue();
        }
        return null;
    }

    private void getThisVal16(byte[] bytes,String key){
        if (key.indexOf(",") != -1){

        }else if(StringUtils.isNumeric(key)){

        }else{

        }
    }

    private void checkExpress(Express e,byte[] bytes){
        if(StringUtils.isNotBlank(e.getOperate())){
            List<String> list = e.getLeftList();
            List<String> list2 = e.getLeftList();
            Stack<String> s = new Stack<String>();
            for (String str : list){
                if (bs2.contains(str)){
                    if (s.size() >= 1){
                        String t1 = s.pop();
                        if(StringUtils.isNumeric(t1)){
                            if("this".equals(str)){

                            }
                            if("v10".equals(str)){

                            }
                            if("v16".equals(str)){

                            }
                        }else{
                            if("this".equals(str)){
                                if(t1.indexOf(",")!=-1){
                                    //this[0],this[3],this[2],
                                    String[] ss = t1.split(",");
                                    for (int index = Integer.valueOf(ss[0]);index < bytes.length;){
                                        for (int i = index;i < Integer.valueOf(ss[1]); i++){
//                                           if(bytes[index] == )
                                        }
                                    }

                                }
                            }
                            if("v10".equals(str)){

                            }
                            if("v16".equals(str)){

                            }
                        }
                    }
                }else if(bs1.contains(str)){
                    if (s.size() >= 2){
                        String t2 = s.pop();
                        String t1 = s.pop();
                        int result = cal(t1,t2,str);
                    }
                }else{
                    s.push(str);
                }
            }
        }
    }


    public static void main(String[] args){
        FrameManager f = new FrameManager();
        f.load("frame/frame_up.xml");
    }


    private final static String ELEMENT_NAME_FRAME = "Frame";
    private final static String ELEMENT_NAME_FRAME_HEAD = "head";
    private final static String ELEMENT_NAME_CHECK = "check";
    private final static String ELEMENT_NAME_SEGMENT = "segment";
    private final static String ELEMENT_NAME_VALIDATE = "validate";
    private final static String ELEMENT_NAME_EXPRESS = "express";



}
