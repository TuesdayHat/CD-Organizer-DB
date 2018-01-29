package dao;

import models.CD;
import org.sql2o.Sql2o;

public class Sql2oCdDao implements CdDao {
  private final Sql2o sql2o;
  public Sql2oCdDao(Sql2o sql2o) {this.sql2o = sql2o;}

  @Override
  public void add(CD album) {

  }
}
