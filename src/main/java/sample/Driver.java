package sample;

import java.sql.*;

public class Driver {

    Connection con;

    public Driver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Class loaded successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void setConnection() {
        con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:Instance01", "g172if686cc_eq04", "padkacqz");
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            System.out.println(se.getMessage());
        }
        if (con != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public static void main(String[] args) {
        Driver driverCon = new Driver();
        driverCon.setConnection();

    }
}
