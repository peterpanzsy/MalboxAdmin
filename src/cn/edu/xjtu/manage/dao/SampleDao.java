package cn.edu.xjtu.manage.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cn.edu.xjtu.manage.business.Sample;


public class SampleDao {
	
	public List<Sample> getSampleList() {
		SQLQuery q = session.createSQLQuery("SELECT t.ID,t.name,t.path,t.type,t.filesize,t.md5,t.analysis,t.firsttime,t.lasttime,t.remark FROM t_sample t order by t.lasttime desc");
		List l = q.list();
		List<Sample> re=new ArrayList<Sample>();
		for(int i=0;i<l.size();i++)
		{
			  Object[] row = (Object[])l.get(i);
			  Integer id = (Integer)row[0];
			  String name = (String)row[1];  
			  String path=(String)row[2];
			  String type=(String)row[3];
			  long filesize=((BigInteger)row[4]).longValue();
			  String md5=(String)row[5];
			  String analysis=(String)row[6];
			  Date firsttime=(Date)row[7];
			  Date lasttime=(Date)row[8];
			  String remark=(String)row[9];			  
			  Sample sample=new Sample(id,name,path,type,filesize,md5,analysis,firsttime,lasttime,remark);	 
			  re.add(sample);
		}
		return re;
	}
	
	public int getCountSample(){

		String sql="select count(*) from t_sample t ";
		SQLQuery q = session.createSQLQuery(sql);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;
	}
	
	public int countSample(String md5){
		Query q = session.createSQLQuery("select count(*) from t_sample t where t.md5=?");
		q.setParameter(0, md5);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;
	}
	
	public int addSample(String name, String path, String type, long filesize,
			String md5, String analysis, Date firsttime, Date lasttime){
		
		Query q = session.createSQLQuery("insert into t_sample (name,path,type,filesize,md5,analysis,firsttime,lasttime) values (?,?,?,?,?,?,?,?)");
		q.setParameter(0, name);
		q.setParameter(1, path);
		q.setParameter(2,type);
		q.setParameter(3, filesize);
		q.setParameter(4,md5);
		q.setParameter(5,analysis);
		q.setParameter(6,firsttime);
		q.setParameter(7,lasttime);
		int result=q.executeUpdate();
		tx.commit();
		return result;
	}
	
	public int updateSample(String md5, Date lasttime){
		
		Query q = session.createSQLQuery("update t_sample set lasttime=? where md5=?");
		q.setParameter(0, lasttime);
		q.setParameter(1, md5);
		int result=q.executeUpdate();
		tx.commit();
		return result;
	}
	
	public int deleteSample(int id) {
		SQLQuery q = session.createSQLQuery("delete from t_sample where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
		return re;
		
	}
	
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
	

	public SampleDao()
	{	
		Configuration cfg = new Configuration();  
        cfg.configure();          
        ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
        SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
                  
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
	}
	
	public  void close()
	{
		//tx.commit();
		session.close();
		//sessionFactory.close();
	}
	public void commit(){
		tx.commit();
		session.close();
	}
	
}
