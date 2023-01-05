package com.shi.springmvc.bean;

import java.util.List;

public class Response {
	public String code;
	public String subCode;
	public String msg;
	public userInfo userinfo;
	public String status;
	public int contentid;//创建后返回ID
	public List<contentList> contents;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public userInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(userInfo userinfo) {
		this.userinfo = userinfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<contentList> getContents() {
		return contents;
	}
	public void setContents(List<contentList> contents) {
		this.contents = contents;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	
	
}
