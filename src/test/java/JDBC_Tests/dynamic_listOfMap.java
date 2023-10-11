package JDBC_Tests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_listOfMap {
    String dbUrl = "jdbc:mysql://localhost/sampledb";
    String dbUsername = "root";
    String dbPassword = "Adnan_1980";


    @Test
    public void dynamicListOfMap() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from customers limit 5;");

        ResultSetMetaData rsmd = resultSet.getMetaData();


        List<Map<String, Object>> queryData = new ArrayList<>();

        // sütün sayısını alalım
        int columnCount = rsmd.getColumnCount();



        while (resultSet.next()){  // --> Ne kadar satır olduğunu bilemedeğimiz için "WHILE" döngüsü içerinde satırlar arasında "resultSet.next()" metodu ile ilerliyoruz.

            Map <String, Object> row = new HashMap<>(); // --> Her bir satıra karşılık gelecek mapler oluşturuyoruz.

            for (int i = 1; i <= columnCount ; i++) {
                row.put(rsmd.getColumnName(i), resultSet.getObject(i));// Oluşturduğumuz mapler içerisine  sütün isimleri ve sütunlara karşılık gelen bilgileri "FOR" döngüsüyle yazdırıyoruz
            }

            queryData.add(row); // --> oluşturduğumuz queryData listininin içerisine maplerimiz ekliyoruz

        }


        // list içerisindeki herbir sütunu (map) bastıralım

        for (Map<String, Object> eachRow : queryData) {

            System.out.println("Herbir satır: " + eachRow.toString());

        }


        // veritabanı bağlantımızı kapatalım

        resultSet.close();
        statement.close();
        connection.close();






    }
}
