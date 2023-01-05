package com.shi.springmvc.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import com.shi.springmvc.DBbean.collectiondb;
import com.shi.springmvc.DBbean.contentdb;
import com.shi.springmvc.DBbean.userdb;
import com.shi.springmvc.bean.Response;
import com.shi.springmvc.bean.contentList;
import com.shi.springmvc.bean.tag;
import com.shi.springmvc.bean.userInfo;
import com.shi.springmvc.dao.beanDao;
import com.shi.springmvc.tools.AesCbcUtil;

import net.sf.json.JSONObject;

public class tuiwen{
	
	private String wxappid = "wx935ebfc05b988cf8";
	private String wxSecret = "3c2b4abe57ae3f0e6ddd8008b571a3ab";
	private String grant_type = "authorization_code";
	private static Logger logger = Logger.getLogger(tuiwen.class);
	
	public Response createUser(String nickName,String avatar,String token){
		
		Response response = new Response();
		beanDao BeanDao = new beanDao();
		List<userdb> userlist = BeanDao.selectUserbyUserId(token);
		userInfo user = new userInfo();
		user.setAvatar(avatar);
		user.setName(nickName);
		if(userlist.size()>0){///�û��Ѵ���
			userdb upuser = userlist.get(0);
			upuser.setUserAvatar(avatar);
			upuser.setUsername(nickName);
			BeanDao.updateUser(upuser);
			//System.out.println("user���ϸ�����ϣ�����user����");
			logger.info("user���ϸ�����ϣ�����user����");
			user.setUserId(upuser.getUserid());
			response.setUserinfo(user);
			response.setCode("0"); 
			response.setMsg("Sucess");
		}else{//�û�������
			response.setCode("1");
			response.setMsg("token is not exists");
		}
		return response;
	}
	
	//���崦��WX��½
	public Response loginWx(String code){
		
		Response response = new Response();
		
		if (code == null || code.length() == 0) {
			
			response.setCode("1");
			response.setMsg("code is empty");
            System.out.println("code is empty");
            return response;
        }
		
		//String url = "https://api.weixin.qq.com/sns/jscode2session?appid = "+wxappid
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+wxappid
				+ "&secret="+wxSecret
				+ "&js_code="+code
				+ "&grant_type=authorization_code";
		//String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+wxappid
		//		+"&secret="+wxSecret
		//		+"&js_code="+code
		//		+"&grant_type="+grant_type;
		//System.out.println("url:"+url);
		logger.info("url:"+url);
		
		String result = getLogin(url);
		JSONObject json = JSONObject.fromObject(result);
		//System.out.println("������openid��session_key" + json);
		logger.info("������openid��session_key" + json);
		Object errcode = json.get("errcode");
		logger.info("errcode:" + errcode);
		if(null != errcode && !errcode.equals("")){
			String errmsg = json.get("errmsg").toString();
			response.setCode("1");
			response.setMsg("����weixin����ʧ��:"+errcode+","+errmsg);
            //System.out.println("����weixin����ʧ��:"+errcode);
            logger.info("����weixin����ʧ��:"+errmsg);
            return response;
		}else{
			String session_key = (String) json.get("session_key");
			String openid = (String) json.get("openid");
			//System.out.println("��½�ɹ���");
			//System.out.println("������ܳɹ�����");
			logger.info("��½�ɹ���");
			logger.info("������ܳɹ�����");
			response.setCode("0");
			response.setSubCode("0");
			response.setMsg("success");
			//����DB��Ϣ
			beanDao BeanDao = new beanDao();
			List<userdb> userlist = BeanDao.selectUserbyOpenId(openid);
			
			//����֮���ٲ�ѯ
			userlist = BeanDao.selectUserbyOpenId(openid);
			userInfo returnUser = new userInfo();
			if(userlist.size()>0){
				logger.info("�ҵ��û����ϣ�");
				userdb user = userlist.get(0);
				returnUser.setAvatar(user.getUserAvatar());
				returnUser.setName(user.getUsername());
				returnUser.setUserId(user.getUserid());
				response.setUserinfo(returnUser);
			}
			else{
				UUID uuid = UUID.randomUUID();
	        	userdb newuser = new userdb();
	        	newuser.setOpenid(openid);
	        	newuser.setSessionKey(session_key);
	        	newuser.setUserid(uuid.toString());
	        	BeanDao.createUser(newuser);
	        	//System.out.println("user���ϲ�����ϣ�����user����");	
	        	logger.info("user���ϲ�����ϣ�����user����");
	        	userInfo user = new userInfo();
	        	user.setUserId(uuid.toString());
	        	response.setUserinfo(user);
			}
        	
		}
		return response;
	}
	
