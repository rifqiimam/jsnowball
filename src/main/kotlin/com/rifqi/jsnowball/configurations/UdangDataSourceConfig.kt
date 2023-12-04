package com.rifqi.jsnowball.configurations

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.Properties
import javax.sql.DataSource

@Configuration

@EnableJpaRepositories(
    basePackages = ["com.rifqi.jsnowball.repository.udang"],
    entityManagerFactoryRef = "udangEntityManagerFactory",
    transactionManagerRef = "udangPlatformTransactionManager"
)
class UdangDataSourceConfig {

    @Value("\${app.datasource.udang.hikari.connection-timeout}")
    private lateinit var connectionTimeout: String

    @Value("\${app.datasource.udang.hikari.minimum-idle}")
    private lateinit var minimumIdle: String

    @Value("\${app.datasource.udang.hikari.maximum-pool-size}")
    private lateinit var maximumPoolSize: String

    @Value("\${app.datasource.udang.hikari.idle-timeout}")
    private lateinit var idleTimeout: String

    @Value("\${app.datasource.udang.hikari.max-lifetime}")
    private lateinit var maxLifetime: String

    @Bean
    @ConfigurationProperties("app.datasource.udang")
    fun udangDataSourceProperties() :DataSourceProperties{
        return DataSourceProperties()
    }


    @Bean
    @ConfigurationProperties("app.datasource.udang.configuration")
    fun udangDataSource(): DataSource {
        return udangDataSourceProperties().initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
            .apply {
                connectionTimeout = this@UdangDataSourceConfig.connectionTimeout.toLong()
                minimumIdle = this@UdangDataSourceConfig.minimumIdle.toInt()
                maximumPoolSize = this@UdangDataSourceConfig.maximumPoolSize.toInt()
                idleTimeout = this@UdangDataSourceConfig.idleTimeout.toLong()
                maxLifetime = this@UdangDataSourceConfig.maxLifetime.toLong()
            }
    }

    @Bean(name = ["udangEntityManagerFactory"])
    fun udangEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(udangDataSource())
            .packages("com.rifqi.jsnowball.entity.udang")
            .build()
    }

    @Bean
    fun udangPlatformTransactionManager(@Qualifier("udangEntityManagerFactory") udangEntityManagerFactory:LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager?{
        return JpaTransactionManager(udangEntityManagerFactory.getObject()!!)
    }
}


