package com.lms.spd.lmsjdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class JDBCConnector {

    public static void connect() {
        DataSource dataSource = createDataSource();

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("");

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static Properties loadProperties() {
        InputStream propertiesAsStream = JDBCConnector.class.getClassLoader().getResourceAsStream("jdbcconnerctor.properties");
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
        cfg.setPassword(properties.getProperty("jdbc.username"));
        cfg.setUsername(properties.getProperty("jdbc.password"));
        cfg.setMaximumPoolSize(Integer.parseInt(properties.getProperty("jdbc.poll.maxConnection")));
        return new HikariDataSource(cfg);
    }
}
