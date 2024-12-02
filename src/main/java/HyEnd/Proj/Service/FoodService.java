package HyEnd.Proj.Service;

import HyEnd.Proj.Entity.Food;
import HyEnd.Proj.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public Food addFood(Food food) {
        // 새로운 데이터만 추가
        if (food.getId() != null && foodRepository.existsById(food.getId())) {
            throw new IllegalArgumentException("Food with ID already exists.");
        }
        return foodRepository.save(food);
    }

    public Food updateFood(Food food) {
        // 존재하는 데이터만 수정
        if (food.getId() == null || !foodRepository.existsById(food.getId())) {
            throw new IllegalArgumentException("Food with ID does not exist.");
        }
        return foodRepository.save(food);
    }
    public void deleteFood(Food food) {
        foodRepository.delete(food);
    }


}
