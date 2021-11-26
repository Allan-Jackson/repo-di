package repodi.persistence;

import repodi.Property;
import repodi.PropertyUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private static final String URL = PropertyUtils.getString(Property.DB_URL_REPO_DI.name());
    private static final String USER = PropertyUtils.getString(Property.DB_REP0_DI_USER.name());
    private static final String PASSWORD = PropertyUtils.getString(Property.DB_REPO_DI_PASSWORD.name());

    public static Connection getConnection() throws SQLException {
        if(connection==null || connection.isClosed()){
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
