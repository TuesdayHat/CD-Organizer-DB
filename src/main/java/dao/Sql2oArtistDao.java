package dao;

import models.Artist;
import org.sql2o.Sql2o;

public class Sql2oArtistDao implements ArtistDao {
  private final Sql2o sql2o;
  public Sql2oArtistDao(Sql2o sql2o) {this.sql2o = sql2o;}

  @Override
  public void add(Artist artist){

  }
}
