package com.std.mdbc.configs.repoconfig;

import ch.qos.logback.core.model.processor.PhaseIndicator;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "h2memEntityManagerFactory",
        transactionManagerRef = "h2memTransactionManager",
        basePackages = "com.std.mdbc.dataaccess.repos.primaryrepo")
public class Db1Conf {

    @Bean(name = "h2memDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:h2:mem:testdb")
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
        HashMap<String, Object> properties = new HashMap<>(){

        };

        properties.put("hibernate.hbm2ddl.auto","create-drop");
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show-sql",true);
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
    @Primary
    public EntityManagerFactoryBuilder H2EntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}
