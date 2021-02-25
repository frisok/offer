package com.advidi.config;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "conversionEntityManagerFactory",
        transactionManagerRef = "conversionTransactionManager", basePackages = {"com.advidi.conversion.repository"})
public class ConversionDatabaseConfiguration {

    @Bean(name = "conversionDataSource")
    @ConfigurationProperties(prefix = "conversion.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "conversionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("conversionDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.advidi.conversion.domain").persistenceUnit("conversion")
                .build();
    }

    @Bean(name = "conversionTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("conversionEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

}