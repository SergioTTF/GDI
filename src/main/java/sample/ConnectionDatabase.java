package sample;

import java.sql.*;

public class ConnectionDatabase {

    Connection con;

    public ConnectionDatabase() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Class loaded successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Connection setConnection() {
        con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:Instance01", "g172if686cc_eq04", "padkacqz");
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            System.out.println(se.getMessage());
        }

        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
