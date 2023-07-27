package com.std.mdbc.RepoConfig;

import jakarta.persistence.EntityManagerFactory;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Getter
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "Db2EntityManagerFactory",
        transactionManagerRef = "Db2TransactionManager",
        basePackages = {"com.std.mdbc.repos.secondaryrepo"})

public class Db2Conf {

//    @Value("${pring.datasource.db2.driver-class-name}")
//    private String sqliteDriverClassName;
//
//    @Value("${spring.datasource.db2.url}")
//    private String sqliteUrl;
//
//    @Value("${spring.datasource.db2.username}")
//    private String sqliteUsername;
//
//    @Value("${spring.db2.jpa.driver-class-name}")
//    private String sqlitePassword;
//
//    @Bean(name = "Db2DataSource")
//    public DataSource sqliteDataSource() {
//        return DataSourceBuilder.create()
//                .url(getsqliteUrl)
//                .username(sqliteUsername)
//                .password(sqlitePassword)
//                .driverClassName("org.hibernate.dialect.PostgreSQLDialect")
//                .build();
//    }

    @Bean(name = "Db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .build();
    }



    @Bean(name = "Db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean liteEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("Db2DataSource") DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);


        LocalContainerEntityManagerFactoryBean builder1 = builder
                .dataSource(dataSource)
                .packages("com.std.mdbc.models.secondaries")
                .persistenceUnit("UserDb2")
                .build();
        builder1.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("spring.jpa.show-sql","true");
        properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        builder1.setJpaPropertyMap(properties);
        return builder1;

    }

    @Bean(name = "Db2TransactionManager")
    public PlatformTransactionManager liteTransactionManager(@Qualifier("Db2EntityManagerFactory") EntityManagerFactory liteEntityManagerFactory) {
        return new JpaTransactionManager(liteEntityManagerFactory);
    }



}
