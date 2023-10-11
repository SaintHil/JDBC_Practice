package JDBC_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

public class CRUD_examples {

    String dbUrl = "jdbc:mysql://localhost/sampledb";
    String dbUsername = "root";
    String dbPassword = "Adnan_1980";



    @Test
    public void createRecord() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String queryOne = "INSERT INTO customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city_id,town_id,postalCode,country_id,salesRepEmployeeNumber,creditLimit)\n" +
                "\tValues(556,'Google','Tech','Euro ','40.32.2555','54, London','United Kingdom',1,1,44000,7,1370,21000.00);";

       int rowsAffected = statement.executeUpdate(queryOne);
        System.out.println("rowsAffected = " + rowsAffected);

        statement.close();
        connection.close();

    }


    @Test
    public void updateRecord() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


        String expectedCustomerName = "Bootflow";
        String queryOne = "UPDATE customers SET customerName = '" + expectedCustomerName + "' WHERE customerNumber=556";

        int rowsAffected = statement.executeUpdate(queryOne);

        // System.out.println("rowsAffected = " + rowsAffected);


        String queryTwo = "select * from customers WHERE customerNumber=556;";
        ResultSet resultSet = statement.executeQuery(queryTwo);

        resultSet.next();

        System.out.println("güncellenen müşteri ismi = " + resultSet.getString("customerName"));

        Assert.assertEquals(resultSet.getString("customerName"), expectedCustomerName, "Bir hata oluştu" );

        String queryThree = "SELECT * FROM customers;";

        resultSet = statement.executeQuery(queryThree);
        resultSet.next();

        System.out.println("Birinci satırdaki müşteri ismi :" + resultSet.getString("customerName"));


        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void deleteRecord() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


        String deleteQuery = "DELETE from customers WHERE customerNumber = 556;";

        int rowsAffected = statement.executeUpdate(deleteQuery);
        System.out.println("Silinen kişi sayısı: " + rowsAffected);

        statement.close();
        connection.close();




    }
}
