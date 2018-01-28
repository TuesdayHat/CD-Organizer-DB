package dao;
import models.*;
import dao.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oArtistDaoTest {

  private Connection conn;
  private Sql2oArtistDao artistDao;
  private Sql2oCdDao cdDao;

  @Before
  public void setUp() throws Exception {
    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    artistDao = new Sql2oArtistDao(sql2o);
//    cdDao = new Sql2oCdDao(sql2o);
    conn = sql2o.open();
  }

  @After
  public void tearDown() throws Exception {
    conn.close();
  }

  public Artist testArtist() {
    return new Artist("The Beatles");
  }

  @Test
  public void add_addArtistSetsId(){
    Artist artist = testArtist();
    artistDao.add(artist);
    assertEquals(1, artist.getId());
  }

  @Test
  public void findById_existingArtistsCanbeFoundById(){
    Artist artist = testArtist();
    artistDao.add(artist);
    Artist foundArtist = artistDao.findById(artist.getId());
    assertEquals(artist, foundArtist);
  }
}
