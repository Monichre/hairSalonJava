import org.sql2o.*;



public class Patient {
  private String name;
  private String condition;

  @Override
  public boolean equals(Object otherPatient){
    if(!(otherPatient instanceof Patient)){
      return false;
    } else {
  Patient newPatient = (Patient) otherPatient;
  return this.getName().equals(newPatient.getName());
  }
}



  public Patient(String name, String condition){
    this.name = name;
    this.condition = condition;
  }

  public String getName(){
    return name;
  }
  public String getCondition(){
    return condition;
  }




}
