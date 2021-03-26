package personalproject.example.pbs.model;

public class SecondaryStatModifier implements SecondaryEffect {

    private final int PROBABILITY;
    private final boolean TARGET_SELF;
    private final String STAT;
    private final int MODIFIER;

    // I think String stat seems like an unsafe way to pass around the stat to be modified - could be separated into classes
    public SecondaryStatModifier(int probability, boolean targetSelf, String stat, int modifier){
        this.PROBABILITY = probability;
        this.TARGET_SELF = targetSelf;
        this.STAT = stat;
        this.MODIFIER = modifier;
    }

    public int getProbability(){
        return PROBABILITY;
    }
    public boolean targetSelf(){
        return TARGET_SELF;
    }
    public String getStat(){
        return STAT;
    }
    public int getModifier(){
        return MODIFIER;
    }

}
