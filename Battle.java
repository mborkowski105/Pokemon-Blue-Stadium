import java.util.Scanner;

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
        turn();
        display();
    }

    public int attack(Pokemon attackingPokemon, Move move, Pokemon defendingPokemon) {
        // below parameters for DamageCalculator seem a little unintuitive - reorder/ reconfigure?
        int damage = 0;
        if (calculateMiss(move.getAccuracy()) == false) {
            damage = DamageCalculator.calculate(attackingPokemon, move, defendingPokemon);
        }
        defendingPokemon.damage(damage);

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
        System.out.println(TRAINER1.getActivePokemon().getStatus1() == null ? "" : TRAINER1.getActivePokemon().getStatus1().getAbbreviated());
        System.out.println(TRAINER2.getActivePokemon().getSpecies() + ": " + TRAINER2.getActivePokemon().getCurrentHp() + " / " + TRAINER2.getActivePokemon().getHp());
        System.out.println(TRAINER2.getActivePokemon().getStatus1() == null ? "" : TRAINER2.getActivePokemon().getStatus1().getAbbreviated());
    }

    public void handleTrainerAction(Trainer activeTrainer, Move move, Trainer opponentTrainer) {
        Pokemon activePokemon = activeTrainer.getActivePokemon();
        Pokemon opponentPokemon = opponentTrainer.getActivePokemon();

        //extract below into some sort of messaging function?
        System.out.println(activePokemon.getSpecies() + " used " + move.getName() + "!");

        // need to include preliminary check for 0x effective to avoid secondary effect taking place
        boolean miss = calculateMiss(move.getAccuracy());
        if (miss == false) {
            //check to see if move is of non-damaging category
            if (move.getBasePower() > 0) {
                int damage = attack(activePokemon, move, opponentPokemon);
            }

            //secondary effects
            //split these up into steps!
            //Status moves
            if (move.getSecondaryEffect() instanceof SecondaryStatus) {
                if (calculateMiss(move.getSecondaryEffect().getProbability()) == false) {
                    //below line looks messy
                    //REST status
                    if (((SecondaryStatus) move.getSecondaryEffect()).getStatus() == Status.REST) {
                        activePokemon.setStatus1(Status.REST);
                        activePokemon.resetHp();
                        System.out.println(activePokemon.getSpecies() + " went to sleep!");
                    }
                    // other STATUSes
                    else {
                        opponentPokemon.setStatus1((((SecondaryStatus) move.getSecondaryEffect()).getStatus()));
                        System.out.println("The opponent has a nasty case of " + opponentPokemon.getStatus1());
                        //implement counter for sleep or toxic
                    }
                }
            }
            //secondaryStatModifier section - check target of move!
            else if (move.getSecondaryEffect() instanceof SecondaryStatModifier) {
                if (calculateMiss(move.getSecondaryEffect().getProbability()) == false) {
                    int change = ((SecondaryStatModifier) move.getSecondaryEffect()).getModifier();
                    //below line looks messy
                    switch (((SecondaryStatModifier) move.getSecondaryEffect()).getStat()) {
                        case "ATK": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                activePokemon.changeAtkStageMultiplierBy(change);
                            } else {
                                opponentPokemon.changeAtkStageMultiplierBy(change);
                            }
                            break;
                        }
                        case "DEF": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                activePokemon.changeDefStageMultiplierBy(change);
                            } else {
                                opponentPokemon.changeDefStageMultiplierBy(change);
                            }
                            break;
                        }
                        case "SPC": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                activePokemon.changeSpcStageMultiplierBy(change);
                            } else {
                                opponentPokemon.changeSpcStageMultiplierBy(change);
                            }
                            break;
                        }
                        case "SPD": {
                            if (move.getSecondaryEffect().targetSelf() == true) {
                                activePokemon.changeSpdStageMultiplierBy(change);
                            } else {
                                opponentPokemon.changeSpdStageMultiplierBy(change);
                            }
                            break;
                        }
                    }
