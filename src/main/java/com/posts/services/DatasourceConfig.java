package com.posts.services;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.posts.services.jpa.TweetRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses={TweetRepository.class})
@PropertySource(value="classpath:database.properties")
public class DatasourceConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource source = new DriverManagerDataSource();

		source.setDriverClassName(env.getProperty("driver.class"));
		source.setUrl(env.getProperty("database.url"));
		source.setUsername(env.getProperty("database.user"));
		source.setPassword(env.getProperty("database.password"));

		return source;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean entityFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityFactoryBean.setDataSource(dataSource);
		entityFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		entityFactoryBean.setPackagesToScan("com.posts.vo.data");

		Properties jpaProperties = new Properties();
		//
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.put("show_sql", "true");
		//		
		entityFactoryBean.setJpaProperties(jpaProperties);

		/*
		entityFactoryBean.afterPropertiesSet();
		 */

		entityFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

		return entityFactoryBean;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean factory) {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(factory.getObject());
		return txManager;
	}

}
