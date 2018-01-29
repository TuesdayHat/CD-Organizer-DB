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

  CD testCDFour = new CD("AM", 4);
  CD testFive = new CD("Comfort Eagle" , 5);

  CD CDOne(){
    return new CD("A Night At The Opera", 1);
  }
  CD CDTwo(){
    return new CD("Wilco", 2);
  }
  CD CDThree() {
    return new CD("{awayland}", 3);
  }

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
    CD CDOne = CDOne();
    cdDao.add(CDOne);
    assertEquals(1, CDOne.getId());
  }

  @Test
  public void findById_existingArtistsCanbeFoundById(){
    CD CDOne = CDOne();
    cdDao.add(CDOne);
    CD foundCD = cdDao.findById(CDOne.getId());
    assertEquals(CDOne, foundCD);
  }

  @Test
  public void getAll_getsAllAddedArtists(){
    cdDao.add(CDOne());
    cdDao.add(CDTwo());
    assertEquals(2, cdDao.getAll().size());
  }
}