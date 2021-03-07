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
        display();

        Pokemon pokemon1 = TRAINER2.getActivePokemon();
        Pokemon pokemon2 = TRAINER1.getActivePokemon();
        Move move = TRAINER2.getActivePokemon().getMove1();

        //extract below into some sort of messaging function?
        System.out.println(pokemon1.getSpecies() + " used " + move.getName() + "!");

        // need to include preliminary check for 0x effective to avoid secondary effect taking place
        boolean miss = calculateMiss(move.getAccuracy());
        if (miss == false) {
            //check to see if move is of non-damaging category
            if (move.getBasePower() > 0) {
                int damage = attack(pokemon1, move, pokemon2);
            }

            //secondary effects
            //split these up into steps!
            if (move.getSecondaryEffect() instanceof SecondaryStatus){
                if (calculateMiss(move.getSecondaryEffect().getProbability()) == false){
                    //below line looks messy
                    pokemon2.setStatus1((((SecondaryStatus) move.getSecondaryEffect()).getStatus()));
                    System.out.println("The opponent has a nasty case of " + pokemon2.getStatus1());
                    //implement counter for sleep or toxic
                }
            }
            //secondaryStatModifier section - check target of move!
            else if (move.getSecondaryEffect() instanceof SecondaryStatModifier){
                if (calculateMiss(move.getSecondaryEffect().getProbability()) == false){
                    int change = ((SecondaryStatModifier) move.getSecondaryEffect()).getModifier();
                    //below line looks messy
                    switch (((SecondaryStatModifier) move.getSecondaryEffect()).getStat()){
                        case "ATK": {
                            if (move.getSecondaryEffect().targetSelf() == true){
                                pokemon1.changeAtkStageMultiplierBy(change);
                            }
                            else {
                                pokemon2.changeAtkStageMultiplierBy(change);
                            }
                            break;
                        }
                        case "DEF": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                pokemon1.changeDefStageMultiplierBy(change);
                            }
                            else {
                                pokemon2.changeDefStageMultiplierBy(change);
                            }
                            break;
                        }
                        case "SPC": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                pokemon1.changeSpcStageMultiplierBy(change);
                            }
                            else {
                                pokemon2.changeSpcStageMultiplierBy(change);
                            }
                            break;
                        }
                        case "SPD": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                pokemon1.changeSpdStageMultiplierBy(change);
                            }
                            else {
                                pokemon2.changeSpdStageMultiplierBy(change);
                            }
                            break;
                        }
                    }
                    // make messages an attribute of SecondaryEffect
                    System.out.println(pokemon2.getSpecies() + "'s " + ((SecondaryStatModifier) move.getSecondaryEffect()).getStat() + " was changed");
                    //implement counter for sleep or toxic
                    System.out.println(pokemon2.getSpecies() + "'s SPC: " + pokemon2.getCurrentSpc() + " / " + pokemon2.getSpc());
                }
            }
            // OHKO section - self-destruct or OHKO moves
            // implement support for the OHKO moves like Guillotine or Fissure
            else if (move.getSecondaryEffect() instanceof SecondaryOHKO){
                if (move.getSecondaryEffect().targetSelf() == true){
                    pokemon1.damage(pokemon1.getCurrentHp());
                    System.out.println(pokemon1.getSpecies() + " self-destructed!");
                }
            }
            // Recovery section
            else if (move.getSecondaryEffect() instanceof SecondaryRecovery){
                if (pokemon1.getCurrentHp() * 2 >= pokemon1.getHp()){
                    pokemon1.resetHp();
                }
                else {
                    pokemon1.heal((int) pokemon1.getHp() / 2);
                }
                System.out.println(pokemon1.getSpecies() + " recovered HP!");
            }
        }

        display();
    }

    public int attack(Pokemon attackingPokemon, Move move, Pokemon defendingPokemon) {
        // below parameters for DamageCalculator seem a little unintuitive - reorder/ reconfigure?
        int damage = 0;
        if (calculateMiss(move.getAccuracy()) == false) {
            damage = DamageCalculator.calculate(attackingPokemon, move, defendingPokemon);
        }
        TRAINER1.getActivePokemon().damage(damage);

        return damage;
    }

    // split these up into util class? or maybe damagecalculator isn't necessary in individual class?
    public boolean calculateMiss(int probability){
        if (Math.random() * 100.0 < (double)probability){
            return false;
        }
        else {
            // move below message to somewhere else - "attack missed" will still display if secondary effect misses
            System.out.println("Attack missed!");
            return true;
        }
    }

    public void display(){
        // displays species and HP
        System.out.println(TRAINER1.getActivePokemon().getSpecies() + ": " + TRAINER1.getActivePokemon().getCurrentHp() + " / " + TRAINER1.getActivePokemon().getHp());
        System.out.println(TRAINER1.getActivePokemon().getStatus1());
        System.out.println(TRAINER2.getActivePokemon().getSpecies() + ": " + TRAINER2.getActivePokemon().getCurrentHp() + " / " + TRAINER2.getActivePokemon().getHp());
        System.out.println(TRAINER2.getActivePokemon().getStatus1());
    }

    public void modifyStat(Pokemon pokemon, SecondaryStatModifier secondaryStatModifier){

    }
}
