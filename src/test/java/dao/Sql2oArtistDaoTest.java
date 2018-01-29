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

  public Artist artistOne() {
    return new Artist("The Beatles");
  }

  public Artist artistTwo(){
    return new Artist("The Who");
  }

  @Test
  public void add_addArtistSetsId(){
    Artist artist = artistOne();
    artistDao.add(artist);
    assertEquals(1, artist.getId());
  }

  @Test
  public void findById_existingArtistsCanbeFoundById(){
    Artist artist = artistOne();
    artistDao.add(artist);
    Artist foundArtist = artistDao.findById(artist.getId());
    assertEquals(artist, foundArtist);
  }

  @Test
  public void getAll_getsAllAddedArtists(){
    artistDao.add(artistOne());
    artistDao.add(artistTwo());
    assertEquals(2, artistDao.getAll().size());
  }

  @Test
  public void update_alterArtistName(){
    String initialName = "The Beatles";
    Artist artist = artistOne();
    artistDao.add(artist);

    artistDao.update(artist.getId(), "Radiohead");
    Artist updatedArtist = artistDao.findById(artist.getId());
    assertNotEquals(artist, updatedArtist);
  }

  @Test
  public void deleteById_deletesCorrectArtist(){
    Artist artistOne = artistOne();
    artistDao.add(artistOne);
    Artist artistTwo = artistTwo();
    artistDao.add(artistTwo);

    artistDao.deleteById(artistOne.getId());
    assertEquals(1, artistDao.getAll().size());

  }

  @Test
  public void clearAll_clearsAllArtists(){
    Artist artistOne = artistOne();
    artistDao.add(artistOne);
    Artist artistTwo = artistTwo();
    artistDao.add(artistTwo);

    artistDao.clearAll();
    assertEquals(0, artistDao.getAll().size());
  }
}
