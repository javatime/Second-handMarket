 package com.secondhandmarket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.secondhandmarket.dao.interf.CollectionDao;
import com.secondhandmarket.domain.Collection;

public class CollectionDaoImpl extends BasicOperationImpl implements CollectionDao{

	@Override
	public boolean create(Object obj) {
	    this.getHibernateTemplate().save( (obj) );
		return true;
	}

	@Override
	public boolean updateObj(final Object obj) {
		//调用 Hibernate 模版更新对象
	    //this.getHibernateTemplate().update(nt);
		this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session) throws HibernateException,
					SQLException {

						session.setFlushMode(FlushMode.AUTO); 

						session.update((obj)); 

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
					@Override
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						session.setFlushMode(FlushMode.AUTO);
						session.beginTransaction();

						Query query = session.createSQLQuery("DELETE FROM collection WHERE clid=?")
								.setInteger(0, ((Collection)obj).getClid() );
						query.executeUpdate();
						
						session.getTransaction().commit();
						return null; 
					}
				});		
		return true;
	}
	@Override
	public Collection findByClid(int clid) {
        String hql = "from collection cl where cl.cid=?";
        List collections = this.getHibernateTemplate().find( hql, new Object[]{new Integer(clid)} );		
        return collections.size()==0?null:(Collection)collections.get(0);
	}

}
