package personalproject.example.pbs.model;

public class SecondaryOhko implements SecondaryEffect {

    private int PROBABILITY;
    private boolean TARGET_SELF;

    public SecondaryOhko(int probability, boolean targetSelf){
        this.PROBABILITY = probability;
        this.TARGET_SELF = targetSelf;
    }

    public int getProbability() {
        return PROBABILITY;
    }

    public boolean targetSelf() {
        return TARGET_SELF;
    }
}