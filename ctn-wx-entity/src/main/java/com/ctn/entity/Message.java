package com.ctn.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ctn.entity.type.EventType;
import com.ctn.entity.type.MsgType;

/**
 * 微信消息通讯载体
 * 
 * @author Barry.LJ.Huang
 *
 */
@XmlRootElement(name="xml")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238509599842135137L;
	
	private String toUserName;
	
	private String fromUserName;
	private Date createTime;
	private MsgType msgType;
	private String content;
	private String msgId;
	private String picUrl;
	private String mediaId;
	private String format;
	private String thumbMediaId;
	private String location_X;
	private String location_Y;
	private String scale;
	private String label;
	private String title;
	private String description;
	private String url;
	private EventType event;
	private String eventKey;
	private String ticket;
	private String latitude;
	private String longitude;
	private String precision;
	private int ArticleCount;
	private MessageArticles articles;
	@XmlElement(name="ArticleCount")
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	@XmlElement(name="Articles")
	public MessageArticles getArticles() {
		return articles;
	}
	public void setArticles(MessageArticles articles) {
		this.articles = articles;
	}
	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	@XmlElement(name="CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@XmlElement(name="Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@XmlElement(name="MsgId")
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	@XmlElement(name="PicUrl")
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@XmlElement(name="MediaId")
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@XmlElement(name="Format")
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	@XmlElement(name="ThumbMediaId")
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	@XmlElement(name="Location_X")
	public String getLocation_X() {
		return location_X;
	}
	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}
	@XmlElement(name="Location_Y")
	public String getLocation_Y() {
		return location_Y;
	}
	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}
	@XmlElement(name="Scale")
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	@XmlElement(name="Label")
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@XmlElement(name="Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement(name="Url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@XmlElement(name="MsgType")
	public MsgType getMsgType() {
		return msgType;
	}
	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
	@XmlElement(name="Event")
	public EventType getEvent() {
		return event;
	}
	public void setEvent(EventType event) {
		this.event = event;
	}
	@XmlElement(name="EventKey")
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	@XmlElement(name="Ticket")
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@XmlElement(name="Latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@XmlElement(name="Longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@XmlElement(name="Precision")
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	@Override
	public String toString() {
		return "Message [toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", content=" + content + ", msgId=" + msgId
				+ ", picUrl=" + picUrl + ", mediaId=" + mediaId + ", format="
				+ format + ", thumbMediaId=" + thumbMediaId + ", location_X="
				+ location_X + ", location_Y=" + location_Y + ", scale="
				+ scale + ", label=" + label + ", title=" + title
				+ ", description=" + description + ", url=" + url + ", event="
				+ event + ", eventKey=" + eventKey + ", ticket=" + ticket
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", precision=" + precision + ", ArticleCount=" + ArticleCount
				+ ", articles=" + articles + "]";
	}
	
	/**
	public void emptyClear(){
		this.toUserName = null;
		this.fromUserName = null;
		this.createTime = null;
		this.msgType = null;
		this.content = null;
		this.msgId = null;
		this.picUrl = null;
		this.mediaId = null;
		this.format = null;
		this.thumbMediaId = null;
		this.location_X = null;
		this.location_Y = null;
		this.scale = null;
		this.label = null;
		this.title = null;
		this.description = null;
		this.url = null;
		this.event = null;
		this.eventKey = null;
		this.ticket = null;
		this.latitude = null;
		this.longitude = null;
		this.precision = null;
	}*/
}
