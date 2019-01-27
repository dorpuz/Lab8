package jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab6_1 {
    public static void main(String[] args) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("dpuzio");
        ds.setServerName("morfeusz.wszib.edu.pl");
        ds.setPortNumber(1433);
        ds.setDatabaseName("AdventureWorks");
        ds.setPassword("haslo@12");

        String sql = "select top 10 * from Person.Contact where LastName = 'Anderson'";

        try (Connection con = ds.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
            }

        } catch (SQLException sqle){
            System.err.println("Problems with db: " + sqle.getMessage());
        }

        System.out.println("----------");

        String sql2 = "select distinct Title from HumanResources.Employee";
        try (Connection con = ds.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql2);

            while (rs.next()){
                System.out.println(rs.getString("Title"));
            }

        } catch (SQLException sqle){
            System.err.println("Problems with db: " + sqle.getMessage());
        }

        System.out.println("-------------------------");

        String sql3 = "select CustomerID, count(*) as NoOfOrders\n" +
                "from Sales.SalesOrderHeader\n" +
                "group by CustomerID\n" +
                "order by NoOfOrders desc";
        try (Connection con = ds.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql3);

            while (rs.next()){
                System.out.println(rs.getString("CustomerID") + " " + rs.getString("NoOfOrders"));
            }

        } catch (SQLException sqle){
            System.err.println("Problems with db: " + sqle.getMessage());
        }

    }
}
