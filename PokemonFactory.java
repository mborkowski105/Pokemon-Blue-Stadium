public abstract class PokemonFactory {

    public static Pokemon createPokemon(String species) {
        Pokemon pokemon;

        switch (species) {
            case "Charizard": {
                pokemon = new Pokemon(species, Type.FIRE, Type.FLYING, 359, 264, 254, 268, 298);
                break;
            }
            case "Blastoise": {
                pokemon = new Pokemon(species, Type.WATER, null, 361, 264, 298, 268, 254);
                break;
            }
            case "Venusaur": {
                pokemon = new Pokemon(species, Type.GRASS, Type.POISON, 363, 262, 264, 298, 258);
                break;
            }
            case "Alakazam": {
                pokemon = new Pokemon(species, Type.PSYCHIC, null, 313, 198, 188, 368, 338);
                break;
            }
            case "Dragonite": {
                pokemon = new Pokemon(species, Type.DRAGON, Type.FLYING, 385, 366, 288, 298, 258);
                break;
            }
            case "Snorlax": {
                pokemon = new Pokemon(species, Type.NORMAL, null, 523, 318, 228, 228, 158);
                break;
            }
            case "Gyarados": {
                pokemon = new Pokemon(species, Type.WATER, Type.FLYING, 393, 348, 256, 298, 260);
                break;
            }
            case "Chansey": {
                pokemon = new Pokemon(species, Type.NORMAL, null, 703, 108, 108, 308, 198);
                break;
            }
            case "Jolteon": {
                pokemon = new Pokemon(species, Type.ELECTRIC, null,363, 308, 228, 218, 358);
                break;
            }
            case "Flareon": {
                pokemon = new Pokemon(species, Type.FIRE, null, 333, 358, 218, 318, 228);
                break;
            }
            case "Vaporeon": {
                pokemon = new Pokemon(species, Type.WATER, null,463, 228, 218, 318, 228);
                break;
            }
            case "Gengar": {
                pokemon = new Pokemon(species, Type.GHOST, Type.POISON, 323, 228, 218, 358, 318);
                break;
            }
            default: {
                return null;
            }
        }

        return pokemon;
    }
}
