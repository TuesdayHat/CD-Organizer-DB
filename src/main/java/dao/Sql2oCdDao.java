package dao;

import models.CD;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
}
