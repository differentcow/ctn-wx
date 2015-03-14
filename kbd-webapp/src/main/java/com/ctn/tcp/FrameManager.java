package com.ctn.tcp;

import com.ctn.entity.tcp.Frame;
import com.ctn.entity.tcp.Segment;
import com.ctn.entity.tcp.Validate;
import com.ctn.util.HexUtil;

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
import java.util.*;

/**
 * Created by barry on 2015/3/13.
 */
public class FrameManager {

    private Map<String,Object> map;

    private Map<String,Frame> frameMap;

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
            Validate validate = null;
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement start = event.asStartElement();
                    String name = start.getName().toString();
//                    System.out.println(name);
                    if(ELEMENT_NAME_FRAME.equals(name)){
                        frame.setSegment(new ArrayList<Segment>());
                        map.put(name,frame);
                    }
                    if(ELEMENT_NAME_SEGMENT.equals(name)){
                        segment = new Segment();
                        map.put(name,segment);
                    }
                    if(ELEMENT_NAME_VALIDATE.equals(name)){
                        validate = new Validate();
                        map.put(name,validate);
                        map.put(ELEMENT_NAME_OPTION,validate);
                    }
                    Iterator<Attribute> it =  start.getAttributes();
                    while (it.hasNext()){
                        Attribute attr = it.next();
                        Method method = map.get(name).getClass().getMethod(getMethodName(attr.getName().toString()),String.class);
                        method.invoke(map.get(name),attr.getValue());
//                        System.out.println("attr["+attr.getName() + ":" + attr.getValue()+"]");
                    }
                }
                if (event.isEndElement()){
                    EndElement end = event.asEndElement();
                    String name = end.getName().toString();
//                    System.out.println("/"+name);
                    if(ELEMENT_NAME_SEGMENT.equals(name)){
                        ((Frame)map.get(ELEMENT_NAME_FRAME)).getSegment().add((Segment)map.get(name));
                    }
                    if(ELEMENT_NAME_VALIDATE.equals(name)){
                        ((Segment)map.get(ELEMENT_NAME_SEGMENT)).setValidate((Validate)map.get(name));
                    }
                }
            }
            System.out.println(frame);
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

    private Map<String,Segment> indexMap;

    public boolean vlidate(byte[] bytes){

        if(indexMap == null){
            indexMap = new HashMap<String,Segment>();
        }

        List<Segment> segments = frame.getSegment();
        if(!segments.isEmpty()){
            int count = 0;
            label:for (int index = 0;index < bytes.length;){
                Segment s = segments.get(count);
                if(s.getValidate() == null){
                    continue label;
                }
                int len = Integer.valueOf(s.getLen());
                if(validate(s.getValidate(),bytes,len,index)){
                    s.setStart(index);
                    s.setEnd(index+len);
                    indexMap.put(s.getName(),s);
                }else{
                    index += len;
                    continue label;
                }
            }
        }


        return false;
    }


    public static void main(String[] args){
        FrameManager f = new FrameManager();
        f.load("frame/frame_up.xml");
    }

    /**
     * 验证
     *
     * @return
     */
    public boolean validate(Validate validate,byte[] val,int len,int start){

        if(MODULE_VALIDATE_VALUE.equals(validate.getModule())){
            //检验值
            String type = validate.getType();
            if(VALIDATE_TYPE_VAL_16.equals(type)){
                byte[] valByte = HexUtil.hexStr2Byte(validate.getValue());
                boolean flag = false;
                if(validate.getValue().indexOf(",")!=-1){
                    label:for(int i = 0; i < len; i++ ){
                        if(val[start+i] == valByte[i]){
                            flag = true;
                            break label;
                        }
                    }
                }else{
                    if(len != valByte.length){
                        return false;
                    }
                    flag = true;
                    label1:for(int i = 0; i < len; i++ ){
                        if(val[start+i] != valByte[i]){
                            flag = false;
                            break label1;
                        }
                    }
                }
                return flag;
            }
        }else if(MODULE_VALIDATE_LENGTH.equals(validate.getModule())){
            //检验长度

        }


        return false;
    }

    private boolean checkValue(String type,String valFrom,String valTo){

        if (valFrom.indexOf(",") != -1){
            String[] vals = valFrom.split(",");
        }

        return false;
    }


    private final static String MODULE_VALIDATE_VALUE = "val";
    private final static String MODULE_VALIDATE_LENGTH = "len";

    private final static String ELEMENT_NAME_FRAME = "Frame";
    private final static String ELEMENT_NAME_SEGMENT = "segment";
    private final static String ELEMENT_NAME_VALIDATE = "validate";
    private final static String ELEMENT_NAME_OPTION = "option";

    private final static String VALIDATE_TYPE_VAL_16 = "16";
    private final static String VALIDATE_TYPE_VAL_10 = "10";
    private final static String VALIDATE_TYPE_LEN_SUM = "sum";



}
