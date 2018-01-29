package models;

import java.util.ArrayList;

public class CD {
  private String title;
  private String artist;
  private static ArrayList<CD> instances = new ArrayList<>();
  private int id;
  private int artistId;

//  public CD(String title, String artist){
//    this.title = title;
//    this.artist = artist;
//    instances.add(this);
//  }
public CD(String title){
  this.title = title;
  instances.add(this);
}

  public String getTitle() {
      return this.title;
  }
  public static ArrayList<CD> getInstances(){
      return instances;
  }

  public String getArtist() {
      return this.artist;
  }

  public int getId(){ return this.id; }
  public void setId(int nId) {this.id = nId;}
  public int getArtistId(){return this.artistId;}
  public void setArtistId(int id) {this.artistId = id;}

  @Override
  public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CD cd = (CD) o;

    if (id != cd.id) return false;
    if (artistId != cd.artistId) return false;
    return title.equals(cd.title);
  }

  @Override
  public int hashCode(){
    int result = this.title.hashCode();
    result = 31*result+id;
    result = 31*result+artistId;

    return result;
  }

}
