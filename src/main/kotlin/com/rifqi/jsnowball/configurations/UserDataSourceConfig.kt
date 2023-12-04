package com.rifqi.jsnowball.configurations

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(
    basePackages = ["com.rifqi.jsnowball.repository.user"],
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userPlatformTransactionManager"
)
class UserDataSourceConfig {

    @Value("\${app.datasource.user.hikari.connection-timeout}")
    private lateinit var connectionTimeout: String

    @Value("\${app.datasource.user.hikari.minimum-idle}")
    private lateinit var minimumIdle: String

    @Value("\${app.datasource.user.hikari.maximum-pool-size}")
    private lateinit var maximumPoolSize: String

    @Value("\${app.datasource.user.hikari.idle-timeout}")
    private lateinit var idleTimeout: String

    @Value("\${app.datasource.user.hikari.max-lifetime}")
    private lateinit var maxLifetime: String

    @Primary
    @Bean
    @ConfigurationProperties("app.datasource.user")
    fun userDataSourceProperties() : DataSourceProperties {
        return DataSourceProperties()
    }


    @Primary
    @Bean
    @ConfigurationProperties("app.datasource.user.configuration")
    fun userDataSource(): DataSource {
        return userDataSourceProperties().initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
            .apply {
                connectionTimeout = this@UserDataSourceConfig.connectionTimeout.toLong()
                minimumIdle = this@UserDataSourceConfig.minimumIdle.toInt()
                maximumPoolSize = this@UserDataSourceConfig.maximumPoolSize.toInt()
                idleTimeout = this@UserDataSourceConfig.idleTimeout.toLong()
                maxLifetime = this@UserDataSourceConfig.maxLifetime.toLong()
            }
    }

    @Primary
    @Bean(name = ["userEntityManagerFactory"])
    fun userEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {

        return builder
            .dataSource(userDataSource())
            .packages("com.rifqi.jsnowball.entity.user")
            .build()
    }


    @Primary
    @Bean
    fun userPlatformTransactionManager(@Qualifier("userEntityManagerFactory") userEntityManagerFactory:LocalContainerEntityManagerFactoryBean
        ): PlatformTransactionManager?{
        return JpaTransactionManager(userEntityManagerFactory.getObject()!!)
        }
}


