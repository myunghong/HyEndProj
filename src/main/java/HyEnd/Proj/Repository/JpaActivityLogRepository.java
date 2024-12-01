package HyEnd.Proj.Repository;

import HyEnd.Proj.DTO.ActivityLogWithDetailsDTO;
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
    public void saveLog(ActivityLog activityLog) {
        em.persist(activityLog);
    }

    @Override
    public void updateLog(ActivityLog activityLog) {
        em.merge(activityLog);
    }

    @Override
    public void saveLogDetail(ActivityLogDetail activityLogDetail) {
        em.persist(activityLogDetail);
    }

    @Override
    public Optional<ActivityLogWithDetailsDTO> findLogById(Long id) {
        ActivityLog activityLog = em.find(ActivityLog.class, id);

        List<ActivityLogDetail> activityLogDetailList =  em.createQuery(
                        "select ald from ActivityLogDetail ald where ald.activityLog = :a", ActivityLogDetail.class)
                .setParameter("a", activityLog).getResultList();

        ActivityLogWithDetailsDTO activityLogEntity = new ActivityLogWithDetailsDTO(activityLog, activityLogDetailList);
        return Optional.of(activityLogEntity);
    }


    @Override
    public List<ActivityLog> findAll() {
        return em.createQuery("select al from ActivityLog  al", ActivityLog.class).getResultList();
    }
}
