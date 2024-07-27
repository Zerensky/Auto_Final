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
public class CourierInfoTest extends AbstractTest{

    @Test
    @Order(1)
    void getCourierInfo_whenValid_shouldReturn() throws SQLException {
        //given
        String sql = "SELECT * FROM courier_info";
        Statement stmt = getConnection().createStatement();
        int countTableSize = 0;
        //when
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            countTableSize++;
        }
        final Query query = getSession().createSQLQuery(sql).addEntity(CourierInfoEntity.class);
        Assertions.assertEquals(4, countTableSize);
        Assertions.assertEquals(4, query.list().size());
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource({"John, Rython", "Bob, Kolaris"})
    void getCourierById_whenValid_shouldReturn(String name, String lastName) throws SQLException {
        //given
        String sql = "SELECT * FROM courier_info WHERE first_name='" + name + "'";
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
    void addCourierInfo_whenValid_shouldSave() {
        //given
        CourierInfoEntity courierInfo = new CourierInfoEntity();
        courierInfo.setCourierId((short) 5);
        courierInfo.setFirstName("Почтальон");
        courierInfo.setLastName("Печкин");
        courierInfo.setPhoneNumber("+ 7 111 222 3456");
        courierInfo.setDeliveryType("bike");
        //when
        Session session = getSession();
        session.beginTransaction();
        session.persist(courierInfo);
        session.getTransaction().commit();

        final Query query = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE courier_id=" + 5)
                .addEntity(CourierInfoEntity.class);
        CourierInfoEntity courierInfoEntity = (CourierInfoEntity) query.uniqueResult();
        //then
        Assertions.assertNotNull(courierInfoEntity);
        Assertions.assertEquals("Почтальон", courierInfoEntity.getFirstName());
        Assertions.assertEquals("Печкин", courierInfoEntity.getLastName());
        Assertions.assertEquals("bike", courierInfoEntity.getDeliveryType());
    }

    @Test
    @Order(4)
    void deleteCourierInfo_whenValid_shouldDelete() {
        //given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE courier_id=" + 5)
                .addEntity(CourierInfoEntity.class);
        Optional<CourierInfoEntity> courierInfoEntity = (Optional<CourierInfoEntity>) query.uniqueResultOptional();
        Assumptions.assumeTrue(courierInfoEntity.isPresent());
        //when
        Session session = getSession();
        session.beginTransaction();
        session.delete(courierInfoEntity.get());
        session.getTransaction().commit();
        //then
        final Query queryAfterDelete = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE courier_id=" + 5)
                .addEntity(CourierInfoEntity.class);
        Optional<CourierInfoEntity> courierInfoEntityAfterDelete = (Optional<CourierInfoEntity>) queryAfterDelete
                .uniqueResultOptional();
        Assertions.assertFalse(courierInfoEntityAfterDelete.isPresent());
    }
}
