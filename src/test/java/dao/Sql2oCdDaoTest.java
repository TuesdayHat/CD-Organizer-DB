package dao;

import models.*;
import dao.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oCdDaoTest {

  private Connection conn;
  private Sql2oArtistDao artistDao;
  private Sql2oCdDao cdDao;

  CD testCDOne = new CD("A Night At The Opera");
  CD testCDTwo = new CD("Wilco");
  CD testCDThree = new CD("{awayland}");
  CD testCDFour = new CD("AM");
  CD testFive = new CD("Comfort Eagle");

  @Before
  public void setUp() throws Exception {
    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
//    artistDao = new Sql2oArtistDao(sql2o);
    cdDao = new Sql2oCdDao(sql2o);
    conn = sql2o.open();
  }

  @After
  public void tearDown() throws Exception {
    conn.close();
  }

  @Test
  public void add_addCdSetsId(){
    cdDao.add(testCDOne);
    assertEquals(1, testCDOne.getId());
  }
}