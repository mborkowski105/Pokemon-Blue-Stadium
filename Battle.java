public class Battle {
    private final Trainer TRAINER1;
    private final Trainer TRAINER2;
    public Battle(Trainer trainer1, Trainer trainer2){
        TRAINER1 = trainer1;
        TRAINER2 = trainer2;
    }

    // remember to implement "switch" functionality - this may throw off current implementation of taking turns
    // switch parameters back to individual trainers, or split this method up into single turn method
    public void battle(){
        // displays species and HP
        System.out.println(TRAINER1.getActivePokemon().getSpecies() + ": " + TRAINER1.getActivePokemon().getCurrentHp() + " / " + TRAINER1.getActivePokemon().getHp());
        System.out.println(TRAINER2.getActivePokemon().getSpecies() + ": " + TRAINER2.getActivePokemon().getCurrentHp() + " / " + TRAINER2.getActivePokemon().getHp());

        System.out.println(TRAINER2.getActivePokemon().getSpecies() + " used " + TRAINER2.getActivePokemon().getMove4().getName() + "!");

        // below parameters for DamageCalculator seem a little unintuitive - reorder/ reconfigure?
        int damage = 0;
        if (calculateMiss(TRAINER2.getActivePokemon().getMove4().getAccuracy()) == false) {
            damage = DamageCalculator.calculate(TRAINER2.getActivePokemon().getMove4(), TRAINER2.getActivePokemon(), TRAINER1.getActivePokemon());
        }
        TRAINER1.getActivePokemon().damage(damage);

        //secondary effects
        //split these up into steps!
        if (TRAINER2.getActivePokemon().getMove4().getSecondaryEffect() instanceof SecondaryStatus){
            if (calculateMiss(TRAINER2.getActivePokemon().getMove4().getSecondaryEffect().getProbability()) == false){
                TRAINER1.getActivePokemon().setStatus1(((SecondaryStatus) TRAINER2.getActivePokemon().getMove4().getSecondaryEffect()).getStatus());
                System.out.println("The opponent has a nasty case of " + TRAINER1.getActivePokemon().getStatus1());
                //implement counter for sleep or toxic
            }
        }

        //displays species and HP post-attack
        System.out.println(TRAINER1.getActivePokemon().getSpecies() + ": " + TRAINER1.getActivePokemon().getCurrentHp() + " / " + TRAINER1.getActivePokemon().getHp());
        System.out.println(TRAINER1.getActivePokemon().getStatus1());
        System.out.println(TRAINER2.getActivePokemon().getSpecies() + ": " + TRAINER2.getActivePokemon().getCurrentHp() + " / " + TRAINER2.getActivePokemon().getHp());
        System.out.println(TRAINER2.getActivePokemon().getStatus1());
    }

    // split these up into util class?
    public boolean calculateMiss(int probability){
        if (Math.random() * 100.0 < (double)probability){
            return false;
        }
        else {
            System.out.println("Attack missed!");
            return true;
        }
    }

}
