package personalproject.example.pbs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personalproject.example.pbs.model.Trainer;
import personalproject.example.pbs.service.TrainerService;
import java.util.List;

@RequestMapping("api/v1/trainer")

@RestController
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @PostMapping
    public void addTrainer(@RequestBody Trainer trainer){
        trainerService.addTrainer(trainer);
    }

    @GetMapping
    public List<Trainer> getAllTrainers(){
        return trainerService.getAllTrainers();
    }
}
