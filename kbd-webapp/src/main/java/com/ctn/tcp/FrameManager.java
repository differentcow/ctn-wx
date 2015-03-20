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
    private Map<String,Packet> mapPacket;
    private Integer index;

    public FrameManager(){
    }

    public FrameManager(String path){
        load(path);
    }

    public Frame getFrame() {
        return frame;
    }

    public void loadPacket(String path){
        if(mapPacket == null){
            mapPacket = new HashMap<String,Packet>();
        }
        if (map == null){
            map = new HashMap<String,Object>();
        }
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        try {
            XMLEventReader reader = factory.createXMLEventReader(in);
            Packet packet = new Packet();
            Segment segment = null;
            com.ctn.entity.tcp.Attribute att = null;
            Head head = null;
            Validate validate = null;
            boolean isCheck = false;
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement start = event.asStartElement();
                    String name = start.getName().toString();
                    if(ELEMENT_NAME_PACKET.equals(name)){
                        packet.setSegment(new ArrayList<Segment>());
                        map.put(name,packet);
                    }
                    if(ELEMENT_NAME_SEGMENT.equals(name)){
                        segment = new Segment();
                        segment.setAttr(new ArrayList<com.ctn.entity.tcp.Attribute>());
                        map.put(name,segment);
                    }
                    if (ELEMENT_NAME_ATTR.equals(name)){
                        att = new com.ctn.entity.tcp.Attribute();
                        map.put(name,att);
                    }
                    Iterator<Attribute> it =  start.getAttributes();
                    while (it.hasNext()){
                        Attribute attr = it.next();
                        Method method = map.get(name).getClass().getMethod(getMethodName(attr.getName().toString()),String.class);
                        method.invoke(map.get(name),attr.getValue());
                    }
                    if(ELEMENT_NAME_PACKET.equals(name)){
                        Object o =map.get(name);
                        if (o != null && StringUtils.isNotBlank(((Packet)o).getId())){
                            mapPacket.put(((Packet)o).getId(),packet);
                        }
                    }
                }
                if (event.isEndElement()){
                    EndElement end = event.asEndElement();
                    String name = end.getName().toString();
                    if(ELEMENT_NAME_SEGMENT.equals(name)){
                        ((Packet)map.get(ELEMENT_NAME_PACKET)).getSegment().add((Segment)map.get(name));
                    }
                    if(ELEMENT_NAME_ATTR.equals(name)){
                        ((Segment)map.get(ELEMENT_NAME_SEGMENT)).getAttr().add((com.ctn.entity.tcp.Attribute) map.get(name));
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

    private Packet framePacket;

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
            Data data = null;
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
                        segment.setData(new ArrayList<Data>());
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
                    if (ELEMENT_NAME_DATA.equals(name)){
                        data = new Data();
                        map.put(name,data);
                    }
                    Iterator<Attribute> it =  start.getAttributes();
                    while (it.hasNext()){
                        Attribute attr = it.next();
                        Method method = map.get(name).getClass().getMethod(getMethodName(attr.getName().toString()),String.class);
                        method.invoke(map.get(name),attr.getValue());
                    }
                    if (ELEMENT_NAME_EXPRESS.equals(name)){
                        ((Validate)map.get(ELEMENT_NAME_VALIDATE)).getExpress().add((Express)map.get(name));
                    }
                    if (ELEMENT_NAME_DATA.equals(name)){
                        ((Segment)map.get(ELEMENT_NAME_SEGMENT)).getData().add((Data)map.get(name));
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

    public void convert(byte[] bytes){
        //寻找帧头
        searchFrame(bytes);
        //如果寻找不到，说明此数据帧不是系统所需数据帧
        if (index == -1){
            return;
        }
        //检验数据帧是否正确
        boolean flag = check(bytes);
        //如果正确，开始转换数据
        if (flag){
            //获取数据段
            List<Segment> segments =  frame.getSegment();
            int mark = index;   //标记当前位置
            for (Segment s : segments){
                //设置开始下标
                s.setStart(mark);
                if (s.getValidate() != null && s.getValidate().getExpress() != null){
                    List<Express> l = s.getValidate().getExpress();
                    List<Boolean> bools = new ArrayList<Boolean>();
                    for (Express e : l){
                        e.setLen(s.getLen() == null?-1:Integer.valueOf(s.getLen()));
                        e.setStart(mark);
                        //开始检验数据段的正确性
                        bools.add(decodeExpress(e, bytes));
                    }
                    boolean flags = checkByValidate(bools,s.getValidate().getBool());
                    //通过检验，装载数据
                    if(flags){
                         System.out.println("通过检验，装载数据");
                    }else{
                        //未通过检验，认为不是系统所需数据，放弃数据
                        System.out.println("未通过检验，认为不是系统所需数据，放弃数据");
                    }
                    for (Data data : s.getData()){
                        if (decodeExpress(data.getExFilter(),bytes)){
                            framePacket = mapPacket.get(data.getRef());
                            String start = decode(data.getExStart().getExpressList(),null,null,bytes);
                            String len = decode(data.getExLen().getExpressList(),null,null,bytes);
                            if (StringUtils.isNumeric(start) && StringUtils.isNumeric(len)){
                                byte[] bs = HexUtil.getSubBytes(bytes,Integer.valueOf(start),Integer.valueOf(len),false);
                            }
                        }

                    }
                }
                mark = mark + (s.getLen() == null?0:Integer.valueOf(s.getLen()));
            }
        }
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
        index = result;
    }

    private boolean checkByValidate(List<Boolean> bools,String type){
        boolean flag=false;
        label : for (Boolean b : bools){
            if ("or".equals(type) && b){
                flag = true;
                break label;
            }
            if ("and".equals(type)){
                if(!b){
                    flag = false;
                    break label;
                }else{
                    flag = true;
                }
            }
        }
        return flag;
    }

    private boolean check(byte[] bytes){
        if (frame.getHead() == null){
            searchFrame(bytes);
        }
        if (frame.getHead().getHeadIndex() == -1){
            return false;
        }
        Validate v1 = frame.getValidate();
        boolean flag = false;
        //检验Frame
        if(v1 != null){
            List<Boolean> bools = new ArrayList<Boolean>();
            List<Express> list = v1.getExpress();
            for (Express e : list){
                bools.add(decodeExpress(e,bytes));
            }
            flag = checkByValidate(bools,v1.getBool());
        }
        return flag;
    }

    public boolean decodeExpress(Express express,byte[] bytes){
        boolean result = false;
        if (express.getOperate() != null){

            String leftResult = decode(express.getLeftList(),express.getStart(),express.getLen(),bytes);
            String righResult = decode(express.getRightList(),express.getStart(),express.getLen(),bytes);

            if ("eq".equals(express.getOperate())){
                if(leftResult.equals(righResult)){
                    result = true;
                }
            }else if("lt".equals(express.getOperate())){

            }else if("gt".equals(express.getOperate())){

            }else if("nl".equals(express.getOperate())){

            }else if("ng".equals(express.getOperate())){

            }else if("nq".equals(express.getOperate())){

            }
        }else{
            singleResult = decode(express.getExpressList(),express.getStart(),express.getLen(),bytes);
        }
        return result;
    }

    private String singleResult;

    private String decode(List<String> list,Integer start,Integer len,byte[] bytes){
        Stack<String> stack = new Stack<String>();
        for (String l : list){
            if(StringUtils.isNumeric(l) || "len".equals(l) || "value".equals(l)){
                stack.push(l);
            }else if(bs1.contains(l)){
                if(stack.size() > 1){
                    int result = cal(stack.pop(),stack.pop(),l);
                    stack.push(""+result);
                }
            }else if(bs2.contains(l)){
                if (stack.size() > 0){
                    String tmp = stack.pop();
                    if ("len".equals(tmp) || "v10(".equals(l)){
                        Byte b = null;
                        if ("v10(".equals(l)){
                            b = HexUtil.hexStr2ByteSingle(tmp);
                        }
                        stack.push(getByteInt(l,tmp,len,b)+"");
                    }else if("value".equals(tmp)){
                        byte[] bs = getByteValue(start,len,bytes);
                        stack.push(HexUtil.bytes2StrNum(bs));
                    }else if("v16(".equals(l)){
                        byte[] bs = getByteAry(l,tmp,bytes);
                        stack.push(HexUtil.bytes2StrNum(bs));
                    }else if("this[".equals(l)){
                        byte[] bs = getByteAry(l,tmp,bytes);

                        stack.push(HexUtil.bytes2StrNum(bs));
                    }
                }
            }else{
                stack.push(l);
            }
        }
        if (stack.size() == 1){
           return stack.pop();
        }
        return null;
    }


    private Integer getByteInt(String oper,String val,Integer len,Byte b){
        if("this[".equals(oper) && "len".equals(val)){
            return len;
        }else if("v10(".equals(oper)){
            return HexUtil.getUnsignByte(b);
        }
        return null;
    }

    private byte[] getByteValue(Integer start, Integer len, byte[] bytes){
        return HexUtil.getSubBytes(bytes,start,len,false);
    }

    private byte[] getByteAry(String oper,String val,byte[] bytes){
        if("v16(".equals(oper)){
            return HexUtil.hexStr2Byte(val);
        }else if("this[".equals(oper) && StringUtils.isNumeric(val)){
            int result = index + Integer.valueOf(val);
            return new byte[]{bytes[result]};
        }
        return null;
    }


    private List<String> bs1 = Arrays.asList("*", "/", "+","-");
    private List<String> bs2 = Arrays.asList("v10(", "this[", "v16(");

    private Integer cal(String t2,String t1,String oper){
        if ("+".equals(oper)){
            return new BigDecimal(t1.trim()).add(new BigDecimal(t2.trim())).intValue();
        }
        if ("-".equals(oper)){
            return new BigDecimal(t1.trim()).subtract(new BigDecimal(t2.trim())).intValue();
        }
        if ("*".equals(oper)){
            return new BigDecimal(t1.trim()).multiply(new BigDecimal(t2.trim())).intValue();
        }
        if ("/".equals(oper)){
            return new BigDecimal(t1.trim()).divide(new BigDecimal(t2.trim())).intValue();
        }
        return null;
    }

    public static void main(String[] args){
        FrameManager f = new FrameManager();
        f.loadPacket("packet/packet_sleep.xml");

        byte[] bs = new byte[]{0x10,0x02,0x01,0x00,0x05,0x24,0x08,0x41,0x01,0x00,0x41,0x54,0x52,0x4D,0x56,0x34, (byte) 0x91,
                               0x18,0x56,0x4D,0x52,0x54,0x41,0x30,0x30,0x33,0x32,0x31,0x01,0x01,
                               0x01,0x01,0x01,0x01,0x43,0x00,0x57,0x00, (byte) 0xC0,0x27,0x09,0x00,0x2A};

        f.convert(bs);
    }


    private final static String ELEMENT_NAME_FRAME = "Frame";
    private final static String ELEMENT_NAME_PACKET = "Packet";
    private final static String ELEMENT_NAME_FRAME_HEAD = "head";
    private final static String ELEMENT_NAME_CHECK = "check";
    private final static String ELEMENT_NAME_SEGMENT = "segment";
    private final static String ELEMENT_NAME_VALIDATE = "validate";
    private final static String ELEMENT_NAME_EXPRESS = "express";
    private final static String ELEMENT_NAME_ATTR = "attr";
    private final static String ELEMENT_NAME_DATA = "data";



}
