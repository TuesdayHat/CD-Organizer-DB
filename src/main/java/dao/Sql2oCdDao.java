package dao;

import models.Artist;
import models.CD;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCdDao implements CdDao {
  private final Sql2o sql2o;
  public Sql2oCdDao(Sql2o sql2o) {this.sql2o = sql2o;}

  @Override
  public void add(CD album) {
    String sql = "INSERT INTO cds (title, artistid) VALUES (:title, :artistid)";
    try(Connection con = sql2o.open()){
      int id = (int) con.createQuery(sql)
//              .addParameter("title", album.getTitle())
//              .addParameter("artistid", album.getArtistId())
//              .addColumnMapping("TITLE", "description")
//              .addColumnMapping("ARTISTID", "categoryId")
//              .executeUpdate()
//              .getKey();
              .bind(album)
              .addParameter("title", album.getTitle())
              .addParameter("artistid", album.getArtistId())
              .executeUpdate()
              .getKey();
      album.setId(id);
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public CD findById(int id){
    String sql = "SELECT * FROM cds WHERE id = :id";
    try (Connection con = sql2o.open()){
      return con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(CD.class);
    }
  }

  @Override
  public List<CD> getAll(){
    String sql = "SELECT * FROM cds";
    try (Connection con = sql2o.open()){
      return con.createQuery(sql)
              .executeAndFetch(CD.class);
    }
  }

  @Override
  public void update(int id, String newTitle){
    String sql = "UPDATE cds SET title = :title WHERE id=:id";
    try(Connection con = sql2o.open()){
      con.createQuery(sql)
              .addParameter("title", newTitle)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public void deleteById(int id) {
    String sql = "DELETE cds WHERE id = :id";
    try(Connection con = sql2o.open()){
      con.createQuery(sql)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }
}
