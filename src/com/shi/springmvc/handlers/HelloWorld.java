package com.shi.springmvc.handlers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shi.springmvc.DBbean.contentdb;
import com.shi.springmvc.bean.Response;
import com.shi.springmvc.bean.contentList;
import com.shi.springmvc.bean.userInfo;
import com.shi.springmvc.domain.tuiwen;

import net.sf.json.JSONObject;

@Controller
public class HelloWorld {
 
    /**
     * 1. 使用RequestMapping注解来映射请求的URL 2. 返回值会通过视图解析器解析为实际的物理视图,
     * 对于InternalResourceViewResolver视图解析器，会做如下解析 通过prefix+returnVal+suffix
     * 这样的方式得到实际的物理视图，然后会转发操作 "/WEB-INF/views/success.jsp"
     * 
     * @return
     */
	private static Logger logger = Logger.getLogger(HelloWorld.class);
	
	@RequestMapping("/index")    
    public String index() {
    	//System.out.println("hello index");
    	logger.info("hello index");
        return "index";
    }
    
    @RequestMapping("/helloworld")    
    public String hello() {
    	//System.out.println("hello world");
    	logger.info("hello world");
        return "success";
    }
    
    @RequestMapping(value="/berich",produces="text/html;charset=UTF-8") 
    @ResponseBody
    public String berich() {
    	//System.out.println("BeRich");
    	logger.info("BeRich");
    	contentList content = new contentList();
    	content.setMainContent("我有药啊 晋江 衣落成火不成灰");
    	content.setTitle("我有药啊");
		Response r = new Response();
		ArrayList<contentList> ac= new ArrayList();
		ac.add(content);
		r.setContents(ac);
    	JSONObject json = JSONObject.fromObject(r);
        return json.toString();
    }
    
    @RequestMapping(value="/getting",produces="text/html;charset=UTF-8") 
    @ResponseBody
    public String getting(HttpServletRequest request) {
    	String name = request.getParameter("title");
    	//System.out.println("getting");
    	//System.out.println(name);
    	logger.info("getting");
    	logger.info("name");
        return name;
    }
    
    @RequestMapping(value="/tuiwen",produces="text/html;charset=UTF-8") 
    @ResponseBody
    public String tuiwen(HttpServletRequest request) {
    	String functionId = request.getParameter("functionId");
    	//System.out.println("functionId:"+functionId);
    	logger.info("functionId:"+functionId);
    	tuiwen T1 = new tuiwen();
    	Response response = new Response();
    	
    	String body = request.getParameter("body");
		logger.info("body:"+body);
		JSONObject jsonData = JSONObject.fromObject(body);
		logger.info("jsonData:"+jsonData);
    	
    	if(functionId.equals("loginWx")){
    		String code = jsonData.get("code").toString();
    		logger.info("code:"+code);
    		response = T1.loginWx(code);
    		/*response.setCode("test");
    		userInfo user = new userInfo();
    		user.setUserId("test userId");
    		user.setAvatar("test Avatar");
    		user.setName("test Name");
    		response.setUserinfo(user);*/
    	}
    	else if(functionId.equals("createUser")){
    		String nickName = jsonData.get("nickName").toString();
    		String avatar = jsonData.get("avatar").toString();
    		String token = jsonData.get("userid").toString();
    		response = T1.createUser(nickName, avatar, token);
    	}
    	else if(functionId.equals("indexAllContents")){
    		String token = jsonData.get("token").toString();
    		String pageNo = jsonData.get("pageNo").toString();//第几页，从0开始
    		int pageNoInt =0;
    		if(pageNo!=null){
    			pageNoInt = Integer.parseInt(pageNo);
    		}
    		String pageSize = jsonData.get("pageSize").toString();//每页个数
    		int pageSizeInt =0;
    		if(pageSize!=null){
    			pageSizeInt = Integer.parseInt(pageSize);
    		}
    		List<contentList> contentList = T1.indexAllContents(token,pageNoInt,pageSizeInt);
    		response.setCode("0");
    		response.setMsg("Sucess");
    		response.setContents(contentList);
    	}
    	else if(functionId.equals("createContent")){
    		String token = jsonData.get("token").toString();
    		String content = jsonData.get("content").toString();
    		String tagscontent = jsonData.get("tagscontent").toString();
    		String title = jsonData.get("title").toString();
    		contentdb contentDB = new contentdb();
    		contentDB.setContentMain(content);
    		contentDB.setContentUserId(token);
    		contentDB.setContentTagsContent(tagscontent);
    		contentDB.setContentTitle(title);
    		contentDB.setContentMakeDate(new Date());
    		response = T1.createContent(contentDB);
    		
    	}
    	else if(functionId.equals("userCreateList")){
    		String token = jsonData.get("token").toString();
    		String userId = jsonData.get("userId").toString();
    		response = T1.userCreateList(token, userId);
    	}
    	else if(functionId.equals("userCollectionList")){
    		String token = jsonData.get("token").toString();
    		String userId = jsonData.get("userId").toString();
    		response = T1.userCollectionList(token, userId);
    	}
    	else if(functionId.equals("collectContent")){
    		String token = jsonData.get("token").toString();
    		String contentId = jsonData.get("contentId").toString();
    		String action = jsonData.get("action").toString();
    		response = T1.collectContent(token, contentId, action);
    	}
    	else if(functionId.equals("getContentDetail")){
    		String token = jsonData.get("token").toString();
    		String contentId = jsonData.get("contentId").toString();
    		response = T1.getContentDetail(token,contentId);
    	}
    	else{//找不到对应ID
    		response.setCode("1");
    		response.setSubCode("1");
    		response.setMsg("not Function");
    	}
    	logger.info("functionId:"+functionId +" response");
    	logger.info("response.getCode"+response.getCode());
    	logger.info("response.getMsg"+response.getMsg());
    	JSONObject json = JSONObject.fromObject(response);
    	logger.info("response json:"+json);
        return json.toString();
    }
    
    public Response returnError(String input){
    	Response rs = new Response();
    	rs.setCode("1");
    	rs.setMsg(input+" value is empty");
    	return rs;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}