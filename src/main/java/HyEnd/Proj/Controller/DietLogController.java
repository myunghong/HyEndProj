package HyEnd.Proj.Controller;

import HyEnd.Proj.DTO.ActivityLogWithDetailsDTO;
import HyEnd.Proj.DTO.DietDTO;
import HyEnd.Proj.DTO.FoodDTO;
import HyEnd.Proj.DTO.RequestActivitiesDTO;
import HyEnd.Proj.Entity.ActivityLog;
import HyEnd.Proj.Entity.DietLog;
import HyEnd.Proj.Repository.DietLogRepository;
import HyEnd.Proj.Service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/diet-log")
public class DietLogController {

    private final DietService dietService;
    DietLogRepository dietLogRepository;

    @Autowired
    public DietLogController(DietLogRepository dietLogRepository, DietService dietService) {
        this.dietLogRepository = dietLogRepository;
        this.dietService = dietService;
    }

    // localhost:8080/diet-log/save
    @PostMapping("/save")
    @ResponseBody
    public DietLog saveDietLog(@RequestBody DietDTO dietDTO) {
        return dietService.saveDietLog(dietDTO);
    }

    @GetMapping("all")
    @ResponseBody
    public List<DietLog> findAll() {
        return dietLogRepository.findAll();
    }

    @GetMapping("id")
    @ResponseBody
    public DietLog findById(@RequestParam Long id) {
        return dietLogRepository.findById(id).orElse(null);
    }


}
