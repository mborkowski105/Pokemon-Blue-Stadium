package personalproject.example.pbs.dao;

import org.springframework.stereotype.Repository;
import personalproject.example.pbs.model.Trainer;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class TrainerDataAccessService implements TrainerDao {

    private static List<Trainer> DB = new ArrayList<Trainer>();

    public int addTrainer(UUID id, Trainer trainer){
        DB.add(new Trainer(id, trainer.getName(),null, null, null, null, null, null));
        return 1;
    }

    public List<Trainer> selectAllTrainers(){
        return DB;
    }
}
