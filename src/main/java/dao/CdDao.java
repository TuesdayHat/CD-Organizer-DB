package dao;

import models.CD;

import java.util.List;

public interface CdDao {

  //Create
  void add(CD album);

  //Read
  CD findById(int id);
  List<CD> getAll();

  //Update
  void update(int id, String newTitle);

  //Delete
//  void deleteById(int id);
//  void clearAll();
}