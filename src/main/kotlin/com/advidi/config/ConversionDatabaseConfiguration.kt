package com.advidi.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "conversionEntityManagerFactory", transactionManagerRef = "conversionTransactionManager", basePackages = ["com.advidi.conversion.repository"])
class ConversionDatabaseConfiguration {
    @Bean(name = ["conversionDataSource"])
    @ConfigurationProperties(prefix = "conversion.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["conversionEntityManagerFactory"])
    fun barEntityManagerFactory(
            builder: EntityManagerFactoryBuilder, @Qualifier("conversionDataSource") dataSource: DataSource?): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages("com.advidi.conversion.domain").persistenceUnit("conversion")
                .build()
    }

    @Bean(name = ["conversionTransactionManager"])
    fun barTransactionManager(
            @Qualifier("conversionEntityManagerFactory") barEntityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
        return JpaTransactionManager(barEntityManagerFactory!!)
    }
}