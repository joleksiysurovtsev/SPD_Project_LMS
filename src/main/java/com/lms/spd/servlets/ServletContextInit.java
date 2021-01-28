package com.lms.spd.servlets;

import com.lms.spd.Main;
import com.lms.spd.pgsql.JDBCConnector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet
public class ServletContextInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (Objects.requireNonNull(JDBCConnector.getConnection()).isValid(3)) {
                Main.initCashes();
                System.out.println("Connection started");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        System.out.println("ServletContextListener init db connection for cashes ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            Objects.requireNonNull(JDBCConnector.getConnection()).close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("ServletContextListener destroyed");
    }
}