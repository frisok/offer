package com.advidi.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "offerEntityManagerFactory", transactionManagerRef = "offerTransactionManager", basePackages = ["com.advidi.offer.repository"])
class OfferDatabaseConfiguration {
    @Primary
    @Bean(name = ["offerDataSource"])
    @ConfigurationProperties(prefix = "offer.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean(name = ["offerEntityManagerFactory"])
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder, @Qualifier("offerDataSource") dataSource: DataSource?): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages("com.advidi.offer.domain").persistenceUnit("offer")
                .build()
    }

    @Primary
    @Bean(name = ["offerTransactionManager"])
    fun transactionManager(
            @Qualifier("offerEntityManagerFactory") entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory!!)
    }
}