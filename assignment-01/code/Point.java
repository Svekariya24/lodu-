package code;
//FREEZE CODE BEGIN
/**
 * Represents a point in 2D space.
 * This class makes no assumption on where the origin is and what is the axis orientation
 * Colours are represented as a String
 *
 * @author Juliano Franz
 */
public class Point {
    private int xCoordinate;
    private int yCoordinate;


    /**
     * Creates a point at the origin (0,0) and colour set to black
     */
    public Point(){
        xCoordinate = 0;
        yCoordinate = 0;
    }

    /**
     * Creates a new point at a given coordinate and colour
     * @param xCoordinate the X coordinate [-999,999]
     * @param yCoordinate the Y coordinate [-999,999]
     */
    public Point(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }


    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Translates a point in 2D space given an X and Y delta
     * @param xChange the amount to translate in the X direction
     * @param yChange the amount to translate in the Y direction
     */
    public void translate(int xChange, int yChange){
        xCoordinate += xChange;
        yCoordinate += yChange;
    }

    /**
     * Calculates the distance between two points in space
     * @param p the other point
     * @return the distance. Please note that are Point is unit-less
     */
    public double distance(Point p){
        double result = Math.sqrt(Math.pow(p.xCoordinate - xCoordinate,2) +
                Math.pow(p.yCoordinate - yCoordinate,2));

        return result;
    }

    @Override
    public String toString(){
        //X: x, Y: y, Colour: colour
        String result = "X: " + xCoordinate + " Y: " + yCoordinate;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //if both objects are in the same memory address, they ARE the same
        if(this == obj){
            return true;
        }

        //if obj is not a Point they cannot be equal
        if(!(obj instanceof Point)){
            return false;
        }

        Point other = (Point)obj;
        if(xCoordinate == other.xCoordinate &&
                yCoordinate == other.yCoordinate ){
            return true;
        }

        return false;
    }

    public Point copy(){
        return new Point(xCoordinate,yCoordinate);

    }
}
//FREEZE CODE END