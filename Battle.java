import java.util.Scanner;

public class Battle {
    private final Trainer TRAINER1;
    private final Trainer TRAINER2;
    public Battle(Trainer trainer1, Trainer trainer2){
        TRAINER1 = trainer1;
        TRAINER2 = trainer2;
    }

    public void battle(){
        while (turn() == false){
            System.out.println("");
        }
    }

    // returns the total amount of damage done by an attack, given the attacking and defending Pokemon, and conditions
    public int attack(Pokemon attackingPokemon, Move move, Pokemon defendingPokemon) {
        int damage = 0;
        if (calculateMiss(move.getAccuracy()) == false) {
            damage = DamageCalculator.calculate(attackingPokemon, move, defendingPokemon);
        }
        defendingPokemon.damage(damage);

        return damage;
    }

    // split these up into util class? or maybe damagecalculator isn't necessary in individual class?
    // returns true if the attack misses
    // get rid of this method
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

    public boolean checkForPokemonFaint(Trainer trainer){
        if (trainer.getActivePokemon().getStatus1() == Status.FAINT){
            return true;
        }
        return false;
    }

    public boolean checkForTeamLoss(Trainer trainer){
        for (Pokemon pokemon : trainer.getTeam()){
            if (pokemon != null) {
                if (pokemon.getStatus1() != Status.FAINT) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display(){
        // displays species and HP
        System.out.println(TRAINER1.getActivePokemon().getSpecies() + ": " + TRAINER1.getActivePokemon().getCurrentHp() + " / " + TRAINER1.getActivePokemon().getHp());
        System.out.println(TRAINER1.getActivePokemon().getStatus1().getAbbreviated());
        System.out.println(TRAINER2.getActivePokemon().getSpecies() + ": " + TRAINER2.getActivePokemon().getCurrentHp() + " / " + TRAINER2.getActivePokemon().getHp());
        System.out.println(TRAINER2.getActivePokemon().getStatus1().getAbbreviated());
    }

    // returns false if the Pokemon is unencumbered by confusion, true if it hits itself and cannot attack
    public boolean handlePokemonConfusion(Pokemon pokemon){
        if (pokemon.getStatus2() == Status.CONFUSED) {
            //random chance to snap out of confusion within 2-5 turns
            if (pokemon.getStatus2Counter() > 4 || (pokemon.getStatus2Counter() > 0 && Math.random() * 100.0 < 25.0)) {
                pokemon.setStatus2(Status.HEALTHY);
                pokemon.resetStatus2Counter();
                System.out.println(pokemon.getSpecies() + "'s CONFUSED no more!");
                return false;
            }
            else {
                System.out.println(pokemon.getSpecies() + " is CONFUSED!");
                if (Math.random() * 100.0 < 50.0) {
                    System.out.println("It hurt itself in CONFUSION!");
                    pokemon.damage(DamageCalculator.calculateConfusionDamage(pokemon));
                    pokemon.incrementStatus2Counter();
                    return true;
                }
                else {
                    pokemon.incrementStatus2Counter();
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }

    // returns true if the Pokemon is able to attack
    public boolean handlePokemonMove(Trainer activeTrainer, Move move, Trainer opponentTrainer) {
        Pokemon activePokemon = activeTrainer.getActivePokemon();
        Pokemon opponentPokemon = opponentTrainer.getActivePokemon();

        if (handlePokemonConfusion(activePokemon) == false) {
            //extract below into some sort of messaging function?
            System.out.println(activePokemon.getSpecies() + " used " + move.getName() + "!");

            // need to include preliminary check for 0x effective to avoid secondary effect taking place
            boolean miss = calculateMiss(move.getAccuracy());
            if (miss == false) {
                //check to see if move is of non-damaging category
                if (move.getBasePower() > 0) {
                    int damage = attack(activePokemon, move, opponentPokemon);
                    // Recoil section
                    if (move.getSecondaryEffect() instanceof SecondaryRecoil) {
                        System.out.println(activePokemon.getSpecies() + "'s hit with RECOIL!");
                        activePokemon.damage((int) damage / 4);
                    }
                }
            }
            move.decrementCurrentPp();
            return true;
        }

        return false;
    }

    // prevent switching in a fainted Pokemon
    public void handlePokemonSwitch(Trainer trainer){
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

        for (Pokemon pokemon : trainer.getTeam()){
            if (pokemon != null) {
                if (pokemon.getSpecies().equals(input)) {
                    trainer.setActivePokemon(pokemon);
                    System.out.println("Go! " + pokemon.getSpecies() + "\n");
                }
            }
        }
    }

    public void handleSecondaryEffects(Pokemon activePokemon, Move move, Pokemon opponentPokemon){
        //Status moves
        if (move.getSecondaryEffect() instanceof SecondaryStatus) {
            Status status = ((SecondaryStatus) move.getSecondaryEffect()).getStatus();
            //REST status
            if (status == Status.REST) {
                if (activePokemon.getCurrentHp() == activePokemon.getHp()){
                    System.out.println(activePokemon.getSpecies() + " is already at full health!");
                }
                else {
                    activePokemon.setStatus1(Status.REST);
                    activePokemon.resetHp();
                    System.out.println(activePokemon.getSpecies() + " went to SLEEP!");
                }
            }
            //CONFUSION
            else if (status == Status.CONFUSED){
                if (opponentPokemon.getStatus2() == Status.HEALTHY && Math.random() * 100.0 <= move.getSecondaryEffect().getProbability()){
                    System.out.println(opponentPokemon.getSpecies() + " was CONFUSED! It may not be able to attack!");
                    opponentPokemon.setStatus2(Status.CONFUSED);
                }
            }
            // for other STATUSes, calculate probability
            // actually, should probably do this at the top of method
            // add in "But it failed!"
            else if (opponentPokemon.getStatus1() == Status.HEALTHY && Math.random() * 100.0 <= move.getSecondaryEffect().getProbability()) {
                opponentPokemon.setStatus1(status);
                if (status == Status.SLEEP){
                    System.out.println(opponentPokemon.getSpecies() + " was put to SLEEP!");
                    opponentPokemon.resetHyperBeamRecharge();
                }
                else if (status == Status.POISON){
                    System.out.println(opponentPokemon.getSpecies() + " was POISONED!");
                }
                else if (status == Status.BAD_POISON){
                    System.out.println(opponentPokemon.getSpecies() + " was badly POISONED!");
                }
                else if (status == Status.FREEZE){
                    System.out.println(opponentPokemon.getSpecies() + " was FROZEN solid!");
                    opponentPokemon.resetHyperBeamRecharge();
                }
                else if (status == Status.PARALYSIS){
                    System.out.println(opponentPokemon.getSpecies() + " was PARALYZED! It may not be able to attack!");
                }
                else if (status == Status.BURN){
                    System.out.println(opponentPokemon.getSpecies() + " was BURNT!");
                }
            }
        }

        //secondaryStatModifier section - check target of move!
        if (move.getSecondaryEffect() instanceof SecondaryStatModifier) {
            if (calculateMiss(move.getSecondaryEffect().getProbability()) == false) {
                String stat = ((SecondaryStatModifier) move.getSecondaryEffect()).getStat();
                int change = ((SecondaryStatModifier) move.getSecondaryEffect()).getModifier();
                boolean targetSelf = move.getSecondaryEffect().targetSelf();

                //change message to "But it failed!" if method returns false/ stageMultiplier can't go any higher/lower
                System.out.println((targetSelf ? activePokemon.getSpecies() : opponentPokemon.getSpecies()) + "'s " + stat + " was changed!");

                switch (stat) {
                    case "ATK": {
                        if (targetSelf == true) {
                            activePokemon.changeAtkStageMultiplierBy(change);
                        }
                        else {
                            opponentPokemon.changeAtkStageMultiplierBy(change);
                        }
                        break;
                    }
                    case "DEF": {
                        if (targetSelf == true) {
                            activePokemon.changeDefStageMultiplierBy(change);
                        }
                        else {
                            opponentPokemon.changeDefStageMultiplierBy(change);
                        }
                        break;
                    }
                    case "SPC": {
                        if (targetSelf == true) {
                            activePokemon.changeSpcStageMultiplierBy(change);
                        }
                        else {
                            opponentPokemon.changeSpcStageMultiplierBy(change);
                        }
                        break;
                    }
                    case "SPD": {
                        if (targetSelf == true) {
                            activePokemon.changeSpdStageMultiplierBy(change);
                        }
                        else {
                            opponentPokemon.changeSpdStageMultiplierBy(change);
                        }
                        break;
                    }
                }
//                    // make messages an attribute of SecondaryEffect
//
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
                System.out.println(activePokemon.getSpecies() + " recovered HP!");
                activePokemon.resetHp();
            } else if (activePokemon.getCurrentHp() < activePokemon.getHp()) {
                System.out.println(activePokemon.getSpecies() + " recovered HP!");
                activePokemon.heal((int) activePokemon.getHp() / 2);
            }
            else {
                System.out.println(activePokemon.getSpecies() + " is already at full health!");
            }
        }

        //Hyper Beam
        else if (move.getSecondaryEffect() instanceof SecondaryHyperBeam){
            activePokemon.setHyperBeamRecharge();
        }
    }

    // returns false if the Pokemon passed in is determined unable to attack
    public boolean runPreAttackTasks(Pokemon pokemon){
        //Check for sleep, increment sleep counter if Pokemon remains asleep, or wake the Pokemon up
        //I'm following Stadium rules rather than original R/B/Y rules here - 3 (or 4) turn max for sleep, 1 in 3 chance of waking up, cannot attack same turn
        if (pokemon.getHyperBeamRecharge() == true){
            System.out.println(pokemon.getSpecies() + " needs to recharge!");
            pokemon.resetHyperBeamRecharge();
            return false;
        }
        else if (pokemon.getStatus1() == Status.SLEEP){
            if (pokemon.getStatus1Counter() == 3 || Math.random() * 100.0 > 66.6){
                System.out.println(pokemon.getSpecies() + " woke up!");
                pokemon.setStatus1(Status.HEALTHY);
                pokemon.resetStatus1Counter();
            }
            else {
                System.out.println(pokemon.getSpecies() + " is fast asleep!");
                pokemon.incrementStatus1Counter();
            }

            return false;
        }
        // in R/B/Y, for Rest, one turn asleep, then one turn waking up without attacking
        else if (pokemon.getStatus1() == Status.REST){
            if (pokemon.getStatus1Counter() == 1){
                System.out.println(pokemon.getSpecies() + " woke up!");
                pokemon.setStatus1(Status.HEALTHY);
                pokemon.resetStatus1Counter();
            }
            else {
                System.out.println(pokemon.getSpecies() + " is fast asleep!");
                pokemon.incrementStatus1Counter();
            }

            return false;
        }
        //Paralysis check - 25% chance of full paralysis
        else if (pokemon.getStatus1() == Status.PARALYSIS){
            if (Math.random() * 100.0 < 25.0){
                System.out.println(pokemon.getSpecies() + "'s fully PARALYZED!");
                return false;
            }
            else {
                return true;
            }
        }
        //Freeze check
        //I'm putting in G/S/C rules of 10% chance of defrosting - being permanently frozen solid is unfair and broken I think
        else if (pokemon.getStatus1() == Status.FREEZE){
            if (Math.random() * 100.0 < 10.0){
                System.out.println(pokemon.getSpecies() + " was defrosted!");
                pokemon.setStatus1(Status.HEALTHY);
            }
            else {
                System.out.println(pokemon.getSpecies() + " is FROZEN solid!");
            }
        }
        // put in confusion check, Hyper Beam check

        return true;
    }

    public void runPostAttackTasks(Pokemon activePokemon, Move move, Pokemon opponentPokemon){
        if (activePokemon.getStatus1() == Status.POISON){
            System.out.println(activePokemon.getSpecies() + "'s hurt by POISON!");
            activePokemon.damage(activePokemon.getHp() / 16);
        }
        if (activePokemon.getStatus1() == Status.BAD_POISON){
            System.out.println(activePokemon.getSpecies() + "'s hurt by POISON!");
            int damage = activePokemon.damage((activePokemon.getHp() / 16) * (1 + activePokemon.getStatus1Counter()));
            if (damage < ((int) 15.0 * Math.floor(activePokemon.getHp() / 16))){
                activePokemon.incrementStatus1Counter();
            }
        }
    }

    // returns true if a player as won
    public boolean turn(){
        display();

        // split this off into individual method
        System.out.println("What'll it be, trainer 1?");
        System.out.println(TRAINER1.getActivePokemon().getMove1().getName() + " | " + TRAINER1.getActivePokemon().getMove2().getName() + " | " + TRAINER1.getActivePokemon().getMove3().getName() + " | " + TRAINER1.getActivePokemon().getMove4().getName() + "\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Move move1 = null;
        for (Move move: TRAINER1.getActivePokemon().getMoves()){
            if (move.getName().equals(input)){
                move1 = move;
            }
        }

        System.out.println("What'll it be, trainer 2?");
        System.out.println(TRAINER2.getActivePokemon().getMove1().getName() + " | " + TRAINER2.getActivePokemon().getMove2().getName() + " | " + TRAINER2.getActivePokemon().getMove3().getName() + " | " + TRAINER2.getActivePokemon().getMove4().getName() + "\n");
        input = scanner.nextLine();

        Move move2 = null;
        for (Move move: TRAINER2.getActivePokemon().getMoves()){
            if (move.getName().equals(input)){
                move2 = move;
            }
        }

        // this is getting convoluted with the web of if/else - split off into different method?
        // make active Pokemon into variables?
        if (TRAINER1.getActivePokemon().getCurrentSpd() > TRAINER2.getActivePokemon().getCurrentSpd()){
            if (runPreAttackTasks(TRAINER1.getActivePokemon()) == true) {
                if (handlePokemonMove(TRAINER1, move1, TRAINER2) == true) {
                    if (move1.getSecondaryEffect() != null) {
                        if (move1.getSecondaryEffect().targetSelf() == true && checkForPokemonFaint(TRAINER1) == false) {
                            handleSecondaryEffects(TRAINER1.getActivePokemon(), move1, TRAINER2.getActivePokemon());
                        } else if (move1.getSecondaryEffect().targetSelf() == false && checkForPokemonFaint(TRAINER2) == false) {
                            handleSecondaryEffects(TRAINER1.getActivePokemon(), move1, TRAINER2.getActivePokemon());
                        }
                    }
                }
                runPostAttackTasks(TRAINER1.getActivePokemon(), move1, TRAINER2.getActivePokemon());
            }

            if (checkForPokemonFaint(TRAINER1) == false && checkForPokemonFaint(TRAINER2) == false){
                if (runPreAttackTasks(TRAINER2.getActivePokemon()) == true) {
                    if (handlePokemonMove(TRAINER2, move2, TRAINER1) == true) {
                        if (move2.getSecondaryEffect() != null) {
                            if (move2.getSecondaryEffect().targetSelf() == true && checkForPokemonFaint(TRAINER1) == false) {
                                handleSecondaryEffects(TRAINER2.getActivePokemon(), move2, TRAINER1.getActivePokemon());
                            } else if (move2.getSecondaryEffect().targetSelf() == false && checkForPokemonFaint(TRAINER2) == false) {
                                handleSecondaryEffects(TRAINER2.getActivePokemon(), move2, TRAINER1.getActivePokemon());
                            }
                        }
                    }
                    runPostAttackTasks(TRAINER2.getActivePokemon(), move2, TRAINER1.getActivePokemon());
                    if (checkForPokemonFaint(TRAINER1) == true){
                        System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
                        if (checkForTeamLoss(TRAINER1) == false) {
                            handlePokemonSwitch(TRAINER1);
                        }
                        else {
                            System.out.println("\n Trainer 2 wins!");
                            return true;
                        }
                    }
                    if (checkForPokemonFaint(TRAINER2) == true){
                        System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
                        if (checkForTeamLoss(TRAINER2) == false) {
                            handlePokemonSwitch(TRAINER2);
                        }
                        else {
                            System.out.println("\n Trainer 1 wins!");
                            return true;
                        }
                    }
                }
            }
            else if (checkForPokemonFaint(TRAINER1) == true){
                System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
                if (checkForTeamLoss(TRAINER1) == false) {
                    handlePokemonSwitch(TRAINER1);
                }
                else {
                    System.out.println("\n Trainer 2 wins!");
                    return true;
                }
            }
            else if (checkForPokemonFaint(TRAINER2) == true){
                System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
                if (checkForTeamLoss(TRAINER2) == false) {
                    handlePokemonSwitch(TRAINER2);
                }
                else {
                    System.out.println("\n Trainer 1 wins!");
                    return true;
                }
            }
        }
        // if Trainer 2's Pokemon is faster
        else {
            // maybe make arguments more consistent - can be confusing when to pass in Trainer or Pokemon
            if (runPreAttackTasks(TRAINER2.getActivePokemon()) == true) {
                if (handlePokemonMove(TRAINER2, move2, TRAINER1) == true) {
                    if (move2.getSecondaryEffect() != null) {
                        if (move2.getSecondaryEffect().targetSelf() == true && checkForPokemonFaint(TRAINER1) == false) {
                            handleSecondaryEffects(TRAINER2.getActivePokemon(), move2, TRAINER1.getActivePokemon());
                        } else if (move2.getSecondaryEffect().targetSelf() == false && checkForPokemonFaint(TRAINER2) == false) {
                            handleSecondaryEffects(TRAINER2.getActivePokemon(), move2, TRAINER1.getActivePokemon());
                        }
                    }
                }
                runPostAttackTasks(TRAINER2.getActivePokemon(), move2, TRAINER1.getActivePokemon());
            }

            if (checkForPokemonFaint(TRAINER1) == false && checkForPokemonFaint(TRAINER2) == false){
                if (runPreAttackTasks(TRAINER1.getActivePokemon()) == true) {
                    if (handlePokemonMove(TRAINER1, move1, TRAINER2) == true) {
                        if (move1.getSecondaryEffect() != null) {
                            if (move1.getSecondaryEffect().targetSelf() == true && checkForPokemonFaint(TRAINER1) == false) {
                                handleSecondaryEffects(TRAINER1.getActivePokemon(), move1, TRAINER2.getActivePokemon());
                            } else if (move1.getSecondaryEffect().targetSelf() == false && checkForPokemonFaint(TRAINER2) == false) {
                                handleSecondaryEffects(TRAINER1.getActivePokemon(), move1, TRAINER2.getActivePokemon());
                            }
                        }
                    }
                    runPostAttackTasks(TRAINER1.getActivePokemon(), move1, TRAINER2.getActivePokemon());
                    if (checkForPokemonFaint(TRAINER1) == true){
                        System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
                        if (checkForTeamLoss(TRAINER1) == false) {
                            handlePokemonSwitch(TRAINER1);
                        }
                        else {
                            System.out.println("\n Trainer 2 wins!");
                            return true;
                        }
                    }
                    if (checkForPokemonFaint(TRAINER2) == true){
                        System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
                        if (checkForTeamLoss(TRAINER2) == false) {
                            handlePokemonSwitch(TRAINER2);
                        }
                        else {
                            System.out.println("\n Trainer 1 wins!");
                            return true;
                        }
                    }
                }
            }
            else if (checkForPokemonFaint(TRAINER1) == true){
                System.out.println(TRAINER1.getActivePokemon().getSpecies() + " fainted!");
                if (checkForTeamLoss(TRAINER1) == false) {
                    handlePokemonSwitch(TRAINER1);
                }
                else {
                    System.out.println("\n Trainer 2 wins!");
                    return true;
                }
            }
            else if (checkForPokemonFaint(TRAINER2) == true){
                System.out.println(TRAINER2.getActivePokemon().getSpecies() + " fainted!");
                if (checkForTeamLoss(TRAINER2) == false) {
                    handlePokemonSwitch(TRAINER2);
                }
                else {
                    System.out.println("\n Trainer 1 wins!");
                    return true;
                }
            }
        }

        return false;
    }

    public void modifyStat(Pokemon pokemon, SecondaryStatModifier secondaryStatModifier){

    }
}
