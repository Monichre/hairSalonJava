import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)){
      return false;
    }else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName());
    } 
  }
  public Stylist(String name){
    this.name = name;  
  }
  public String getName(){
    return name;
  }
  public int getId(){
    return id;
  }
}
