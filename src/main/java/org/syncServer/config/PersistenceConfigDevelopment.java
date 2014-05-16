package org.syncServer.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Profile("Development")
@Configuration
@EnableTransactionManagement
public class PersistenceConfigDevelopment {
	


	@Bean
	public javax.sql.DataSource dataSource() {
		
		 EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		 System.out.println("H2 DataSource INITIATED");
		 return builder.setType(EmbeddedDatabaseType.H2).build();	
	     
	     
	}
	
	@Bean
	public org.springframework.orm.hibernate4.LocalSessionFactoryBean sessionFactory() {
		
		org.springframework.orm.hibernate4.LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(this.dataSource());
		sessionFactory.setPackagesToScan("org.syncServer");
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}
	
	
	   @Bean
	   public HibernateTransactionManager transactionManager() {
	      HibernateTransactionManager txManager = new HibernateTransactionManager();
	      txManager.setSessionFactory(sessionFactory().getObject());

	      return txManager;
	   }

	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	

	   Properties hibernateProperties() {
	      return new Properties() {


			{
	            setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	            setProperty("connection.driver_class", "org.h2.Driver");
	            setProperty("hibernate.connection.url", "jdbc:h2:./db/repository");
	            setProperty("hibernate.show_sql", "true");	            
//	            setProperty("hibernate.hbm2ddl.auto", "create");
	            setProperty("hibernate.hbm2ddl.auto", "create-drop");
//	            setProperty("hibernate.hbm2ddl.auto", "update");

	         }
	      };
	   }


	
	
}
