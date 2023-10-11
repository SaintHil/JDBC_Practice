package JDBC_Tests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMap_example {


    String dbUrl = "jdbc:mysql://localhost/hr";
    String dbUsername = "root";
    String dbPassword = "Adnan_1980";


    @Test
    public void ListOfMapExample() {

        List<Map<String, Object>> queryData = new ArrayList<>();

        Map <String, Object > row1 = new HashMap<>();

        row1.put ("first_name", "Steven");
        row1.put ("last_name", "King");
        row1.put ("salary", 24000);
        row1.put ("job_id", 4);

        System.out.println("1. satır = " + row1.toString());



        Map <String, Object > row2 = new HashMap<>();

        row2.put ("first_name", "Neena");
        row2.put ("last_name", "Kochar");
        row2.put ("salary", 17000);
        row2.put ("job_id", 5);

        System.out.println("2. Satır= " + row2.toString());

        // birer birer listin içerisine maplerimizi atıyoruz

        queryData.add(row1);
        queryData.add(row2);

        System.out.println("last name: " + queryData.get(0).get("last_name"));
        System.out.println("salary: " + queryData.get(1).get("salary"));


    }


    @Test
    public void metaDataExample() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, salary, job_id FROM employees LIMIT 5;");


        ResultSetMetaData rsmd = resultSet.getMetaData();

        resultSet.next();

        List<Map<String, Object>> queryData = new ArrayList<>();

        Map <String, Object > row1 = new HashMap<>();

        row1.put (rsmd.getColumnName(1), resultSet.getString(1) );
        row1.put (rsmd.getColumnName(2), resultSet.getString(2) );
        row1.put (rsmd.getColumnName(3), resultSet.getString(3) );
        row1.put (rsmd.getColumnName(4), resultSet.getString(4) );


        System.out.println("row1.toString() = " + row1.toString());

        resultSet.next();


        Map <String, Object > row2 = new HashMap<>();

        row2.put (rsmd.getColumnName(1), resultSet.getString(1) );
        row2.put (rsmd.getColumnName(2), resultSet.getString(2) );
        row2.put (rsmd.getColumnName(3), resultSet.getString(3) );
        row2.put (rsmd.getColumnName(4), resultSet.getString(4) );


        System.out.println("row1.toString() = " + row2.toString());

        queryData.add(row1);
        queryData.add(row2);


        System.out.println("last name: " + queryData.get(0).get("last_name"));
        System.out.println("salary: " + queryData.get(1).get("salary"));


    }
}
