package com.shi.springmvc.DBbean;

import java.io.Serializable;
import java.util.Date;

public class collectiondb implements Serializable {

	private static final long serialVersionUID = -2132876417323439533L;  
	private String userid;
	private int contentid;
	private Date collectMakeDate;
	private String collectStatus;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public Date getCollectMakeDate() {
		return collectMakeDate;
	}
	public void setCollectMakeDate(Date collectMakeDate) {
		this.collectMakeDate = collectMakeDate;
	}
	public String getCollectStatus() {
		return collectStatus;
	}
	public void setCollectStatus(String collectStatus) {
		this.collectStatus = collectStatus;
	}
	// ע����������� ������Ҫ��дequals��hashCode  
    public boolean equals(Object object) {  
       return true;  
    }  
   
    public int hashCode() {  
       return 1;  
    }  
	
}
