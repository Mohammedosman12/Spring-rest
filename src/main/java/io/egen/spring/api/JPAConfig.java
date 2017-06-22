package io.egen.spring.api;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:application.properties")
public class JPAConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean emf(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setDataSource(getDatasource());
		emf.setPackagesToScan("io.egen.spring.api.entity");
		emf.setJpaProperties(jpaProperties());
		return emf;
	}
	
	@Bean
	public DataSource getDatasource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(env.getProperty("db.url","jdbc:mysql://localhost:3306/exampledb?useSSL=false"));
		ds.setUsername(env.getProperty("db.username","root"));
		ds.setPassword(env.getProperty("db.password","root123"));
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager txtManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}
	private Properties jpaProperties(){
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql","true"));
		props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format.sql","true"));
		props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl","create"));
		return props;
	}
}
