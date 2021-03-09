import java.util.Scanner;

public class Battle {
    private final Trainer TRAINER1;
    private final Trainer TRAINER2;
    private boolean trainer1Win;
    private boolean trainer2Win;
    public Battle(Trainer trainer1, Trainer trainer2){
        TRAINER1 = trainer1;
        TRAINER2 = trainer2;
        trainer1Win = false;
        trainer2Win = false;
    }

    public void battle(){
        while (checkForVictory() == false){
            display();
            turn();
        }

        if (trainer1Win){
            System.out.println("\n Trainer 1 has won!");
        }
        else if (trainer2Win) {
            System.out.println("\n Trainer 2 has won!");
        }
    }

    public int attack(Pokemon attackingPokemon, Move move, Pokemon defendingPokemon) {
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

    public boolean checkForFaintedPokemon(){
        if (TRAINER1.getActivePokemon().getStatus1() == Status.FAINT){
            System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
            if (checkForVictory() == false){
                promptForPokemonSwitch(TRAINER1);
            }
            return true;
        }
        else if (TRAINER2.getActivePokemon().getStatus1() == Status.FAINT){
            System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
            if (checkForVictory() == false){
                promptForPokemonSwitch(TRAINER2);
            }
            return true;
        }
        return false;
    }

    public boolean checkForVictory(){
        trainer1Win = true;
        trainer2Win = true;

        for (Pokemon p : TRAINER1.getTeam()){
            if (p != null) {
                if (p.getStatus1() != Status.FAINT) {
                    trainer2Win = false;
                }
            }
        }
        for (Pokemon p : TRAINER2.getTeam()){
            if (p != null) {
                if (p.getStatus1() != Status.FAINT) {
                    trainer1Win = false;
                }
            }
        }

        if (trainer1Win){
            return true;
        }
        else if (trainer2Win){
            return true;
        }
        else {
            return false;
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

    public void promptForPokemonSwitch(Trainer trainer){
        Scanner scanner = new Scanner(System.in);
        //prevent user from switching in fainted pokemon!
        System.out.println("\n Switch in a new pokemon: \n");
        for (Pokemon p : trainer.getTeam()){
            if (p != null) {
                System.out.print(p.getSpecies() + " | ");
            }
        }
        System.out.println("\n");
        String input = scanner.nextLine();

        for (Pokemon p : trainer.getTeam()){
            if (p != null) {
                if (p.getSpecies().equals(input)) {
                    trainer.setActivePokemon(p);
                    System.out.println("Go! " + p.getSpecies() + "\n");
                }
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
            if (checkForFaintedPokemon() == false){
                handleTrainerAction(TRAINER2, move2, TRAINER1);
                checkForFaintedPokemon();
            }
        }
        else {
            handleTrainerAction(TRAINER2, move2, TRAINER1);
            // handle fainted pokemon - split this into different method
            if (checkForFaintedPokemon() == false){
                handleTrainerAction(TRAINER1, move1, TRAINER2);
                checkForFaintedPokemon();
            }
        }
    }

    public void modifyStat(Pokemon pokemon, SecondaryStatModifier secondaryStatModifier){

    }
}
