package com.lms.spd.pgsql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnector {

    // public static Connection connection = getConnection();

    public static Connection getConnection() {
        Connection connection = null;
        DataSource dataSource = createDataSource();
        //   Flyway flyway = createFlyway(dataSource);
        //   flyway.migrate();
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            System.err.println("connection failed");
        }
        return connection;
    }

    private static Flyway createFlyway(DataSource dataSource) {
        return Flyway.configure().dataSource(dataSource).load();
    }

    private static Properties loadProperties() {
        InputStream propertiesAsStream = JDBCConnector.class.getClassLoader()
                .getResourceAsStream("db/jdbcconnerctor.properties");
        Properties properties = new Properties();
        try {
            properties.load(propertiesAsStream);
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the input stream.");
        }
        return properties;
    }

    private static DataSource createDataSource() {
        Properties properties = loadProperties();
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        cfg.setPassword("CA2122AP");
        cfg.setUsername("postgres");
        cfg.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(cfg);
    }
}
