//FREEZE CODE BEGIN
package UnitTests;
import code.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class P2Tests {
    @Test
    public void personImplementsInterfaces(){
        assertTrue( "Person should implement both interfaces",
                Susceptible.class.isAssignableFrom(Person.class)
                && Movable.class.isAssignableFrom(Person.class));
    }

    @Test
    public void tryInfectTest(){
        //Please note that there is no randomness in the test cases
        Disease d1 = new Disease("D1",.8, 30);

        assertTrue("Remember that the infection probability value should be inclusive." +
                "\nRead it as x% change of being infected",
                d1.tryInfect());

        Disease d2 = new Disease("D2",.01, 30);
        assertFalse("Remember that the infection probability value should be inclusive." +
                "\nRead it as x% change of being infected",
                d2.tryInfect());

    }

    @Test
    public void personInfect() {
        Person p = new Person(new Point(0, 0), "Joe");
        Disease d1 = new Disease("D1", .99, 30);
        if (!p.infect(d1) || p.getCurrentDisease() == null) {
            fail("The person should be infected");
        }

        Disease d2 = new Disease("D1", .99, 30);
        assertFalse("The person was already infected with D1, you cannot " +
                "infect people with a new disease", p.infect(d2));

    }
    @Test
    public void immuneTest(){
        Disease d1 = new Disease("D1", .99, 30);
        Person immunePerson = new Person(new Point(0,0), "House");
        immunePerson.setImmune();

        assertTrue("The person should be immune",
                immunePerson.isImmune());

        assertFalse("You cannot infect an immune person",immunePerson.infect(d1));
    }

    @Test
    public void forceInfectTest(){
        Person p = new Person(new Point(0,0), "Joe");
        Disease d1 = new Disease("D1",.0, 30);
        p.forceInfection(d1);
        assertSame("The person should be infected even with an " +
                "infection probability of 0.00. \nForceInfect ignores probabilities", p.getCurrentDisease(), d1);


        Person immunePerson = new Person(new Point(0,0), "House");
        immunePerson.setImmune();
        immunePerson.forceInfection(d1);
        assertSame("The person should be infected. \nForceInfect ignores immunity",
                immunePerson.getCurrentDisease(),d1);

    }

    @Test
    public void getPosition(){
        Person p = new Person(new Point(7,3), "Joe");
        Point startPosition = p.getPosition();
        if(!startPosition.equals(new Point(7,3))){
            fail("Get position should've returned a point at (7,3)");
        }
    }

    @Test
    public void addToTracingAndGetOthers(){
        Person p = new Person(new Point(0,0), "Joe");
        Disease d1 = new Disease("D1",.99, 30);
        p.forceInfection(d1);

        //Create some targets;
        List<Susceptible> folks = new ArrayList<>();

        folks.add(new Person(new Point(0,0), "Ann"));
        folks.add(new Person(new Point(4,23), "Mike"));
        folks.add(new Person(new Point(0,43), "Larry"));
        folks.add(new Person(new Point(0,0), "Kacey"));

        for (Susceptible person : folks){
            p.addToTracing(person);
        }

        assertEquals("We added 4 people to the contact tracing. " +
                "\nWe should be able to retrieve them", folks, p.getOthersInfected());


    }

    @Test
    public void toStringTest(){
        Person p = new Person(new Point(0,0), "Jane");
        assertEquals("toString should only return the person's name",
            p.toString(),"Jane" );
    }
}
//FREEZE CODE END