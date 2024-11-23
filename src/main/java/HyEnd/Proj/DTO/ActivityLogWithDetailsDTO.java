package HyEnd.Proj.DTO;

import HyEnd.Proj.Entity.ActivityLog;
import HyEnd.Proj.Entity.ActivityLogDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActivityLogWithDetailsDTO {
    private ActivityLog activityLog;
    private List<ActivityLogDetail> activityLogDetails;

    // 생성자
    public ActivityLogWithDetailsDTO(ActivityLog activityLog, List<ActivityLogDetail> activityLogDetails) {
        this.activityLog = activityLog;
        this.activityLogDetails = activityLogDetails;
    }
}
