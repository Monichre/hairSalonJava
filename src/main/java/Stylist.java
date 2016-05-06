import org.sql2o.*;
import java.util.List;

public class Stylist {
  private String name;
  private int client_id;
  private int id;

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)){
      return false;
    }else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
      this.getId() == newStylist.getId();
    } 
  }


  public Stylist(String name){
    this.name = name;  
  }
  public String getName(){ // TEST THIS
    return name;
  }
  public int getId(){ // TEST THIS
    return id;
  }
  public static List<Stylist> all() {
    String sql = "SELECT id, name, client_id FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
}
