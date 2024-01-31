package code;
import java.util.Random;
import java.util.*;

// WRITE YOUR CODE HERE
public class Person implements Susceptible, Movable{
  private Disease CurrentDisease;
  private String name;
  private Point position;
  private List<Susceptible> otherInfected;
  private boolean isImmune;

  public Person(Point position , String name){
    this.position = position;
    this.name = name;
    otherInfected = new ArrayList<Susceptible>();
    isImmune = false;
  }

  public void addToTracing(Susceptible person){
    otherInfected.add(person);
  }

  public List<Susceptible> getOthersInfected(){
    return otherInfected;
  }

  public String toString(){
    return name;
  }
  
  // neeed to modifed 
  public boolean infect(Disease disease){
    if(!isImmune() && disease.tryInfect()){
      this.CurrentDisease = disease;
      setImmune();
      return true;
    }else{
      return false;
    }
  }


  public void forceInfection(Disease disease){
    this.CurrentDisease = disease;
    setImmune();
  }

  public Disease getCurrentDisease(){
//    if (CurrentDisease == null && !isImmune()){
//      return null;
//    }else{
//      return CurrentDisease;
//    }
    return CurrentDisease;
  }

  public void setImmune(){
    isImmune = true;
  }

  public boolean isImmune(){
    return isImmune;
  }

  public Point getPosition(){
    return position;
  }

  public void setPosition(Point position){
    this.position = position;
  }

  public void move(int step){
    Random rand = RandomNumbers.rnd;
    if(position != null){
      int x = rand.nextInt(step);
      int y = rand.nextInt(step);
      position.translate(x,y);
    }
  }
}  