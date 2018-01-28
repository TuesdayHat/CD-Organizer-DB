package dao;

import models.Artist;

public interface ArtistDao {

  //Create
  void add(Artist artist);

  //Read
  Artist findById(int id);

  //Update

  //Delete
}
