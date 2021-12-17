package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Constants;
import java.sql.*;

public class MySQLUtil {

    private static final Logger log = LogManager.getLogger(MySQLUtil.class.getName());
    private  static Connection connection = null;

    public static Connection connectToJDBC() {

        try {
            String url = ConfigurationUtil.getConfigurationEntry(Constants.MYSQL_URL);
            String username = ConfigurationUtil.getConfigurationEntry(Constants.MYSQL_USERNAME);
            String password = ConfigurationUtil.getConfigurationEntry(Constants.MYSQL_PASSWORD);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error(e);
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(Constants.CREATE_DATABASE);
            statement.executeUpdate(Constants.CREATE_TABLE_PROFILE);
            statement.executeUpdate(Constants.CREATE_TABLE_PROFILE_EMPLOYER);
            statement.executeUpdate(Constants.CREATE_TABLE_PROFILE_EMPLOYEE);
            statement.executeUpdate(Constants.CREATE_TABLE_VACANCY);
            statement.executeUpdate(Constants.CREATE_TABLE_FEEDBACK);
            return connection;
        } catch (Exception e) {
            return connection;
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }

}
