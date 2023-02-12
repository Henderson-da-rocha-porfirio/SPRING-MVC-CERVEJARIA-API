package com.algaworks.brewer.config;

import com.algaworks.brewer.model.*;
import com.algaworks.brewer.repository.*;
import org.apache.commons.dbcp2.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import javax.sql.*;
import java.net.*;

@Configuration
@ComponentScan(basePackageClasses = Cervejas.class)
@EnableJpaRepositories(basePackageClasses = Cervejas.class, enableDefaultTransactions = false)
@EnableTransactionManagement
@ComponentScan(basePackageClasses = Cervejas.class)
public class JPAConfig {
	
	@Profile("local")
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/brewerDB");
	}
	
	@Profile("prod")
	@Bean
	public DataSource dataSourceProd() throws URISyntaxException {
		URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

	    String username = jdbUri.getUserInfo().split(":")[0];
	    String password = jdbUri.getUserInfo().split(":")[1];
	    String port = String.valueOf(jdbUri.getPort());
	    String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
	    
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setUrl(jdbUrl);
	    dataSource.setUsername(username);
	    dataSource.setPassword(password);
	    dataSource.setInitialSize(10);
	    return dataSource;
}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(Cerveja.class.getPackage().getName());
		factory.setMappingResources("sql/consultas-nativas.xml");
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
}
