package dao;

import models.Artist;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oArtistDao implements ArtistDao {
  private final Sql2o sql2o;
  public Sql2oArtistDao(Sql2o sql2o) {this.sql2o = sql2o;}

  @Override
  public void add(Artist artist){
    String sql = "INSERT INTO artists (name) VALUES (:name)";
    try (Connection con = sql2o.open()) {
      int id = (int) con.createQuery(sql)
              .bind(artist)
              .executeUpdate()
              .getKey();
      artist.setId(id);
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public Artist findById(int id){
    String sql = "SELECT * FROM artists WHERE id = :id";
    try (Connection con = sql2o.open()){
      return con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Artist.class);
    }
  }

  @Override
  public List<Artist> getAll(){
    String sql = "SELECT * FROM artists";
    try (Connection con = sql2o.open()) {
      return con.createQuery(sql)
              .executeAndFetch(Artist.class);
    }
  }

  @Override
  public void update(int id, String newName){
    String sql = "UPDATE artists SET name = :name WHERE id=:id";
    try(Connection con = sql2o.open()){
      con.createQuery(sql)
              .addParameter("name", newName)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public void deleteById(int id){
    String sql = "DELETE FROM artists WHERE id = :id";
    try(Connection con = sql2o.open()){
      con.createQuery(sql)
              .addParameter("id", id)
              .executeUpdate();
    }catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }
}
