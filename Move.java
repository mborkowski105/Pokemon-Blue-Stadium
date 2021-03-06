public class Move {
    // instance variables
    private final String NAME;
    private final Type TYPE;
    private final int BASE_POWER;
    private final int ACCURACY;
    private final int MAX_PP;
    private int currentPp;
    private final int PRIORITY;

    private final SecondaryEffect SECONDARY_EFFECT;

    /**
     * Constructor for objects of class Move
     */

    public Move(String name, Type type, int basePower, int accuracy, int maxPp, int priority, SecondaryEffect secondaryEffect) {
        this.NAME = name;
        this.TYPE = type;
        this.BASE_POWER = basePower;
        this.ACCURACY = accuracy;
        this.MAX_PP = maxPp;
        this.currentPp = maxPp;
        this.PRIORITY = priority;
        this.SECONDARY_EFFECT = secondaryEffect;
    }

    public String getName() {
        return NAME;
    }
    public Type getType() {
        return TYPE;
    }
    public int getBasePower() {
        return BASE_POWER;
    }
    public int getAccuracy() {
        return ACCURACY;
    }
    public int getMaxPp() {
        return MAX_PP;
    }
    public int getCurrentPp() {
        return currentPp;
    }
    public int getPriority() {
        return PRIORITY;
    }
    public SecondaryEffect getSecondaryEffect() {
        return SECONDARY_EFFECT;
    }
    public void decrementCurrentPp() {
        if (currentPp > 0) {
            currentPp --;
        }
    }
    public void resetCurrentPp() {
        currentPp = MAX_PP;
    }
}
