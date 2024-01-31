package code;
// WRITE YOUR CODE HERE
public interface Susceptible {
  // Method to infect with a disease
  public boolean infect(Disease disease);

  // Method to forcefully infect with a disease
  public void forceInfection(Disease disease);

  // Method to get the current disease
  public Disease getCurrentDisease();

  // Method to set the immune status
  public void setImmune();

  // Method to check if the entity is immune
  public boolean isImmune();

  // Method to get the position
  public Point getPosition();

  // Method to set the position
  public void setPosition(Point position);
}