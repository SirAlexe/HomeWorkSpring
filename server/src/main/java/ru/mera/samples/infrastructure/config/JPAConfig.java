package ru.mera.samples.infrastructure.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ru.mera.samples.application.mappings.AddressToDTOMap;
import ru.mera.samples.application.mappings.AddressToEntityMap;
import ru.mera.samples.application.mappings.ImageToDTOMap;
import ru.mera.samples.application.mappings.ImageToEntityMap;
import ru.mera.samples.application.mappings.UserToDTOMap;
import ru.mera.samples.application.mappings.UserToEntityMap;
import ru.mera.samples.application.service.EmbeddedServiceBean;
import ru.yandex.qatools.embed.service.PostgresEmbeddedService;

@Configuration
@EnableTransactionManagement
public class JPAConfig {

	private static final Log logger = LogFactory.getLog(JPAConfig.class);

	public static final String HOST = "localhost";

	public static final int PORT = 5432;

	public static final String USERNAME = "postgres";

	public static final String PASSWORD = "postrges";

	public static final String DB_NAME = "test";

	{
		logger.info("JPA Configuration loaded.");
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		EntityManagerFactory entityManagerFactory1 = entityManagerFactory().getObject();
		JpaTransactionManager jtManager = new JpaTransactionManager(entityManagerFactory1);
		return jtManager;
	}

	@Bean
	@DependsOn("embeddedServiceBean")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		System.out.println("===>LocalContainerEntityManagerFactoryBean");
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("PersistenceUnit");
		factoryBean.setDataSource(dataSource());
		JpaVendorAdapter hibernateVendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(hibernateVendorAdapter);
		String[] packages = { "ru.mera.samples.domain.entities" };
		factoryBean.setPackagesToScan(packages);
		factoryBean.setJpaProperties(getJPAProperties());
		return factoryBean;
	}

	@Bean
	public EmbeddedServiceBean embeddedServiceBean() throws IOException {
		EmbeddedServiceBean embeddedServiceBean = new EmbeddedServiceBean();
		embeddedServiceBean.setService(
				new PostgresEmbeddedService(getHost(), getPort(), getUsername(), getPassword(), getDbName()));
		return embeddedServiceBean;
	}

	@Bean
	public ModelMapper modelMapper() throws IOException {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.addMappings(new ImageToDTOMap());
		modelMapper.addMappings(new ImageToEntityMap());

		modelMapper.addMappings(userToDTOMap());

		// TODO don't work modelMapper.addMappings(userToEntityMap());

		modelMapper.addMappings(addressToEntityMap());
		modelMapper.addMappings(addressToDTOMap());

		return modelMapper;
	}

	@Bean
	// @Scope( value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode =
	// ScopedProxyMode.TARGET_CLASS)
	public AddressToDTOMap addressToDTOMap() {
		return new AddressToDTOMap();
	}

	@Bean
	public AddressToEntityMap addressToEntityMap() {
		return new AddressToEntityMap();
	}

	@Bean
	public UserToDTOMap userToDTOMap() {
		return new UserToDTOMap();
	}

	@Bean
	public UserToEntityMap userToEntityMap() {
		return new UserToEntityMap();
	}

	private Properties getJPAProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		// properties.setProperty("javax.persistence.jdbc.driver",
		// "org.postgresql.Driver");
		// properties.setProperty("javax.persistence.jdbc.url",
		// "jdbc:postgresql://" + getHost() + ":" + getPort() + "/" +
		// getDbName());
		// properties.setProperty("javax.persistence.jdbc.user", getUsername());
		// properties.setProperty("javax.persistence.jdbc.password",
		// getPassword());
		return properties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://" + getHost() + ":" + getPort() + "/" + getDbName());
		dataSource.setUsername(getUsername());
		dataSource.setPassword(getPassword());

		return dataSource;
	}

	private String getPassword() {
		return PASSWORD;
	}

	private String getUsername() {
		return USERNAME;
	}

	private String getDbName() {
		return DB_NAME;
	}

	private int getPort() {
		return PORT;
	}

	private String getHost() {
		return HOST;
	}
}