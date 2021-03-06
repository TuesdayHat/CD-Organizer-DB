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

  CD CDOne(){
    return new CD("A Night At The Opera", 1);
  }
  CD CDTwo(){
    return new CD("Wilco", 2);
  }
  CD CDThree() {
    return new CD("Killer Queen", 1);
  }

  @Before
  public void setUp() throws Exception {
    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    cdDao = new Sql2oCdDao(sql2o);
    conn = sql2o.open();
  }

  @After
  public void tearDown() throws Exception {
    conn.close();
  }

  @Test
  public void add_addCdSetsId(){
    CD cdOne = CDOne();
    cdDao.add(cdOne);
    assertEquals(1, cdOne.getId());
  }

  @Test
  public void findById_existingArtistsCanbeFoundById(){
    CD cdOne = CDOne();
    cdDao.add(cdOne);
    CD foundCD = cdDao.findById(cdOne.getId());
    assertEquals(cdOne, foundCD);
  }

  @Test
  public void getAll_getsAllAddedArtists(){
    cdDao.add(CDOne());
    cdDao.add(CDTwo());
    assertEquals(2, cdDao.getAll().size());
  }

  @Test
  public void update_alterArtistName(){
    String initialName = "A Night At The Opera";
    CD cdOne = CDOne();
    cdDao.add(cdOne);

    cdDao.update(cdOne.getId(), "Killer Queen");
    CD updatedCD = cdDao.findById(cdOne.getId());
    assertNotEquals(initialName, updatedCD.getTitle());
  }

  @Test
  public void deleteById_deletesCorrectCD(){
    CD cdOne = CDOne();
    cdDao.add(cdOne);
    CD cdTwo = CDTwo();
    cdDao.add(cdTwo);

    cdDao.deleteById(cdOne.getId());
    assertEquals(1, cdDao.getAll().size());

  }

  @Test
  public void clearAll_clearsAllCDs(){
    CD cdOne = CDOne();
    cdDao.add(cdOne);
    CD cdTwo = CDTwo();
    cdDao.add(cdTwo);

    cdDao.clearAll();
    assertEquals(0, cdDao.getAll().size());
  }
  @Test
  public void getAllCDsByArtist(){
    cdDao.add(CDOne());
    cdDao.add(CDTwo());
    cdDao.add(CDThree());
    assertEquals(2, cdDao.getAllCDsByArtist(1).size());
  }

}