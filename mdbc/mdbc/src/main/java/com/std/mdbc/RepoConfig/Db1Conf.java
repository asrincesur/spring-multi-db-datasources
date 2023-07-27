package com.std.mdbc.RepoConfig;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "h2memEntityManagerFactory",
        transactionManagerRef = "h2memTransactionManager",
        basePackages = "com.std.mdbc.repos.PrimaryRepo")
public class Db1Conf {


    @Bean(name = "h2memDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:h2:mem:testdb")
                .driverClassName("org.hibernate.dialect.H2Dialect")
                .build();
    }

    @Bean(name = "h2memEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);


        LocalContainerEntityManagerFactoryBean builder1 = builder
                .dataSource(dataSource())
                .packages("com.std.mdbc.models.primaries")
                .persistenceUnit("UserH2")
                .build();
        builder1.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.put("spring.jpa.show-sql","true");
        builder1.setJpaPropertyMap(properties);
        return builder1;
    }

    @Bean(name = "h2memTransactionManager")
    @Primary
    public PlatformTransactionManager h2TransactionManager(
            @Qualifier("h2memEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}
