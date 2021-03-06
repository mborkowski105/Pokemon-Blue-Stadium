// TODO turn abstract DamageCalculator class into interface (can be versioned by Gen 1-8 formulas)

public abstract class DamageCalculator {
    public static int calculate(Move move, Pokemon attackingPokemon, Pokemon defendingPokemon){
        double stab = 1.0;
        double typeModifier = Type.getDamageModifier(move.getType(), defendingPokemon.getType1(), defendingPokemon.getType2());
        double basePower = move.getBasePower();
        double attackingStat;
        double defendingStat;
        double level = 100.0;
        double randomNumber = (217.0 + (Math.random() * 38.0)) / 255.0;
        double criticalHitProbability = attackingPokemon.getBaseSpd() * 100.0 / 512.0;

        double multiplier = stab * typeModifier * randomNumber;

        if (move.getType().getDamageCategory() == "physical") {
            attackingStat = attackingPokemon.getCurrentAtk();
            defendingStat = defendingPokemon.getCurrentDef();
        }
        else {
            attackingStat = attackingPokemon.getCurrentSpc();
            defendingStat = defendingPokemon.getCurrentSpc();
        }

        if (typeModifier > 1.0) {
            System.out.println("It's super effective! x" + typeModifier);
        }
        else if (typeModifier == 0) {
            System.out.println("It doesn't effect");
        }
        else if (typeModifier < 1.0) {
            System.out.println("It's not very effective... x" + typeModifier);
        }

        if (attackingPokemon.getType1() == move.getType() || attackingPokemon.getType2() == move.getType()) {
            stab = 1.5;
            System.out.println("STAB bonus");
        }

        if (Math.random() * 100 < criticalHitProbability) {
            System.out.println("Critical hit!");
            level = level * 2;
        }

        return (int)((Math.min((int)((int)(((2.0 + (int)(0.4 * level)) * attackingStat * basePower) / defendingStat) / 50.0) , 997.0) + 2.0) * multiplier);
    }
}
