package HyEnd.Proj.Repository;

import HyEnd.Proj.DTO.ActivityLogWithDetailsDTO;
import HyEnd.Proj.Entity.ActivityLog;
import HyEnd.Proj.Entity.ActivityLogDetail;

import java.util.List;
import java.util.Optional;

public interface ActivityLogRepository {
    void saveLog(ActivityLog activityLog);
    void updateLog(ActivityLog activityLog);
    void saveLogDetail(ActivityLogDetail activityLogDetail);
    Optional<ActivityLogWithDetailsDTO> findLogById(Long id);
    List<ActivityLog> findAll();

}
