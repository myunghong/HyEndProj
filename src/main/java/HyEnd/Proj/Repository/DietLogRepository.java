package HyEnd.Proj.Repository;

import HyEnd.Proj.Entity.DietLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietLogRepository extends JpaRepository<DietLog, Long> {
}
