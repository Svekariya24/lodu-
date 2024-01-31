package code;
import java.util.Random;

public class Disease {
  private String name;
  private double infectionProbability;
  private int infectionRadius;
  private int timeToCure;

  public Disease (String name, double infectionProbability, int infectionRadius){
    this.name = name;
    this.infectionProbability = infectionProbability;
    this.infectionRadius = infectionRadius;
    this.timeToCure = 10;
  }

  public Disease (String name, double infectionProbability, int infectionRadius, int timeToCure){
    this.name = name;
    this.infectionProbability = infectionProbability;
    this.infectionRadius = infectionRadius;
    this.timeToCure = timeToCure;
  }

  public String getName(){
    return name;
  }

  public double getInfectionProbability(){
    return infectionProbability;
  }

  public int getInfectionRadius(){
    return infectionRadius;
  }
  
  public boolean tryInfect(){
    Random rand = RandomNumbers.rnd;
    return rand.nextDouble() < infectionProbability;
  }
}
