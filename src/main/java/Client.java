import org.sql2o.*;

public class Client {
  
  private String name;
  private int id;

  @Override
  public boolean equals(Object otherClient){
    if(!(otherClient instanceof Client)){
      return false;
    }else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName());
    } 
  }

  public Client(String name){
  	this.name = name;
  }

  public String getName(){
  	return name;
  }
  public int getId(){
  	return id;
  }

}
