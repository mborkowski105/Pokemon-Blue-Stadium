
/**
 * Write a description of class Move here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Move {
    // instance variables
    private final int BASE_POWER;
    private final int ACCURACY;
    private final int MAX_PP;

    /**
     * Constructor for objects of class Move
     */
    public Move(int basePower, int accuracy, int maxPp) {
        // initialise instance variables
        this.BASE_POWER = basePower;
        this.ACCURACY = accuracy;
        this.MAX_PP = maxPp;
    }

    public int getBasePower() {
        // put your code here
        return BASE_POWER;
    }
    
    public int getAccuracy() {
        return ACCURACY;
    }
    
    public int getMaxPp() {
        return MAX_PP;
    }
}
