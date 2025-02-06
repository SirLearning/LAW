package pgl.LAW.V4DB;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MySQLUse extends JFrame {
    Object data[][];
    Object colname[] = {"sID", "name", "age", "major"};
    JTable studentTable;
    public MySQLUse() {
        super("visit database through MySQL JDBC.");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        try{
            /*
            build driver from database provider
             */
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vmap4", "root", "hanalijin");
            Statement stmt = conn.createStatement();
            String sql = "select * from stu";
            ResultSet rs = stmt.executeQuery(sql);
            // change cusor to the last line of result set
            int n = rs.getRow();
            data = new Object[n][10];
            studentTable = new JTable(data, colname);
            c.add(new JScrollPane(studentTable), BorderLayout.CENTER);
            int i = 0;
            // change cusor to the first line
            rs.beforeFirst();
            while (rs.next()){
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getInt(3);
                data[i][3] = rs.getString(4);
                i++;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        MySQLUse app = new MySQLUse();
        app.setSize(500, 200);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
