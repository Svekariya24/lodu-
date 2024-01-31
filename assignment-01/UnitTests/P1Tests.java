//FREEZE CODE BEGIN
package UnitTests;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;

import code.Susceptible;
import code.Movable;


public class P1Tests {

    @Test
    public void susceptibleInterfaceMethods(){
        Method[] methods = Susceptible.class.getDeclaredMethods();
        String[] expectedMethods = {"infect","forceInfection","getCurrentDisease","setImmune","isImmune","getPosition","setPosition"};

        for (String method :expectedMethods){
            boolean found = false;
            for (Method declaredMethod : methods){
                if(method.equals(declaredMethod.getName())){
                    found = true;
                    break;
                }
            }
            if(!found){
                fail("Method: " + method + " not found!");

            }
        }
        return;
    }

    @Test
    public void movableMethods(){
        Method[] methods = Movable.class.getDeclaredMethods();

        String[] expectedMethods = {"move"};

        for (String method :expectedMethods){
            boolean found = false;
            for (Method declaredMethod : methods){
                if(method.equals(declaredMethod.getName())){
                    found = true;
                    break;
                }
            }
            if(!found){
                fail("Method: " + method + " not found!");
            }
        }
        return;
    }


}
//FREEZE CODE END