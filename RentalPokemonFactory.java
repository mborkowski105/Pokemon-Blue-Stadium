public abstract class RentalPokemonFactory {
    public static Pokemon createPokemon(String species) {

        Pokemon pokemon;

        switch (species) {
            case "CHARIZARD": {
                pokemon = PokemonFactory.createPokemon("CHARIZARD", "FIRE BLAST", "SWORDS DANCE", "EARTHQUAKE", "BODY SLAM");
                break;
            }
            case "NIDOKING": {
                pokemon = PokemonFactory.createPokemon("NIDOKING", "EARTHQUAKE", "BLIZZARD", "THUNDER", "BODY SLAM");
                break;
            }
            case "GOLEM": {
                pokemon = PokemonFactory.createPokemon("GOLEM", "EARTHQUAKE", "ROCK SLIDE", "EXPLOSION", "FIRE BLAST");
                break;
            }
            default: {
                pokemon = null;
            }
        }

        return pokemon;
    }
}
