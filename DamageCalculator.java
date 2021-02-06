public abstract class DamageCalculator {
    public static int calculate(Move move, Pokemon attackingPokemon, Pokemon defendingPokemon){
        double stab = 1.0;
        double modifier = Type.getDamageModifier(move.getType(), defendingPokemon.getType1(), defendingPokemon.getType2());

        if (modifier > 1.0) {
            System.out.println("It's super effective! x" + modifier);
        }
        if (modifier < 1.0) {
            System.out.println("It's not very effective... x" + modifier);
        }

        if (attackingPokemon.getType1() == move.getType() || attackingPokemon.getType2() == move.getType()) {
            stab = 1.5;
        }

        return (int)((((42 * move.getBasePower() * (attackingPokemon.getCurrentSpc() / defendingPokemon.getCurrentSpc()))/ 50)+2)*(stab * modifier));
    }
}
