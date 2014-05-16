package org.syncServer.Repository.main;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public interface IRepository {

	public SessionFactory getSessionFactory();
	
	public void setSessionFactory(SessionFactory sessionFactory);
	
}
