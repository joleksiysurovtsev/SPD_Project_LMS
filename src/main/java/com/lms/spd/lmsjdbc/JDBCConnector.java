package com.lms.spd.lmsjdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCConnector {
    public static void main(String[] args) {
        connect();
    }

    public static void connect() {
        DataSource dataSource = createDataSource();

        Flyway flyway = createFlyway(dataSource);
        flyway.migrate();

    }

    private static Flyway createFlyway(DataSource dataSource){
       return Flyway.configure().dataSource(dataSource).load();
    }

    private static Properties loadProperties() {
        InputStream propertiesAsStream = JDBCConnector.class.getClassLoader().getResourceAsStream("db/jdbcconnerctor.properties");
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
        cfg.setJdbcUrl(properties.getProperty("jdbc.url"));
        cfg.setPassword(properties.getProperty("jdbc.password"));
        cfg.setUsername(properties.getProperty("jdbc.username"));
       // System.out.println(properties.getProperty("jdbc.poll.maxConnection"));
        cfg.setMaximumPoolSize(Integer.parseInt(properties.getProperty("jdbc.poll.maxConnection")));
        return new HikariDataSource(cfg);
    }
}
