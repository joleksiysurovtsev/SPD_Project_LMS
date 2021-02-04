package com.lms.spd.servlets;

import com.lms.spd.Main;
import com.lms.spd.pgsql.JDBCConnector;
import com.lms.spd.utils.Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

@WebServlet
public class ServletContextInit implements ServletContextListener {

    public static final int TIMEOUT = 3;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (Objects.requireNonNull(JDBCConnector.getConnection()).isValid(TIMEOUT)) {
                Main.initCashes();
                Util.GLOBAL_LOGGER.info("Connection started");
            }
        } catch (SQLException throwable) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(throwable.getStackTrace()));
        }
        Util.GLOBAL_LOGGER.info("ServletContextListener init db connection for cashes ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            Objects.requireNonNull(JDBCConnector.getConnection()).close();
        } catch (SQLException throwables) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(throwables.getStackTrace()));
        }
        Util.GLOBAL_LOGGER.info("ServletContextListener destroyed");
    }
}