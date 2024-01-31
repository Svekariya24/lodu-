//FREEZE CODE BEGIN
package UnitTests;

import org.junit.Test;
import static org.junit.Assert.*;

import code.Disease;
import code.Simulation;
import code.Susceptible;

import java.util.LinkedList;
import java.util.List;

public class P3Tests {


    @Test
    public void S1() {
        //Let's try to run a simulation and check if we are infecting people correctly.
        Disease d = new Disease("Black Plague", 0.40, 40);
        Simulation sim = new Simulation(10, 3, d,  200);

        //We'll do 7 simulation steps and check who is infected.
        int steps = 7;
        System.out.println("Infected before running: ");
        for (Susceptible susceptible : sim.getEntities()) {
            if (susceptible.getCurrentDisease() != null)
                System.out.println(susceptible);
        }
        for (int i = 0; i < steps; i++) {
            sim.stepSimulation();
        }

        //After 7 steps we are expecting these folks to be infected.
        String[] infectedNames = {"Person 0" , "Person 1", "Person 2", "Person 3", "Person 4", "Person 7"};

        System.out.println("Infected after running: ");
        for (Susceptible susceptible : sim.getEntities()) {
            if (susceptible.getCurrentDisease() != null)
                System.out.println(susceptible);
        }

        //Okay, let's compare.
        //Iterate over all the people in the simulation and check each infected person against the expected list.
        //Let's populate a list of infected people
        List<String> actualInfected = new LinkedList<>();
        for (Susceptible susceptible : sim.getEntities()) {
            if (susceptible.getCurrentDisease() == null) {
                continue;
            }
            actualInfected.add(susceptible.toString());
        }
        //If the list is empty, we know we failed from the start
        assertTrue("Infected list is unchanged from the start condition, are you infecting people?",
                actualInfected.size() != 3);

        //Ok, list is not empty lets compare the names
        for (String actualName :
                actualInfected) {
            boolean found = false;

            for (String name : infectedNames) {
                if (name.equals(actualName)) {
                    found = true;
                    break;
                }
            }
            //If we did not find the person's name in the list, then we infected the wrong person.
            if (!found) {
                StringBuilder sb = new StringBuilder();

                sb.append("You simulation is not correct.\nExpected infected are: ");
                for (String name : infectedNames) {
                    sb.append(name).append(" ");
                }
                fail(sb.toString());
            }
        }

        //We'll follow the same procedure from before. We will keep running the simulation (4 more steps).
        //By then, we should have 3 people that are now immune (the first three that were infected)

        String[] immuneFolks = {"Person 0" , "Person 1", "Person 2"};
        //Let's run a bit more and test the immune folks
        steps = 4;
        for (int i = 0; i < steps; i++) {
            sim.stepSimulation();
        }

        System.out.println("Immune after running: ");
        for (Susceptible susceptible : sim.getEntities()) {
            if (susceptible.isImmune())
                System.out.println(susceptible);
        }

        List<String> actualImmune = new LinkedList<>();
        for (Susceptible susceptible : sim.getEntities()) {
            if (!susceptible.isImmune()) {
                continue;
            }
            actualImmune.add(susceptible.toString());
        }

        assertFalse("We should have 3 immune people",
                actualImmune.isEmpty());


        //Ok, list is not empty lets compare the names
        for (String actualName :
                actualImmune) {
            boolean found = false;

            for (String name : immuneFolks) {
                if (name.equals(actualName)) {
                    found = true;
                    break;
                }
            }
            //If we did not find the person's name in the list. Are we making them immune?
            if (!found) {
                StringBuilder sb = new StringBuilder();

                sb.append("You simulation is not correct.\nExpected immune are: ");
                for (String name : infectedNames) {
                    sb.append(name).append(" ");
                }
                fail(sb.toString());
            }
        }
    }
}
//FREEZE CODE END