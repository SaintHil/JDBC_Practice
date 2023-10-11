package JDBC_Tests;

import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_examples {

    String db_Url = "jdbc:mysql://localhost/sampledb";
    String db_userName = "root";
    String db_Password = "Adnan_1980";

    @Test
    public void test1() throws SQLException {

        System.out.println("--- bağlantı oluşturuldu ---");

        Connection connection = DriverManager.getConnection(db_Url, db_userName, db_Password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

        resultSet.last();

        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        resultSet.beforeFirst();

        System.out.println("---------------------------------------------------------------");
        while (resultSet.next()){

            System.out.println("customerName: " + resultSet.getString("customerName"));
        }

        System.out.println("---------------------------------------------------------------");


        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void test2() throws SQLException {

        System.out.println("--- bağlantı oluşturuldu ---");

        Connection connection = DriverManager.getConnection(db_Url, db_userName, db_Password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

        // database ile ilgili ekstra bilgileri alıyoruz

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(" veritabanı kullanıcı ismi : " + databaseMetaData.getUserName());
        System.out.println("db sürücü ismi: " + databaseMetaData.getDriverName());
        System.out.println("db ürün ismi:" + databaseMetaData.getDatabaseProductName());
        System.out.println("db adres bilgisi:  " + databaseMetaData.getURL());
        System.out.println("db driver versiyon bilgisi:" + databaseMetaData.getDriverVersion());

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();

        System.out.println("columnCount = " + columnCount);

        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(2));
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(3));
        System.out.println("resultSetMetaData.getColumnType() = " + resultSetMetaData.getColumnType(4));

        for (int i = 1; i < columnCount +1 ; i++) {

            System.out.println("column " + i + " " + resultSetMetaData.getColumnName(i));

        }


        resultSet.close();
        statement.close();
        connection.close();


        // vt bağlantısını oluşturun
        // customers tablosunda kaçtane satır olduğunu COUNT ifadesi kullanarak getirin
        // bağlantıyı kapatın



    }


}