	/**
     * ͨ����ҳ��Ȩ��ȡ�û���Ϣ
     *
     * @param accessToken ��ҳ��Ȩ�ӿڵ���ƾ֤
     * @param openId �û���ʶ
     * @return SNSUserInfo 
     * �����ò�����ǰ�˽��
     */
    public static userInfo getUserInfo(String accessToken, String openId,Response response) {
    	userInfo userInfo = null;
        // ƴ�������ַ s
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // ͨ����ҳ��Ȩ��ȡ�û���Ϣ
        String result = get(requestUrl);
		JSONObject json = JSONObject.fromObject(result);
		logger.info("getUserInfo:"+json);
        if (null != json) {
            try {
            	userInfo = new userInfo();
                // �ǳ�
            	userInfo.setName(json.getString("nickname"));
            	userInfo.setSex(json.get("sex").toString());
                
            } catch (Exception e) {
            	userInfo = null;
                String errorCode = json.get("errcode").toString();
                String errorMsg = json.getString("errmsg");
                response.setCode("1");
                response.setMsg("errorMsg:"+errorCode+","+errorMsg);
                logger.error("��ȡ�û���Ϣʧ�� errcode:{} errmsg:{}"+errorCode+","+ errorMsg);
            }
        }
        return userInfo;
    }

	
	
	private String getLogin(String url){
		String result = "";
        result = get(url);
        logger.info("result:"+result);
        return result;
	}
	
	//http GET  Request
    public static String get(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        StringBuffer sb = new StringBuffer();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);          

