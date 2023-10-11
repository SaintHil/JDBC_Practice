package JDBC_Tests;

import java.sql.*;


public class db_connection {

    public static void main(String[] args) throws SQLException {

        String db_Url = "jdbc:mysql://localhost/sampledb";
        String db_userName = "root";
        String db_Password = "Adnan_1980";


        // bağlantı oluşturalım

        Connection connection = DriverManager.getConnection(db_Url, db_userName, db_Password);

        // create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // sorgumuzu çalıştırıp  resultSet objesi içerisine taşıyoruz

        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

        resultSet.next();

        // satır ve sütunlar üzerinde işlemler yapıyoruz

        System.out.println("customerNumber: " + resultSet.getInt(1) + ", customerLastName: " + resultSet.getString(3));

        resultSet.next();
        System.out.println("customerName: " + resultSet.getString("customerName") + ", phone: " + resultSet.getString(5));

        // bağlantımızı kapatıyoruz

        resultSet.close();
        statement.close();
        connection.close();

        // resultSet.next();

        // System.out.println("customerName: " + resultSet.getString("customerName") + ", phone: " + resultSet.getString(6));


    }




}
