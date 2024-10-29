package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 * @param <T>
 */
public abstract class DBContext<T> {

    protected Connection connection;

    public DBContext() {
        String user = "zeldais";
        String pass = "1234567890";
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=PRJ301_MyAssignment;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract void insert(T model);

    public abstract void update(T model);

    public abstract void delete(T model);

    public abstract ArrayList<T> list();

    public abstract T get(int id);
}
