package com.SNJM.models;

import com.SNJM.util.Mysql;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ClientTest {
   @Before
   public void setUp() throws Exception {
       Session session = Mysql.getSession();
       session.beginTransaction();
       session.createNativeQuery("truncate table clients").executeUpdate();
       session.getTransaction().commit();
       session.close();

   }




    @Test
    public void shouldCreateNewClientandSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("Jenny123");
        session.save(c);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, c.getId());
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveDueToNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("0123456wd0112252525252525541111111111111111111111111111111111111111111111111111123546546546546545645645645645645645646545645645645456456456454545545454545");
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

}