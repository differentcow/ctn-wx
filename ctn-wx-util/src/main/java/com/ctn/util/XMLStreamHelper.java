package com.ctn.util;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang.StringUtils;

import com.ctn.entity.Message;
import com.ctn.entity.MessageArticle;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;

/**
 * XML解析与编写工具
 * 
 * @author Barry.LJ.Huang
 *
 */
public class XMLStreamHelper {

//	public static Message turnMessage(InputStream in){
//		XMLInputFactory factory = XMLInputFactory.newInstance();
//		factory.setProperty("javax.xml.stream.isCoalescing", true);
//		
//		try {  
//            XMLEventReader reader = factory.createXMLEventReader(in);
//            
//            Message msg = new Message();
//            
//            while (reader.hasNext()) {
//            	XMLEvent event = reader.nextEvent();
//            	
//            	if (event.isStartElement()) {  
//            		StartElement startElement = event.asStartElement();
//            		/**
//            		Field[] fields = msg.getClass().getDeclaredFields();
//            		label: for (Field field : fields) {
//            			
//						String fieldName = getMethodName(field.getName());
//						if  (startElement.getName().getLocalPart().equals(fieldName)) {
//	                		event = reader.nextEvent();
//	                		if ("class java.util.Date".equals(field.getGenericType().toString())) {
//	                			long timeLong = Long.parseLong(event.asCharacters().getData());
//	        					Date date = new Date(timeLong);
//	                			Method m = msg.getClass().getDeclaredMethod("set"+fieldName, Date.class);
//	                			m.setAccessible(true); *//** 因为写成private 所以这里必须设置 *//**
//	                			m.invoke(msg, date);
//							}else{
//								Method m = msg.getClass().getDeclaredMethod("set"+fieldName, field.getType());
//	                			m.setAccessible(true); *//** 因为写成private 所以这里必须设置 *//**
//	                			if ("class java.lang.String".equals(field.getGenericType().toString())) {
//	                				m.invoke(msg, event.asCharacters().getData());
//								}else if ("class java.lang.Integer".equals(field.getGenericType().toString())) {
//	                				m.invoke(msg, Integer.valueOf(event.asCharacters().getData()));
//								}else 	if ("class java.lang.Double".equals(field.getGenericType().toString())) {
//	                				m.invoke(msg, Double.valueOf(event.asCharacters().getData()));
//								}else if ("class java.lang.Long".equals(field.getGenericType().toString())) {
//	                				m.invoke(msg, Long.valueOf(event.asCharacters().getData()));
//								}
//	                			*//**
//	        					 * TODO others type
//	        					 *//**
//							}
//	                		break label;
//	    				}
//					}*/
//            		if  (startElement.getName().getLocalPart().equals("ToUserName")) {
//                		event = reader.nextEvent();  
//                		msg.setToUserName(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("FromUserName")) {
//    					event = reader.nextEvent();  
//                		msg.setFromUserName(event.asCharacters().getData());  
//                		continue;				
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("CreateTime")) {
//    					event = reader.nextEvent();
//    					long timeLong = Long.parseLong(event.asCharacters().getData());
//    					Date date = new Date(timeLong);
//                		msg.setCreateTime(date);  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("MsgType")) {
//    					event = reader.nextEvent();  
//                		msg.setMsgType(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Content")) {
//    					event = reader.nextEvent();  
//                		msg.setContent(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("MsgId")) {
//    					event = reader.nextEvent();  
//                		msg.setMsgId(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("PicUrl")) {
//    					event = reader.nextEvent();  
//                		msg.setPicUrl(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("MediaId")) {
//    					event = reader.nextEvent();  
//                		msg.setMediaId(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Format")) {
//    					event = reader.nextEvent();  
//                		msg.setFormat(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("ThumbMediaId")) {
//    					event = reader.nextEvent();  
//                		msg.setThumbMediaId(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Location_X")) {
//    					event = reader.nextEvent();  
//                		msg.setLocation_X(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Location_Y")) {
//    					event = reader.nextEvent();  
//                		msg.setLocation_Y(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Scale")) {
//    					event = reader.nextEvent();  
//                		msg.setScale(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Label")) {
//    					event = reader.nextEvent();  
//                		msg.setLabel(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Title")) {
//    					event = reader.nextEvent();  
//                		msg.setTitle(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Description")) {
//    					event = reader.nextEvent();  
//                		msg.setDescription(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Url")) {
//    					event = reader.nextEvent();  
//                		msg.setUrl(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Event")) {
//    					event = reader.nextEvent();  
//                		msg.setEvent(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("EventKey")) {
//    					event = reader.nextEvent();  
//                		msg.setEventKey(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Ticket")) {
//    					event = reader.nextEvent();  
//                		msg.setTicket(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Latitude")) {
//    					event = reader.nextEvent();  
//                		msg.setLatitude(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Longitude")) {
//    					event = reader.nextEvent();  
//                		msg.setLongitude(event.asCharacters().getData());  
//                		continue;
//    				}
//    				if  (event.asStartElement().getName().getLocalPart().equals("Precision")) {
//    					event = reader.nextEvent();  
//                		msg.setPrecision(event.asCharacters().getData());  
//                		continue;
//    				}
//            	}
//			}
//            return msg;
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            return null;
//        }
//	}
//	
	public static void turnXML(Message msg, OutputStream output){
		try{  
				if (msg == null) {
					output.write("".getBytes());
					return;
				}
				XMLOutputFactory outputFactory=XMLOutputFactory.newInstance();
			    XMLStreamWriter xmlWriter = outputFactory.createXMLStreamWriter(output);
			    xmlWriter.writeStartElement("xml");
			    if (!StringUtils.isBlank(msg.getToUserName())) {
			    	xmlWriter.writeStartElement("ToUserName");  
				    xmlWriter.writeCData(msg.getToUserName());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getFromUserName())) {
			    	xmlWriter.writeStartElement("FromUserName");  
				    xmlWriter.writeCData(msg.getFromUserName());  
				    xmlWriter.writeEndElement();
				}
			    if (msg.getCreateTime() != null) {
			    	xmlWriter.writeStartElement("CreateTime");  
				    xmlWriter.writeCharacters(String.valueOf(msg.getCreateTime().getTime()));  
				    xmlWriter.writeEndElement();
				}
			    if (msg.getMsgType()!=null) {
			    	xmlWriter.writeStartElement("MsgType");  
				    xmlWriter.writeCData(msg.getMsgType().toString());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getContent())) {
			    	xmlWriter.writeStartElement("Content");  
				    xmlWriter.writeCData(msg.getContent());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getMsgId())) {
			    	xmlWriter.writeStartElement("MsgId");  
				    xmlWriter.writeCData(msg.getMsgId());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getFormat())) {
			    	xmlWriter.writeStartElement("Format");  
				    xmlWriter.writeCData(msg.getFormat());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getThumbMediaId())) {
			    	xmlWriter.writeStartElement("ThumbMediaId");  
				    xmlWriter.writeCData(msg.getThumbMediaId());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getLocation_X())) {
			    	xmlWriter.writeStartElement("Location_X");  
				    xmlWriter.writeCData(msg.getLocation_X());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getLocation_Y())) {
			    	xmlWriter.writeStartElement("Location_Y");  
				    xmlWriter.writeCData(msg.getLocation_Y());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getScale())) {
			    	xmlWriter.writeStartElement("Scale");  
				    xmlWriter.writeCData(msg.getScale());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getLabel())) {
			    	xmlWriter.writeStartElement("Label");  
				    xmlWriter.writeCData(msg.getLabel());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getTitle())) {
			    	xmlWriter.writeStartElement("Title");  
				    xmlWriter.writeCData(msg.getTitle());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getDescription())) {
			    	xmlWriter.writeStartElement("Description");  
				    xmlWriter.writeCData(msg.getDescription());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getUrl())) {
			    	xmlWriter.writeStartElement("Url");  
				    xmlWriter.writeCData(msg.getUrl());  
				    xmlWriter.writeEndElement();
				}
			    if (msg.getEvent()!=null) {
			    	xmlWriter.writeStartElement("Event");  
				    xmlWriter.writeCData(msg.getEvent().toString());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getEventKey())) {
			    	xmlWriter.writeStartElement("EventKey");  
				    xmlWriter.writeCData(msg.getEventKey());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getTicket())) {
			    	xmlWriter.writeStartElement("Ticket");  
				    xmlWriter.writeCData(msg.getTicket());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getLatitude())) {
			    	xmlWriter.writeStartElement("Latitude");  
				    xmlWriter.writeCData(msg.getLatitude());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getLongitude())) {
			    	xmlWriter.writeStartElement("Longitude");  
				    xmlWriter.writeCData(msg.getLongitude());  
				    xmlWriter.writeEndElement();
				}
			    if (!StringUtils.isBlank(msg.getPrecision())) {
			    	xmlWriter.writeStartElement("Precision");  
				    xmlWriter.writeCData(msg.getPrecision());  
				    xmlWriter.writeEndElement();
				}
			    if (msg.getArticleCount()!=0) {
			    	xmlWriter.writeStartElement("ArticleCount");  
				    xmlWriter.writeCharacters(msg.getArticleCount()+"");  
				    xmlWriter.writeEndElement();
				}
			    if (msg.getArticles() != null) {
			    	xmlWriter.writeStartElement("Articles");  
			    	int count = 0;
				    label : for (MessageArticle art : msg.getArticles().getItem()) {
				    	if (count == 10) {
							break label;
						}
				    	xmlWriter.writeStartElement("item");  
				    	xmlWriter.writeStartElement("Title");  
					    xmlWriter.writeCData(StringUtils.isBlank(art.getTitle())?"":art.getTitle());  
					    xmlWriter.writeEndElement();
					    xmlWriter.writeStartElement("Description");  
					    xmlWriter.writeCData(StringUtils.isBlank(art.getDescription())?"":art.getDescription());  
					    xmlWriter.writeEndElement();
					    xmlWriter.writeStartElement("PicUrl");  
					    xmlWriter.writeCData(StringUtils.isBlank(art.getPicUrl())?"":art.getPicUrl());  
					    xmlWriter.writeEndElement();
					    xmlWriter.writeStartElement("Url");  
					    xmlWriter.writeCData(StringUtils.isBlank(art.getUrl())?"":art.getUrl());  
					    xmlWriter.writeEndElement();
					    xmlWriter.writeEndElement();
					    count++; 
					}  
				    xmlWriter.writeEndElement();
				}
			    /*Field[] fields = msg.getClass().getDeclaredFields();
			    for (Field field : fields) {
					String filedName = getMethodName(field.getName());
					
					*//** String Type *//*
	                if("class java.lang.String".equals(field.getGenericType().toString())){	
						Method m = (Method) msg.getClass().getMethod("get" + filedName);
						String val = (String) m.invoke(msg);
						if (!StringUtils.isBlank(val)) {
							xmlWriter.writeStartElement(filedName);  
						    xmlWriter.writeCData(val);  
						    xmlWriter.writeEndElement(); 
						}
					}
					
	                *//** Date Type *//*
	                if ("class java.util.Date".equals(field.getGenericType().toString())) {  	
	                    Method m = (Method) msg.getClass().getMethod("get" + filedName);  
	                    Date val = (Date) m.invoke(msg);  
	                    if (val != null) { 
	                    	xmlWriter.writeStartElement(filedName);  
						    xmlWriter.writeCharacters(String.valueOf(val.getTime()));  
						    xmlWriter.writeEndElement();
	                    }  
	                }
	                
					*//** Integer type *//*
					if("class java.lang.Integer".equals(field.getGenericType().toString())){
						Method m = (Method) msg.getClass().getMethod("get" + filedName);
						Integer val = (Integer) m.invoke(msg);
						if (val != null) {
							xmlWriter.writeStartElement(filedName);  
						    xmlWriter.writeCharacters(String.valueOf(val));
						    xmlWriter.writeEndElement(); 
						}
					}
					
					*//**
					 * TODO others type
					 *//*
				}*/
			    xmlWriter.writeEndElement(); 
			    xmlWriter.flush(); 
			    xmlWriter.close();  
			} catch (Exception e){
				e.printStackTrace();
			} 
	}
	
	/**
	 * 首字母大写
	 * 
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	/**
	private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }*/
	
	
	/**
	 * 测试编写XML
	 * 
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception{
//		Message msg = new Message();
//		msg.setToUserName("Barry");
//		msg.setFromUserName("Marry");
//		msg.setCreateTime(new Date());
//		msg.setMsgType("video");
//		msg.setTitle("测试");
//		msg.setDescription("测试示例");
//		msg.setUrl("www.qq.com");
//		msg.setMsgId("123456798");
//		List<MessageArticles> list = new ArrayList<MessageArticles>();
//		MessageArticles item1 = new MessageArticles();
//		item1.setDescription("测试");
//		item1.setPicUrl("http://5.wechatmei.sinaapp.com/img/1.jpg");
//		item1.setTitle("测试示例");
//		item1.setUrl("http://www.baidu.com");
//		list.add(item1);
//		MessageArticles item2 = new MessageArticles();
//		item2.setDescription("测试2");
//		item2.setTitle("测试示例2");
//		list.add(item2);
//		msg.setArticles(list);
//		msg.setArticleCount(String.valueOf(list.size()));
//		java.io.OutputStream out = new java.io.FileOutputStream(new java.io.File("D:\\3.xml"));
//		XMLStreamHelper.turnXML(msg, out);
//	}
	
	/**
	 * 测试XML解析
	 * @param args
	 * @throws Exception
	 */
	/**
	public static void main(String[] args) throws Exception {
		
		java.io.File file = new java.io.File("D:\\2.xml");
		InputStream in = new java.io.FileInputStream(file);
		Message msg = XMLStreamHelper.turnMessage(in);
		System.out.println(msg.getToUserName());
		System.out.println(msg.getFromUserName());
		System.out.println(msg.getCreateTime());
		System.out.println(msg.getMsgType());
		System.out.println(msg.getTitle());
		System.out.println(msg.getDescription());
		System.out.println(msg.getUrl());
		System.out.println(msg.getMsgId());
	}
	*/
}
