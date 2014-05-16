package org.syncServer.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Profile("Production")
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	


	@Bean
	public org.apache.commons.dbcp.BasicDataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/syncDB");
		dataSource.setUsername("databaseuser");
		dataSource.setPassword("bigsecret");
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);
		
		return dataSource;
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
	            setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	            setProperty("hibernate.chach.provider_class", "org.hibernate.cache.NoCacheProvider");
	            setProperty("hibernate.show_sql", "true");	            
//	            setProperty("hibernate.hbm2ddl.auto", "create");
	            setProperty("hibernate.hbm2ddl.auto", "update");

	         }
	      };
	   }


	
	
}
