package com.shi.springmvc.DBbean;

import java.util.Date;

public class contentdb {

	private int contentid;
	private String contentMain;
	private String contentTitle;
	private String contentTagsid;
	private String contentTagsContent;
	private String contentUserId;
	private Date contentMakeDate;
	
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public String getContentMain() {
		return contentMain;
	}
	public void setContentMain(String contentMain) {
		this.contentMain = contentMain;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContentTagsid() {
		return contentTagsid;
	}
	public void setContentTagsid(String contentTagsid) {
		this.contentTagsid = contentTagsid;
	}
	public String getContentTagsContent() {
		return contentTagsContent;
	}
	public void setContentTagsContent(String contentTagsContent) {
		this.contentTagsContent = contentTagsContent;
	}
	public String getContentUserId() {
		return contentUserId;
	}
	public void setContentUserId(String contentUserId) {
		this.contentUserId = contentUserId;
	}
	public Date getContentMakeDate() {
		return contentMakeDate;
	}
	public void setContentMakeDate(Date contentMakeDate) {
		this.contentMakeDate = contentMakeDate;
	}
	
	
}
