package personalproject.example.pbs.model;

public class SecondaryStatus implements SecondaryEffect {

    private final int PROBABILITY;
    private final boolean TARGET_SELF;
    private final Status STATUS;

    public SecondaryStatus(int probability, boolean targetSelf, Status status){
        this.PROBABILITY = probability;
        this.TARGET_SELF = targetSelf;
        this.STATUS = status;
    }

    public int getProbability(){
        return PROBABILITY;
    }
    public boolean targetSelf(){
        return TARGET_SELF;
    }
    public Status getStatus(){
        return STATUS;
    }

}
