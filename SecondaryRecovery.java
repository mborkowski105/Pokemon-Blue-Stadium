public class SecondaryRecovery implements SecondaryEffect {

    private int PROBABILITY;
    private boolean TARGET_SELF;

    public SecondaryRecovery(int probability, boolean targetSelf){
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