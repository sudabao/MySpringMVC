package com.shi.springmvc.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.shi.springmvc.DBbean.collectiondb;
import com.shi.springmvc.DBbean.contentdb;
import com.shi.springmvc.DBbean.owuser;
import com.shi.springmvc.DBbean.owuserPROD;
import com.shi.springmvc.DBbean.userdb;

public class beanDao{
    //Configuration config = new Configuration().configure();
    //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    //SessionFactory factory = config.buildSessionFactory(serviceRegistry);
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = null;
	
	public void createUser(userdb user){
		//开启session  
		session = sessionFactory.openSession();  
        //开启事务  
        session.beginTransaction(); 
        
        session.save(user);
        
        //提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
	}
	
	public void updateUser(userdb user){
		//开启session  
		session = sessionFactory.openSession();  
		//开启事务  
		session.beginTransaction(); 
		session.update(user);
		//提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
	}
	
	public List<userdb> selectUserbyOpenId(String openId){
		//开启session  
		session = sessionFactory.openSession();  
		//开启事务  
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<userdb> userdbList = session.createQuery("from userdb where openid = ?").setParameter(0, openId).list();
		return userdbList;
	}
	
	public List<userdb> selectUserbyUserId(String userid){
		//开启session  
		session = sessionFactory.openSession();  
		//开启事务  
		session.beginTransaction();
				
		@SuppressWarnings("unchecked")
		List<userdb> userdbList = session.createQuery("from userdb where userid = ?").setParameter(0, userid).list();
		return userdbList;
	}
	
	public void createcollection(collectiondb collection){
		//开启session  
		session = sessionFactory.openSession();  
        //开启事务  
        session.beginTransaction(); 
        
        session.save(collection);
        
        //提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
	}
	
	public Integer createContent(contentdb content){
		//开启session  
		session = sessionFactory.openSession();  
        //开启事务  
        session.beginTransaction(); 
        
        session.save(content);
        
        //提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }
        
        return content.getContentid();
	}
	
	public List<Object[]> indexAllContents(String token,int pageNo,int pageSize){
		// 开启session
		session = sessionFactory.openSession();
		// 开启事务
		session.beginTransaction();
		
		String content = "";
		if(token!=null || !"null".equals(token)){
			content = "b.userid = '"+token+"'";
		}
		
		String sql = "select a.contentTitle,a.contentMain,a.contentTagsid,a.contentTagsContent,b.collectStatus"
				+ ",c.username,c.userAvatar,c.userid,a.contentMakeDate,a.contentid "
				+ " from contentDB a "
				+ " left join collectiondb b on b.contentid = a.contentid and "+content
				+ " left join userdb c on c.userid = a.contentUserId"
				+ " limit "+pageNo*pageSize+","+(pageNo+1)*pageSize;
		SQLQuery query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		
		if (session.isOpen()) {
			// 关闭session
			session.close();
		}
		
		return result;

		
	}
	
	public List<Object[]> selectCreateContentToOther(String token,String UserId){
		// 开启session
		session = sessionFactory.openSession();
		// 开启事务
		session.beginTransaction();
		
		String content = "";
		if(token!=null || !"null".equals(token)){
			content = "b.userid = '"+token+"'";
		}
		
		String sql = "select a.contentTitle,a.contentMain,a.contentTagsid,a.contentTagsContent,b.collectStatus"
				+ ",c.username,c.userAvatar,c.userid,a.contentMakeDate,a.contentid "
				+ " from contentDB a "
				+ " left join collectiondb b on b.contentid = a.contentid and "+content
				+ " left join userdb c on c.userid = a.contentUserId"
				+ " where a.contentUserId = '"+UserId+"'";
		SQLQuery query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		
		if (session.isOpen()) {
			// 关闭session
			session.close();
		}
		
		return result;

		
	}
	
	public List<Object[]> selectCreateContentToMine(String token){
		// 开启session
		session = sessionFactory.openSession();
		// 开启事务
		session.beginTransaction();
		
		String content = "";
		if(token!=null || !"null".equals(token)){
			content = "b.userid = '"+token+"'";
		}
		
		String sql = "select a.contentTitle,a.contentMain,a.contentTagsid,a.contentTagsContent,b.collectStatus"
				+ ",c.username,c.userAvatar,c.userid,a.contentMakeDate,a.contentid "
				+ " from contentDB a "
				+ " left join collectiondb b on b.contentid = a.contentid and "+content
				+ " left join userdb c on c.userid = a.contentUserId"
				+ " where a.contentUserId = '"+token+"'";
		SQLQuery query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		
		if (session.isOpen()) {
			// 关闭session
			session.close();
		}
		
		return result;

		
	}
	
