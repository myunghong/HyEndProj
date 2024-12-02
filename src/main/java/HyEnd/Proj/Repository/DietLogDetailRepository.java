package HyEnd.Proj.Repository;

import HyEnd.Proj.Entity.DietLogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietLogDetailRepository extends JpaRepository<DietLogDetail, Long> {
}
