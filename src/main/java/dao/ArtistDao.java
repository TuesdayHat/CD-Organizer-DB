package dao;

import models.Artist;

import java.util.List;

public interface ArtistDao {

  //Create
  void add(Artist artist);

  //Read
  Artist findById(int id);
  List<Artist> getAll();

  //Update
  void update(int id, String newName);

  //Delete
  void deleteById(int id);
  void clearAll();
}
