package personalproject.example.pbs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Trainer {
    private UUID id;
    private String name;
    private List<Pokemon> team;
    private Pokemon activePokemon;

    // need to account for null Pokemon values here (if team only has 3 Pokes rather than 6)
    public Trainer(UUID id, String name, Pokemon pokemon1, Pokemon pokemon2, Pokemon pokemon3, Pokemon pokemon4, Pokemon pokemon5, Pokemon pokemon6){
        this.id = id;
        this.name = name;

        team = new ArrayList<Pokemon>();
        team.add(pokemon1);
        team.add(pokemon2);
        team.add(pokemon3);
        team.add(pokemon4);
        team.add(pokemon5);
        team.add(pokemon6);

        activePokemon = team.get(0);
    }

    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Pokemon getActivePokemon(){
        return activePokemon;
    }
    public List<Pokemon> getTeam() { return team; }
    public void setActivePokemon(Pokemon p){
        activePokemon = p;
    }
}