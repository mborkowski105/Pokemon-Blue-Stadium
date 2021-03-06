import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private List<Pokemon> team;
    private Pokemon activePokemon;

    // need to account for null Pokemon values here (if team only has 3 Pokes rather than 6)
    public Trainer(Pokemon pokemon1, Pokemon pokemon2, Pokemon pokemon3, Pokemon pokemon4, Pokemon pokemon5, Pokemon pokemon6){
        team = new ArrayList<Pokemon>();
        team.add(pokemon1);
        team.add(pokemon2);
        team.add(pokemon3);
        team.add(pokemon4);
        team.add(pokemon5);
        team.add(pokemon6);

        activePokemon = team.get(0);
    }

    public Pokemon getActivePokemon(){
        return activePokemon;
    }

    // implement check within Trainer to see if all Pokemon are fainted
}
