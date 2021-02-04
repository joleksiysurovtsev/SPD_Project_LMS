package com.lms.spd.pgsql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class JDBCConnector {

    public static Connection getConnection() {
        Connection connection = null;
        DataSource dataSource = createDataSource();
        Flyway flyway = createFlyway(dataSource);
        flyway.migrate();
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            System.err.println("connection failed");
        }
        return connection;
    }

    private static Flyway createFlyway(DataSource dataSource) {
        return Flyway.configure().dataSource(dataSource).load();
    }

    private static DataSource createDataSource() {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        cfg.setPassword("CA2122AP");
        cfg.setUsername("postgres");
        cfg.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(cfg);
    }

    private JDBCConnector() {
    }
}
