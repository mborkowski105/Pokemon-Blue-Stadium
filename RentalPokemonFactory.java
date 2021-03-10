public abstract class RentalPokemonFactory {
    public static Pokemon createPokemon(String species) {

        Pokemon pokemon;

        switch (species) {
            case "VENUSAUR": {
                pokemon = PokemonFactory.createPokemon("VENUSAUR", "RAZOR LEAF", "SLEEP POWDER", "BODY SLAM", "SWORDS DANCE");
                break;
            }
            case "CHARIZARD": {
                pokemon = PokemonFactory.createPokemon("CHARIZARD", "FIRE BLAST", "SWORDS DANCE", "EARTHQUAKE", "BODY SLAM");
                break;
            }
            case "BLASTOISE": {
                pokemon = PokemonFactory.createPokemon("BLASTOISE", "HYDRO PUMP", "BLIZZARD", "EARTHQUAKE", "REST");
                break;
            }
            case "RAICHU": {
                pokemon = PokemonFactory.createPokemon("RAICHU", "THUNDERBOLT", "SURF", "THUNDER WAVE", "AGILITY");
                break;
            }
            case "NIDOKING": {
                pokemon = PokemonFactory.createPokemon("NIDOKING", "EARTHQUAKE", "BLIZZARD", "THUNDER", "BODY SLAM");
                break;
            }
            case "DUGTRIO": {
                pokemon = PokemonFactory.createPokemon("DUGTRIO", "EARTHQUAKE", "ROCK SLIDE", "BODY SLAM", "TOXIC");
                break;
            }
            case "ALAKAZAM": {
                pokemon = PokemonFactory.createPokemon("ALAKAZAM", "PSYCHIC", "RECOVER", "THUNDER WAVE", "SEISMIC TOSS");
                break;
            }
            case "GOLEM": {
                pokemon = PokemonFactory.createPokemon("GOLEM", "EARTHQUAKE", "ROCK SLIDE", "EXPLOSION", "FIRE BLAST");
                break;
            }
            case "GENGAR": {
                pokemon = PokemonFactory.createPokemon("GENGAR", "THUNDERBOLT", "HYPNOSIS", "EXPLOSION", "NIGHT SHADE");
                break;
            }
            case "HYPNO": {
                pokemon = PokemonFactory.createPokemon("HYPNO", "PSYCHIC", "HYPNOSIS", "SEISMIC TOSS", "REST");
                break;
            }
            case "WEEZING": {
                pokemon = PokemonFactory.createPokemon("WEEZING", "TOXIC", "FIRE BLAST", "THUNDER", "EXPLOSION");
                break;
            }
            case "CHANSEY": {
                pokemon = PokemonFactory.createPokemon("CHANSEY", "THUNDERBOLT", "ICE BEAM", "SOFT-BOILED", "THUNDER WAVE");
                break;
            }
            case "STARMIE": {
                pokemon = PokemonFactory.createPokemon("STARMIE", "SURF", "THUNDERBOLT", "RECOVER", "THUNDER WAVE");
                break;
            }
            case "ELECTABUZZ": {
                pokemon = PokemonFactory.createPokemon("ELECTABUZZ", "THUNDERBOLT", "THUNDER WAVE", "PSYCHIC", "SEISMIC TOSS");
                break;
            }
            
            case "VAPOREON": {
                pokemon = PokemonFactory.createPokemon("VAPOREON", "SURF", "BLIZZARD", "ACID ARMOR", "REST");
                break;
            }
            case "SNORLAX": {
                pokemon = PokemonFactory.createPokemon("SNORLAX", "BODY SLAM", "AMNESIA", "REST", "ICE BEAM");
                break;
            }
            case "KABUTOPS": {
                pokemon = PokemonFactory.createPokemon("KABUTOPS", "SWORDS DANCE", "SLASH", "HYDRO PUMP", "BLIZZARD");
                break;
            }
            case "ZAPDOS": {
                pokemon = PokemonFactory.createPokemon("ZAPDOS", "THUNDERBOLT", "DRILL PECK", "THUNDER WAVE", "AGILITY");
                break;
            }
            default: {
                pokemon = null;
            }
        }

        return pokemon;
    }
}
