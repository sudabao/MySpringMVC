package com.shi.springmvc.domain;


import java.util.List;

import com.shi.springmvc.DBbean.owuser;
import com.shi.springmvc.DBbean.owuserPROD;
import com.shi.springmvc.dao.beanDao;

public class owfuneral{
	public boolean byebye(String idName,String mainHero,String finalWord,String time,String isTest){
		
		beanDao save = new beanDao();
		if(isTest.equalsIgnoreCase("prod")){
			owuserPROD u = new owuserPROD();
			u.setIdname(idName);
			u.setMainhero(mainHero);
			u.setFinalword(finalWord);
			u.setTime(time);
			save.createOWUserPROD(u);
		}else{
			owuser u = new owuser();
			u.setIdname(idName);
			u.setMainhero(mainHero);
			u.setFinalword(finalWord);
			u.setTime(time);
			save.createOWUserTEST(u);
		}
		
		return true;
	}
	
	public List<owuser> home(String isTest){
		beanDao select = new beanDao();
		List<owuser> owuserList = select.selectowuserTEST();
		return owuserList;
		
	}
	
	public List<owuserPROD> homePROD(String isTest){
		beanDao select = new beanDao();
		List<owuserPROD> owuserList = select.selectowuserPROD();
		return owuserList;
	}
	
	public static void main(String arg[]){
		owfuneral ow = new owfuneral();
		ow.byebye("Test#12345", "TEST", "ты╪Ш", "2022-01-06","prod");
		}
	
	
}