//                    // make messages an attribute of SecondaryEffect
//                    System.out.println(opponentPokemon.getSpecies() + "'s " + ((SecondaryStatModifier) move.getSecondaryEffect()).getStat() + " was changed");
//                    //implement counter for sleep or toxic
//                    System.out.println(opponentPokemon.getSpecies() + "'s SPC: " + opponentPokemon.getCurrentSpc() + " / " + opponentPokemon.getSpc());
                }
            }
            // OHKO section - self-destruct or OHKO moves
            // implement support for the OHKO moves like Guillotine or Fissure
            else if (move.getSecondaryEffect() instanceof SecondaryOHKO) {
                if (move.getSecondaryEffect().targetSelf() == true) {
                    activePokemon.damage(activePokemon.getCurrentHp());
                    activePokemon.setStatus1(Status.FAINT);
                    System.out.println(activePokemon.getSpecies() + " self-destructed!");
                }
            }
            // Recovery section
            else if (move.getSecondaryEffect() instanceof SecondaryRecovery) {
                if (activePokemon.getCurrentHp() * 2 >= activePokemon.getHp()) {
                    activePokemon.resetHp();
                } else {
                    activePokemon.heal((int) activePokemon.getHp() / 2);
                }
                System.out.println(activePokemon.getSpecies() + " recovered HP!");
            }
        }
    }

    public void turn(){
        // split this off into individual method
        System.out.println("What'll it be, trainer 1?");
        System.out.println(TRAINER1.getActivePokemon().getMove1().getName() + " | " + TRAINER1.getActivePokemon().getMove2().getName() + " | " + TRAINER1.getActivePokemon().getMove3().getName() + " | " + TRAINER1.getActivePokemon().getMove4().getName());
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Move move1 = null;
        for (Move m: TRAINER1.getActivePokemon().getMoves()){
            if (m.getName().equals(input)){
                move1 = m;
            }
        }

        System.out.println("What'll it be, trainer 2?");
        System.out.println(TRAINER2.getActivePokemon().getMove1().getName() + " | " + TRAINER2.getActivePokemon().getMove2().getName() + " | " + TRAINER2.getActivePokemon().getMove3().getName() + " | " + TRAINER2.getActivePokemon().getMove4().getName());
        input = scanner.nextLine();

        Move move2 = null;
        for (Move m: TRAINER2.getActivePokemon().getMoves()){
            if (m.getName().equals(input)){
                move2 = m;
            }
        }

        if (TRAINER1.getActivePokemon().getCurrentSpd() > TRAINER2.getActivePokemon().getCurrentSpd()){
            handleTrainerAction(TRAINER1, move1, TRAINER2);
            // handle fainted pokemon - split this into different method
            if (TRAINER1.getActivePokemon().getStatus1() == Status.FAINT){
                System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
            }
            else if (TRAINER2.getActivePokemon().getStatus1() == Status.FAINT){
                System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
            }
            else {
                handleTrainerAction(TRAINER2, move2, TRAINER1);
                // again, split into different method
                if (TRAINER1.getActivePokemon().getStatus1() == Status.FAINT){
                    System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
                }
                else if (TRAINER2.getActivePokemon().getStatus1() == Status.FAINT){
                    System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
                }
            }
        }
        else {
            handleTrainerAction(TRAINER2, move2, TRAINER1);
            // handle fainted pokemon - split this into different method
            if (TRAINER1.getActivePokemon().getStatus1() == Status.FAINT){
                System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
            }
            else if (TRAINER2.getActivePokemon().getStatus1() == Status.FAINT){
                System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
            }
            else {
                handleTrainerAction(TRAINER1, move1, TRAINER2);
                // again, split into different method
                if (TRAINER1.getActivePokemon().getStatus1() == Status.FAINT){
                    System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
                }
                else if (TRAINER2.getActivePokemon().getStatus1() == Status.FAINT){
                    System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
                }
            }
        }
    }

    public void modifyStat(Pokemon pokemon, SecondaryStatModifier secondaryStatModifier){

    }
}