	public List<Object[]> selectCollentContent(String UserId){
		// 开启session
		session = sessionFactory.openSession();
		// 开启事务
		session.beginTransaction();
		
		String content = "";
		if(UserId!=null || !"null".equals(UserId)){
			content = "b.userid = '"+UserId+"'";
		}
		
		String sql = "select a.contentTitle,a.contentMain,a.contentTagsid,a.contentTagsContent,b.collectStatus"
				+ ",c.username,c.userAvatar,c.userid,a.contentMakeDate,a.contentid "
				+ " from contentDB a "
				+ " inner join collectiondb b on b.contentid = a.contentid and "+content
				+ " left join userdb c on c.userid = a.contentUserId";
		SQLQuery query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		
		if (session.isOpen()) {
			// 关闭session
			session.close();
		}
		
		return result;
	}
	
	public List<Object[]> selectCollentContentByContentId(String token,String ContentId){
		// 开启session
		session = sessionFactory.openSession();
		// 开启事务
		session.beginTransaction();
		
		
		String sql = "select a.contentTitle,a.contentMain,a.contentTagsid,a.contentTagsContent,b.collectStatus"
				+ ",c.username,c.userAvatar,c.userid,a.contentMakeDate,a.contentid "
				+ " from contentDB a "
				+ " left join collectiondb b on b.contentid = a.contentid and b.userid = '"+token+"'"
				+ " left join userdb c on c.userid = a.contentUserId"
				+ " where a.contentid = '"+ContentId+"'";
		SQLQuery query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		
		if (session.isOpen()) {
			// 关闭session
			session.close();
		}
		
		return result;
	}
	
	public void collectContent(collectiondb collect){
		//开启session  
		session = sessionFactory.openSession();  
        //开启事务  
        session.beginTransaction(); 
        
        session.save(collect);
        
        //提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
	}
	
	public void createOWUserTEST(owuser owuser){
		//开启session  
		session = sessionFactory.openSession();  
        //开启事务  
        session.beginTransaction(); 
        
        session.save(owuser);
        
        //提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
	}
	
	public void createOWUserPROD(owuserPROD owuserPROD){
		//开启session  
		session = sessionFactory.openSession();  
        //开启事务  
        session.beginTransaction(); 
        
        session.save(owuserPROD);
        
        //提交事务  
        session.getTransaction().commit();
        
        if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
	}
	
	public List<owuser> selectowuserTEST(){
		//开启session  
		session = sessionFactory.openSession();  
		//开启事务  
		session.beginTransaction();
		Query query = session.createQuery("from owuser");
		query.setMaxResults(200);
		List<owuser> owuserList = query.list();
		if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
		return owuserList;
	}
	
	public List<owuserPROD> selectowuserPROD(){
		//开启session  
		session = sessionFactory.openSession();  
		//开启事务  
		session.beginTransaction();
		Query query = session.createQuery("from owuserPROD");
		query.setMaxResults(200);
		List<owuserPROD> owuserList = query.list();
		if(session.isOpen()){  
            //关闭session  
            session.close();  
        }  
		return owuserList;
	}
	
	public static void main(String args[]){
		//collectiondb collection = new collectiondb();
		/*beanDao testbeanDao = new beanDao();
		List<Object[]> test =testbeanDao.selectAllContent("15f295ff-ef80-4a43-986f-11cf412937fd");
		
		for(Object[] obj :test)    
        {    
            System.out.println(obj[0]+" -- "+ obj[1]+" -- "+obj[2]);    
        }  
		System.out.println(test);*/
		
		/*UUID uuid = UUID.randomUUID();
		collection.setUserid(uuid.toString());
		collection.setContentid(11111);
		collection.setCollectMakeDate(new Date());
		collection.setCollectStatus("1");*/
		/*UUID uuid = UUID.randomUUID();
		user.setUserid("4afe7812-4780-4741-947c-152d61b61ec1");
		user.setOpenid(uuid.toString());
		user.setUsername("QQQQQ");
		user.setUserAvatar("QQQQQQ");*/
		/*contentdb content = new contentdb();
		content.setContentMain("ContentMainTest");
		content.setContentTitle("TTTEST");
		content.setContentMakeDate(new Date());
		content.setContentUserId(uuid.toString());
		
		beanDao testbeanDao = new beanDao();
		testbeanDao.createContent(content);*/
		//testbeanDao.createcollection(collection);
		//List<userdb> list =testbeanDao.selectUserbyOpenId("5f40eea1-97f0-4153-98c7-9c3b15db53e9");
		
		//System.out.println(list.get(0).getSessionKey());
	}
}