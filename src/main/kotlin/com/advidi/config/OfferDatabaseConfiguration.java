package com.advidi.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "offerEntityManagerFactory",
        transactionManagerRef = "offerTransactionManager", basePackages = {"com.advidi.offer.repository"})
public class OfferDatabaseConfiguration {

    @Primary
    @Bean(name = "offerDataSource")
    @ConfigurationProperties(prefix = "offer.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "offerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("offerDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.advidi.offer.domain").persistenceUnit("offer")
                .build();
    }

    @Primary
    @Bean(name = "offerTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("offerEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}