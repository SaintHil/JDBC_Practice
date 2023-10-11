package JDBC_Tests;

import org.testng.annotations.Test;
import utiltities.dbUtils;

import java.util.List;
import java.util.Map;

public class dbUtils_practice {


    @Test
    public void test1() {
        //create connection
        dbUtils.createConnection();


        //using method to get result as a list of maps
        List<Map<String, Object>> queryData = dbUtils.getQueryResultMap("SELECT customerNumber,customerName,contactLastName,contactFirstName, " +
                "phone,addressLine1,addressLine2,city_id,town_id,postalCode,country_id," +
                "salesRepEmployeeNumber,creditLimit FROM customers");

        //printing the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close connection
        dbUtils.destroy();
    }


    @Test
    public void test3() {

        dbUtils.createConnection();

        List<Object> columnData = dbUtils.getColumnData("SELECT * FROM customers", "contactFirstName");

        System.out.println(columnData.toString());

        dbUtils.destroy();
    }



}
