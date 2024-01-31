package code;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VizMain extends  JPanel{

    private static final int GRID_SIZE = 1000;

    public static void main(String[] args) {

        //Set up the sim
        Disease disease = new Disease("BirdFlu", 0.3, 50,20);
        //Simulation sim = new Simulation(10, 1, disease, 0, 50);
        Simulation sim = new Simulation(100, 10, disease, GRID_SIZE);


        //Set up our rendering frame
        JFrame frame = new JFrame(String.format("Disease: %s, prob: %f, radius: %d",
                disease.getName(),disease.getInfectionProbability(),disease.getInfectionRadius()));
        JButton SimStep = new JButton("StepSim");
        PaintComponent paintComponent = new PaintComponent(sim.getEntities());

        SimStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sim.stepSimulation();
                paintComponent.repaint();
            }
        });

        frame.add(paintComponent);//,BorderLayout.CENTER);
        frame.add(SimStep, BorderLayout.PAGE_END);
        frame.setSize(GRID_SIZE,GRID_SIZE);

        paintComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Susceptible susceptible : sim.getEntities()){
                    Point mouse = e.getPoint();

                    double distance = mouse.distance(new Point(susceptible.getPosition().getxCoordinate(),
                            susceptible.getPosition().getyCoordinate()));

                    if(distance<5 && susceptible instanceof Person){
                        Person p = (Person)susceptible;
                        List<Susceptible> othersInfected = p.getOthersInfected();
                        System.out.println(String.format("This person infected %d people (directly or indirectly)\nList: %s",
                                p.getOthersInfected().size(),p.getOthersInfected()));
                    }

                }
            }
        });

        frame.setVisible(true);


    }


}
