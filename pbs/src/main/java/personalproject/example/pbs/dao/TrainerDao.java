package personalproject.example.pbs.dao;

import java.util.UUID;
import personalproject.example.pbs.models.Trainer;
import java.util.List;

public interface TrainerDao {
    int addTrainer(UUID id, Trainer trainer);

    default int addTrainer(Trainer trainer){
        UUID id = UUID.randomUUID();
        return addTrainer(id, trainer);
    }

    List<Trainer> selectAllTrainers();
}
