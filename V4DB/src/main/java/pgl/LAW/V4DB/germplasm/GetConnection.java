package pgl.LAW.V4DB.germplasm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    public Connection getConn(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/germplasm", "root", "hanalijin"
            );
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return conn;
    }
}
