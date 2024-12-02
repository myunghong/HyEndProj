package HyEnd.Proj.Repository;

import HyEnd.Proj.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
