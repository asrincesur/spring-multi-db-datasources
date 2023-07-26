package com.std.mdbc.RepoConfig;

import jakarta.persistence.EntityManagerFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Data
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "Db2EntityManagerFactory",
        transactionManagerRef = "Db2TransactionManager",
        basePackages = {"com.std.mdbc.repos.secondaryrepo"})

public class Db2Conf {

    @Value("${spring.datasource.db2.driver-class-name}")
    private String sqliteDriverClassName;

    @Value("${spring.datasource.db2.url}")
    private String sqliteUrl;

    @Value("${spring.datasource.db2.username}")
    private String sqliteUsername;

    @Value("${spring.datasource.db2.password}")
    private String sqlitePassword;

    @Bean(name = "Db2DataSource")
    public DataSource sqliteDataSource() {
        return DataSourceBuilder.create()
                .url(getSqliteUrl())
                .username(getSqliteUsername())
                .password(getSqlitePassword())
                .driverClassName(getSqliteDriverClassName())
                .build();
    }

    @Bean(name = "Db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean liteEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("Db2DataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.std.mdbc.models.secondaries")
                .persistenceUnit("UserDb2")
                .build();
    }

    @Bean(name = "Db2TransactionManager")
    public PlatformTransactionManager liteTransactionManager(@Qualifier("Db2EntityManagerFactory") EntityManagerFactory liteEntityManagerFactory) {
        return new JpaTransactionManager(liteEntityManagerFactory);
    }

}
