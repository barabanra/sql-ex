package ru.barabanra.sqlex.config;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Bean
    @ConfigurationProperties("db.datasource")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
        TransactionAwareDataSourceProxy transactionAwareDataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        return new DataSourceConnectionProvider(transactionAwareDataSourceProxy);
    }

    @Bean
    public DSLContext dsl(DefaultConfiguration jooqConfiguration) {
        return new DefaultDSLContext(jooqConfiguration);
    }

    @Bean
    public DefaultConfiguration jooqConfiguration(DataSourceConnectionProvider connectionProvider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider);
        return jooqConfiguration;
    }

}