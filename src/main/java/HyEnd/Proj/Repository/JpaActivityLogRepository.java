package HyEnd.Proj.Repository;

import HyEnd.Proj.Entity.ActivityLog;
import HyEnd.Proj.Entity.ActivityLogDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaActivityLogRepository implements ActivityLogRepository {
    private final EntityManager em;

    public JpaActivityLogRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long saveLog(ActivityLog activityLog) {
        em.persist(activityLog);
        return activityLog.getId();
    }
    public void updateLog(ActivityLog activityLog) {
        em.merge(activityLog);
    }

    @Override
    public Long saveLogDetail(ActivityLogDetail activityLogDetail) {
        em.persist(activityLogDetail);
        return activityLogDetail.getId();
    }

    @Override
    public Optional<ActivityLog> findLogById(Long id) {
        ActivityLog activityLog = em.find(ActivityLog.class, id);
        return Optional.ofNullable(activityLog);
    }

    @Override
    public Optional<ActivityLogDetail> findLogDetailById(Long id) {
        ActivityLogDetail activityLogDetail = em.find(ActivityLogDetail.class, id);
        return Optional.ofNullable(activityLogDetail);
    }


    @Override
    public List<ActivityLog> findAll() {
        return List.of();
    }
}
