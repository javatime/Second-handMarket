package com.secondhandmarket.dao.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.secondhandmarket.dao.interf.BuyerRecordDao;
import com.secondhandmarket.domain.BuyerRecord;
import com.secondhandmarket.tools.Args;
import com.secondhandmarket.tools.HQLSelectTools;

public class BuyerRecordDaoImpl extends BasicOperationImpl implements BuyerRecordDao {

	@Override
	public BuyerRecord findByBid(int bid) {
        String hql = "from buyerRecord b where b.bid=?";
        List buyerRecords = this.getHibernateTemplate().find( hql, new Object[]{new Integer(bid)} );
		
        if(buyerRecords!=null&&buyerRecords.size()==0)
   			 return ((BuyerRecord)(buyerRecords.get(0)));         
        else 
        	 return null;
	}

	@Override
	public boolean create(Object obj) {
	    this.getHibernateTemplate().save( ((BuyerRecord)obj) );
		return true;
	}

	@Override
	public boolean updateObj(final Object obj) {
		//调用 Hibernate 模版更新对象
	    //this.getHibernateTemplate().update(nt);
		this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) throws HibernateException,
					SQLException {

						session.setFlushMode(FlushMode.AUTO); 

						session.update(((BuyerRecord)obj)); 

						session.flush(); 

						return null; 
					}
		});
		return true;
	}

	@Override
	public boolean deleteObj(final Object obj) {
		//调用 Hibernate 模版删除对象
	    //this.getHibernateTemplate().delete(nt);
		this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						session.setFlushMode(FlushMode.AUTO);
						session.beginTransaction();

						Query query = session.createSQLQuery("DELETE FROM buyerRecord WHERE bid=?")
								.setInteger(0, ((BuyerRecord)obj).getBid() );
						query.executeUpdate();
						
						session.getTransaction().commit();
						return null; 
					}
				});		
		return true;
	}

}