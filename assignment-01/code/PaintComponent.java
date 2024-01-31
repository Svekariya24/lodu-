//FREEZE CODE BEGIN
package code;
import java.awt.*;
import java.util.List;

public class PaintComponent extends Component {

    private List<Susceptible> paintTargets;

    public PaintComponent(List<Susceptible> paintTargets){
        this.paintTargets = paintTargets;
    }

    @Override
    public void paint(Graphics g) {
         Graphics2D graphics = (Graphics2D)g;

        for (Susceptible p : paintTargets){
            if(p.isImmune()){
                graphics.setPaint(Color.ORANGE);
                graphics.fillOval(
                        p.getPosition().getxCoordinate()-5,
                        p.getPosition().getyCoordinate()-5,
                        10,10);
            }

            else if(p.getCurrentDisease() == null)
            {
                graphics.setPaint(Color.green);


                graphics.fillOval(
                        p.getPosition().getxCoordinate()-5,
                        p.getPosition().getyCoordinate()-5,
                        10,10);
            }
            else
            {


                    graphics.setPaint(Color.red);
                    graphics.fillOval(p.getPosition().getxCoordinate() - 5,
                            p.getPosition().getyCoordinate() - 5,
                            10, 10);

                    int infectionRadius = p.getCurrentDisease().getInfectionRadius();

                    graphics.drawOval(p.getPosition().getxCoordinate() - infectionRadius / 2,
                            p.getPosition().getyCoordinate() - infectionRadius / 2,
                            infectionRadius, infectionRadius);


            }
        }


    }
}
//FREEZE CODE END
