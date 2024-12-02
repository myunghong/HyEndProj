package HyEnd.Proj.Controller;

import HyEnd.Proj.Entity.Food;
import HyEnd.Proj.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {

    FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("save")
    public Food addFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }

    @GetMapping("get-all")
    public List<Food> getAllFood() {
        return foodService.getAllFood();
    }

    @GetMapping("get-food")
    public Food findFoodById(@RequestParam("id") long id) {
        Optional<Food> food = foodService.getFoodById(id);
        return food.orElse(null);
    }

}
