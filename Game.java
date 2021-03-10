
public class Game
{
    public static void main(String[] args) {
//        Pokemon gengar = requestRentalPokemonCreation("Gengar");
//        Pokemon gyarados = requestRentalPokemonCreation("Gyarados");
//        System.out.print(Type.getDamageModifier(Type.NORMAL, gengar.getType1(), gengar.getType2()));
//        gengar.damage(100);
//        gengar.heal(50);
//        gengar.setStatus1(Status.SLEEP);
//        gengar.incrementStatus1Counter();
//        gengar.incrementSpcStageMultiplier(3);
//        Move thunderbolt = requestMoveCreation("THUNDERBOLT");

//        System.out.println(gengar.getSpecies() + " used " + thunderbolt.getName());
//
//        int damage = DamageCalculator.calculate(thunderbolt, gengar, gyarados);
//        gyarados.damage(damage);
//        System.out.println(damage + " damage!");
//        System.out.println("Gyarados: " + gyarados.getCurrentHp() + " / " + gyarados.getHp() + " HP");
//
//        System.out.println(gengar.getSpecies());
//        System.out.println(gengar.getStatus1() + ", " + gengar.getStatus1Counter() + " turn");
//        System.out.println(gengar.getCurrentSpc() + " / " + gengar.getSpc());

        Trainer trainer1 = new Trainer(requestNewRentalPokemon("VAPOREON"), requestNewRentalPokemon("CHARIZARD"), null, null, null, null);
        Trainer trainer2 = new Trainer(requestNewRentalPokemon("WEEZING"), requestNewRentalPokemon("NIDOKING"), null, null, null, null);

        Battle battle = new Battle(trainer1, trainer2);
        battle.battle();
    }

    public static Pokemon requestNewRentalPokemon(String species) {
        Pokemon pokemon;

        pokemon = RentalPokemonFactory.createPokemon(species);

        return pokemon;
    }

    public static Move requestNewMove(String name) {
        Move move;

        move = MoveFactory.createMove(name);

        return move;
    }
}
