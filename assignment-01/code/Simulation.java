package code;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private final int MAX_MOVEMENT_STEP = 50;
    private final int GRID_SIZE;
    private List<Susceptible> simulationEntities;

    public Simulation(int numberOfSusceptible, int numberOfInfected, Disease disease, int gridSize) {
        simulationEntities = new ArrayList<>(numberOfSusceptible);
        Random rnd = RandomNumbers.rnd;
        GRID_SIZE = gridSize;

        for (int i = 0; i < numberOfSusceptible; i++) {


            Point coordinate = new Point(rnd.nextInt(gridSize),rnd.nextInt(gridSize));

            Person p = new Person(coordinate,String.format("Person %d",i));
            simulationEntities.add(p);
        }

        for (int i = 0; i < numberOfInfected; i++) {
            simulationEntities.get(i).forceInfection(disease);
        }
    }

    public List<Susceptible> getEntities(){
        return simulationEntities;
    }

    public void stepSimulation(){
        for (Susceptible source : simulationEntities) {
            if (source instanceof Movable) {
                Movable object = (Movable) source;
                object.move(MAX_MOVEMENT_STEP);
                Point newPosition = source.getPosition();
                //Check for bounds
                if(newPosition.getxCoordinate()<0){
                    newPosition.setxCoordinate(GRID_SIZE);
                } else if (newPosition.getxCoordinate()>GRID_SIZE+1) {
                    newPosition.setxCoordinate(0);
                }

                if(newPosition.getyCoordinate()<0){
                    newPosition.setyCoordinate(GRID_SIZE);
                } else if (source.getPosition().getyCoordinate()>GRID_SIZE+1) {
                    newPosition.setyCoordinate(0);
                }
                source.setPosition(newPosition);

            }
            if (source.getCurrentDisease() != null) {
                double infectionRadius = source.getCurrentDisease().getInfectionRadius();
                for (Susceptible entity : simulationEntities) {
                    if (source != entity && entity.getCurrentDisease() == null) {
                        Point hostEntityPosition = source.getPosition();
                        double distanceBtwEntities = entity.getPosition().distance(hostEntityPosition);
                        if (distanceBtwEntities < infectionRadius) {
                            Disease hostDiesease = source.getCurrentDisease();
                            entity.infect(hostDiesease);
                        }
                    }
                }
            }

        }
        // for (Susceptible entity : simulationEntities){
        //   if (entity instanceof Movable){
            
        //   }
        // }
        //Start your solution here
// WRITE YOUR CODE HERE
    }
}
