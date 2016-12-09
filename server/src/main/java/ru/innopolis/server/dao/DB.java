package ru.innopolis.server.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

import static ru.innopolis.server.constants.Constants.*;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class DB {
    static DataSource datasource;

    public DB() throws SQLException {
        if (datasource == null) {

            PoolProperties p = new PoolProperties();
            p.setUrl(JDBC_URL);
            p.setDriverClassName(JDBC_DRIVER);
            p.setUsername(USER);
            p.setPassword(PASSWORD);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                    "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (datasource == null) {
            new DB();
        }
        Connection con = datasource.getConnection();
        return con;
    }
}
