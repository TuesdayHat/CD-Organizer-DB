package models;

public class Artist {
  private String name;
  private int id;

  public Artist(String name) {
    this.name = name;
  }

  public String getName(){return this.name;}
  public void setName(String nName){this.name = nName;}

  public int getId(){return this.id;}
  public void setId(int nId){this.id = nId;}

  @Override
  public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Artist artist = (Artist) o;

    if (id != artist.id) return false;
    return name.equals(artist.name);
  }

  @Override
  public int hashCode(){
    int result = this.name.hashCode();
    result = 31*result+id;
    return result;
  }
}
