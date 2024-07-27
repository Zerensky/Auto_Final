package Lesson4;

import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdersTest extends AbstractTest{

    @Test
    void getOrders_whenValid_shouldReturn() throws SQLException {
        //given
        String sql = "SELECT * FROM orders";
        Statement stmt = getConnection().createStatement();
        int countTableSize = 0;
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            countTableSize++;
        }
        final Query query = getSession().createSQLQuery(sql).addEntity(OrdersEntity.class);
        Assertions.assertEquals(15, countTableSize);
        Assertions.assertEquals(15, query.list().size());
    }


    @ParameterizedTest
    @CsvSource({"1, 1", "2, 2", "4, 4"})
    void getOrdersById_whenValid_shouldReturn(String order_id, String customer_id) throws SQLException {
        //given
        String sql = "SELECT * FROM orders WHERE order_id='" + order_id + "'";
        Statement stmt  = getConnection().createStatement();
        String orderIdString = "";
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            orderIdString = rs.getString(2);
        }
        //then
        Assertions.assertEquals(customer_id, orderIdString);
    }
}