            HttpEntity entity = response.getEntity();
            InputStreamReader reader = new InputStreamReader(entity.getContent(),"utf-8");
            char [] charbufer;
            while (0<reader.read(charbufer=new char[10])){
                sb.append(charbufer);
            }
        }catch (IOException e){//1
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }
        return sb.toString();
    }

	
	public Response createContent(contentdb content){
		logger.info("createContent Start");
		beanDao BeanDao = new beanDao();
		int contentId = BeanDao.createContent(content);
		Response response = new Response();
		response.setCode("0");
		response.setStatus("0");//�����ɹ�
		response.setContentid(contentId);
		logger.info("createContent End");
		return response;
	}
	
	public List<contentList> indexAllContents(String token,int pageNo,int pageSize){
		logger.info("indexAllContents Start");
		beanDao BeanDao = new beanDao();
		List<Object[]> allContents= BeanDao.indexAllContents(token,pageNo,pageSize);
		List<contentList> contentList = new ArrayList<contentList>();
		contentList = DecodeContent(allContents);
		logger.info("indexAllContents End");
		return contentList;
	}
	
	public Response userCreateList(String token,String userId){
		logger.info("userCreateList Start");
		Response response = new Response();
		response.setCode("0");
		response.setMsg("success");
		beanDao BeanDao = new beanDao();
		List<Object[]> allContents = new ArrayList<Object[]>();;
		//�����Ǳ��˵�ҳ��
		if(null!=userId&&!"".equals(userId)){
			logger.info("�����Ǳ��˵�ҳ��");
			allContents = BeanDao.selectCreateContentToOther(token,userId);
		}else{
		//�������Լ���ҳ��
			logger.info("�������Լ���ҳ��");
			allContents = BeanDao.selectCreateContentToMine(token);
		}
		List<contentList> contentList = new ArrayList<contentList>();
		contentList = DecodeContent(allContents);
		response.setContents(contentList);
		logger.info("��ȡ���Ľ���");
		userInfo user = getUser(token);
		response.setUserinfo(user);
		logger.info("userCreateList End");
		return response;
	}
	
	public Response userCollectionList(String token,String userId){
		logger.info("userCollectionList Start");
		beanDao BeanDao = new beanDao();
		List<Object[]> allContents = new ArrayList<Object[]>();
		//�����Ǳ��˵�ҳ��
		if(null!=userId&&!"".equals(userId)){
			logger.info("�����Ǳ����ղص�ҳ��");
			allContents = BeanDao.selectCollentContent(userId);
		}else{
		//�������Լ���ҳ��
			logger.info("�������Լ��ղص�ҳ��");
			allContents = BeanDao.selectCollentContent(token);
		}
		List<contentList> contentList = new ArrayList<contentList>();
		contentList = DecodeContent(allContents);
		logger.info("��ȡ���Ľ���");
		Response response = new Response();
		response.setCode("0");
		response.setMsg("success");
		userInfo user = getUser(token);
		response.setUserinfo(user);
		response.setContents(contentList);
		logger.info("userCollectionList End");
		return response;
	}
	
	public Response collectContent(String token,String contentId,String action){
		logger.info("collectContent Start");
		beanDao BeanDao = new beanDao();
		collectiondb coldb = new collectiondb();
		coldb.setUserid(token);
		coldb.setContentid(Integer.valueOf(contentId));
		coldb.setCollectStatus(action);
		BeanDao.collectContent(coldb);
		Response response = new Response();
		response.setCode("0");
		response.setMsg("success");
		response.setStatus(action);
		logger.info("collectContent End");
		return response;
	}
	
	//��ַ��ص�����
	public List<contentList> DecodeContent(List<Object[]> allContents){
		List<contentList> contentList = new ArrayList<contentList>();
		for(Object obj[]:allContents){
			contentList content = new contentList();
			userInfo user = new userInfo();
			List<tag> taglist = new ArrayList<tag>();
			
			if(obj[0]!=null && !"null".equals(obj[0])){
				content.setTitle(obj[0].toString());
			}
			if(obj[1]!=null && !"null".equals(obj[1])){
				content.setMainContent(obj[1].toString());
			}
			if(obj[4]!=null && !"null".equals(obj[4])){
				content.setIsCollected(obj[4].toString());
			}else{
				content.setIsCollected("0");//û�����ݾ���û�ղ�
			}
			if(obj[8]!=null && !"null".equals(obj[8])){
				content.setCreateDate(obj[8].toString());
			}
			if(obj[9]!=null && !"null".equals(obj[9])){
				content.setContentId(obj[9].toString());
			}
			
			//author
			if(obj[5]!=null && !"null".equals(obj[5])){
				user.setName(obj[5].toString());
			}
			if(obj[6]!=null && !"null".equals(obj[6])){
				user.setAvatar(obj[6].toString());
			}
			if(obj[7]!=null && !"null".equals(obj[7])){
				user.setUserId(obj[7].toString());
			}
			
			content.setAuthor(user);
			
			//TagsId
			if(obj[2]!=null && !"null".equals(obj[2])){
				
			}
			
			//TagsContent
			if (obj[3] != null && !"null".equals(obj[3])) {
				String Contents = obj[3].toString();
				String tags[] = Contents.split("_");
				for(String tagContent:tags){
					tag t = new tag();
					t.setTagText(tagContent);
					taglist.add(t);
				}
			}
			content.setTags(taglist);
			contentList.add(content);
		}
		return contentList;
	}
	
	public userInfo getUser(String token){
		beanDao BeanDao = new beanDao();
		userInfo user = new userInfo();
		List<userdb> userdblist = BeanDao.selectUserbyUserId(token);
		if(null!=userdblist&&userdblist.size()>0){
			userdb userSchema = userdblist.get(0);
			user.setAvatar(userSchema.getUserAvatar());
			user.setName(userSchema.getUsername());
		}
		return user;
	}
	
	public Response getContentDetail(String token, String contentId) {
		logger.info("getContentDetail Start");
		beanDao BeanDao = new beanDao();
		List<Object[]> allContents = new ArrayList<Object[]>();
		allContents = BeanDao.selectCollentContentByContentId(token,contentId);
		List<contentList> contentList = new ArrayList<contentList>();
		contentList = DecodeContent(allContents);
		logger.info("��ȡ���Ľ���");
		Response response = new Response();
		response.setCode("0");
		response.setMsg("success");
		userInfo user = getUser(token);
		response.setUserinfo(user);
		response.setContents(contentList);
		logger.info("getContentDetail End");
		return response;
	}
	
	public static void main(String[] args){
		
		tuiwen t = new tuiwen();
		//t.loginWx("033vCYFV00y3GZ1F5fGV0lkWFV0vCYFa");
		/*t.indexAllContents("15f295ff-ef80-4a43-986f-11cf412937fd");*/
		/*contentdb contentDB = new contentdb();
		contentDB.setContentMain("����ҩ�� ���� ����ɻ𲻳ɻ�");
		contentDB.setContentUserId("cf0ca80b-af49-47e2-9714-f0d18405970b");
		contentDB.setContentTitle("����ҩ��");
		contentDB.setContentMakeDate(new Date());
		Response r = t.createContent(contentDB);
		System.out.println(r.getContentid());*/
		Response r  = t.getContentDetail("cf0ca80b-af49-47e2-9714-f0d18405970b", "24");
		System.out.println(r.getContents().get(0).getContentId());
		JSONObject json = JSONObject.fromObject(r);
		System.out.println(json);
	}

	
}