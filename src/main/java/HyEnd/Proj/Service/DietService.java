package HyEnd.Proj.Service;


import HyEnd.Proj.DTO.DietDTO;
import HyEnd.Proj.DTO.FoodDTO;
import HyEnd.Proj.Entity.DietLog;
import HyEnd.Proj.Entity.DietLogDetail;
import HyEnd.Proj.Entity.Food;
import HyEnd.Proj.Entity.User;
import HyEnd.Proj.Repository.DietLogDetailRepository;
import HyEnd.Proj.Repository.DietLogRepository;
import HyEnd.Proj.Repository.FoodRepository;
import HyEnd.Proj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DietService {
    @Autowired
    private DietLogRepository dietLogRepository;

    @Autowired
    private DietLogDetailRepository dietLogDetailRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;


    public DietLog saveDietLog(DietDTO dietDTO) {
        List<FoodDTO> foodDTOList = dietDTO.getFoodList();
        Date dietDate = dietDTO.getDietDate();
        User user = userRepository.findByUserId(dietDTO.getUserId());
        DietLog dietLog = new DietLog();
        dietLog.setUser(user);
        dietLog.setDate(dietDate);
        dietLogRepository.save(dietLog);
        float totalCalories = 0f;

        for (FoodDTO foodDTO : foodDTOList) {
            Long id = foodDTO.getId();
            float served = foodDTO.getServed();

            Food food = foodRepository.findById(id).orElse(null);
            DietLogDetail dietLogDetail = new DietLogDetail();
            dietLogDetail.setFood(food);
            dietLogDetail.setDietLog(dietLog);
            dietLogDetail.setServings(served);
            float calories = (Objects.requireNonNull(food).getCaloriesPerserving() * served);
            dietLogDetail.setServingCalories(calories);
            totalCalories += calories;
            dietLogDetailRepository.save(dietLogDetail);
        }
        dietLog.setTotalCaloriesConsumed(totalCalories);
        return dietLogRepository.save(dietLog);
    }

    public List<DietLog> getAllDietLogs() {
        return dietLogRepository.findAll();
    }

    public Optional<DietLog> getDietLogById(Long id) {
        return dietLogRepository.findById(id);
    }

    public DietLogDetail saveDietLogDetail(DietLogDetail dietLogDetail) {
        return dietLogDetailRepository.save(dietLogDetail);
    }

}
