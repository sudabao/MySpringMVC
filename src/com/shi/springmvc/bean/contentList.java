package com.shi.springmvc.bean;

import java.util.Date;
import java.util.List;

public class contentList{
	private userInfo author;
	private String title;
	private String mainContent;
	private List<tag> tags;
	private String createDate;
	private String isCollected;
	private String contentId;
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public userInfo getAuthor() {
		return author;
	}
	public void setAuthor(userInfo author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMainContent() {
		return mainContent;
	}
	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}
	public List<tag> getTags() {
		return tags;
	}
	public void setTags(List<tag> tags) {
		this.tags = tags;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(String isCollected) {
		this.isCollected = isCollected;
	}


	
	
}