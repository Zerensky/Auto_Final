package Lesson4;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTest extends AbstractTest{

    @Test
    @Order(1)
    void getCustomers_whenValid_shouldReturn() throws SQLException {
        //given
        String sql = "SELECT * FROM customers";
        Statement stmt = getConnection().createStatement();
        int countTableSize = 0;
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            countTableSize++;
        }
        final Query query = getSession().createSQLQuery(sql).addEntity(CustomersEntity.class);
        Assertions.assertEquals(15, countTableSize);
        Assertions.assertEquals(15, query.list().size());
    }


    @Order(2)
    @ParameterizedTest
    @CsvSource({"Jennifer, Radriges", "Bella, Lorenson", "Henry, Smith"})
    void getCustomerById_whenValid_shouldReturn(String name, String lastName) throws SQLException {
        //given
        String sql = "SELECT * FROM customers WHERE first_name='" + name + "'";
        Statement stmt  = getConnection().createStatement();
        String nameString = "";
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            nameString = rs.getString(3);
        }
        //then
        Assertions.assertEquals(lastName, nameString);
    }


    @Test
    @Order(3)
    void addCustomer_whenValid_shouldSave() {
        //given
        CustomersEntity entity = new CustomersEntity();
        entity.setCustomerId((short) 16);
        entity.setApartment("257");
        entity.setDistrict("Южный");
        entity.setFirstName("Самуил");
        entity.setLastName("Маршак");
        entity.setHouse("16");
        entity.setPhoneNumber("+7 123 456 7887");
        entity.setStreet("Панфиловцев");
        //when
        Session session = getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();

        final Query query = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 16)
                .addEntity(CustomersEntity.class);
        CustomersEntity customersEntity = (CustomersEntity) query.uniqueResult();
        //then
        Assertions.assertNotNull(customersEntity);
        Assertions.assertEquals("257", customersEntity.getApartment());
        Assertions.assertEquals("Маршак", customersEntity.getLastName());
    }


    @Test
    @Order(4)
    void deleteCustomer_whenValid_shouldDelete() {
        //given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 16)
                .addEntity(CustomersEntity.class);
        Optional<CustomersEntity> customersEntity = (Optional<CustomersEntity>) query.uniqueResultOptional();
        Assumptions.assumeTrue(customersEntity.isPresent());
        //when
        Session session = getSession();
        session.beginTransaction();
        session.delete(customersEntity.get());
        session.getTransaction().commit();
        //then
        final Query queryAfterDelete = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 16)
                .addEntity(CustomersEntity.class);
        Optional<CustomersEntity> customersEntityAfterDelete = (Optional<CustomersEntity>) queryAfterDelete
                .uniqueResultOptional();
        Assertions.assertFalse(customersEntityAfterDelete.isPresent());
    }
}
