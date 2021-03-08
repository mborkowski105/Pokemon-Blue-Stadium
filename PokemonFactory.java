public abstract class PokemonFactory {

    public static Pokemon createPokemon(String species, String move1Name, String move2Name, String move3Name, String move4Name) {
        Pokemon pokemon;

        Move move1 = MoveFactory.createMove(move1Name);
        Move move2 = MoveFactory.createMove(move2Name);
        Move move3 = MoveFactory.createMove(move3Name);
        Move move4 = MoveFactory.createMove(move4Name);

        switch (species) {
            case "VENUSAUR": {
                pokemon = new Pokemon(species, Type.GRASS, Type.POISON, 363, 262, 264, 298, 258, 80, move1, move2, move3, move4);
                break;
            }
            case "CHARIZARD": {
                pokemon = new Pokemon(species, Type.FIRE, Type.FLYING, 359, 264, 254, 268, 298, 100, move1, move2, move3, move4);
                break;
            }
            case "BLASTOISE": {
                pokemon = new Pokemon(species, Type.WATER, null, 361, 264, 298, 268, 254, 78, move1, move2, move3, move4);
                break;
            }
            case "RAICHU": {
                pokemon = new Pokemon(species, Type.ELECTRIC, null, 323, 278,208,278,298, 100, move1, move2, move3, move4);
                break;
            }
            case "NIDOQUEEN": {
                pokemon = new Pokemon(species, Type.GROUND, Type.POISON, 383, 262, 272, 248, 250, 76, move1, move2, move3, move4);
                break;
            }
            case "NIDOKING": {
                pokemon = new Pokemon(species, Type.GROUND, Type.POISON, 365, 282, 252, 248, 268, 85, move1, move2, move3, move4);
                break;
            }
            case "DUGTRIO": {
                pokemon = new Pokemon(species, Type.GROUND, null, 273,258, 198, 238, 338, 120, move1, move2, move3, move4);
                break;
            }
            case "ALAKAZAM": {
                pokemon = new Pokemon(species, Type.PSYCHIC, null, 313, 198, 188, 368, 338, 120, move1, move2, move3, move4);
                break;
            }
            case "GOLEM": {
                pokemon = new Pokemon(species, Type.ROCK, Type.GROUND, 363, 318, 358, 208, 188, 45, move1, move2, move3, move4);
                break;
            }
            case "GENGAR": {
                pokemon = new Pokemon(species, Type.GHOST, Type.POISON, 323, 228, 218, 358, 318, 130, move1, move2, move3, move4);
                break;
            }
            case "CHANSEY": {
                pokemon = new Pokemon(species, Type.NORMAL, null, 703, 108, 108, 308, 198, 50, move1, move2, move3, move4);
                break;
            }
            case "STARMIE": {
                pokemon = new Pokemon(species, Type.WATER, Type.PSYCHIC,323,248, 268, 298, 328, 115, move1, move2, move3, move4);
                break;
            }
            case "GYARADOS": {
                pokemon = new Pokemon(species, Type.WATER, Type.FLYING, 393, 348, 256, 298, 260, 81, move1, move2, move3, move4);
                break;
            }
            case "VAPOREON": {
                pokemon = new Pokemon(species, Type.WATER, null,463, 228, 218, 318, 228, 65, move1, move2, move3, move4);
                break;
            }
            case "JOLTEON": {
                pokemon = new Pokemon(species, Type.ELECTRIC, null,363, 308, 228, 218, 358, 130, move1, move2, move3, move4);
                break;
            }
            case "FLAREON": {
                pokemon = new Pokemon(species, Type.FIRE, null, 333, 358, 218, 318, 228, 65, move1, move2, move3, move4);
                break;
            }
            case "SNORLAX": {
                pokemon = new Pokemon(species, Type.NORMAL, null, 523, 318, 228, 228, 158, 30, move1, move2, move3, move4);
                break;
            }
            case "ZAPDOS": {
                pokemon = new Pokemon(species, Type.ELECTRIC, Type.FLYING,383, 278, 268, 348, 298, 100, move1, move2, move3, move4);
            }
            case "DRAGONITE": {
                pokemon = new Pokemon(species, Type.DRAGON, Type.FLYING, 385, 366, 288, 298, 258, 80, move1, move2, move3, move4);
                break;
            }
            default: {
                return null;
            }
        }

        return pokemon;
    }
}
