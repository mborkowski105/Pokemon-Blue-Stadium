package personalproject.example.pbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personalproject.example.pbs.dao.TrainerDao;
import personalproject.example.pbs.model.Trainer;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerDao trainerDao;

    @Autowired
    public TrainerService(TrainerDao trainerDao){
        this.trainerDao = trainerDao;
    }

    public int addTrainer(Trainer trainer){
        return trainerDao.addTrainer(trainer);
    }

    public List<Trainer> getAllTrainers(){
        return trainerDao.selectAllTrainers();
    }
}
