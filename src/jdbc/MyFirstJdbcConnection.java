package jdbc;

import java.sql.*;

public class MyFirstJdbcConnection {
    public static void main(String[] args) throws SQLException {
        String connectionUrl = "jdbc:sqlserver://morfeusz.wszib.edu.pl:1433;databaseName=AdventureWorks;user=dpuzio;password=haslo@12 ";

        Connection con = DriverManager.getConnection(connectionUrl);

        Statement stmn = con.createStatement();

        ResultSet rs = stmn.executeQuery("select * from Person.Contact");

        while (rs.next()){
            System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
        }

        rs.close();
        stmn.close();
        con.close();
    }
}
