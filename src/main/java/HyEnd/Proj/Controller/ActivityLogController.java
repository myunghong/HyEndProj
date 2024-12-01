package HyEnd.Proj.Controller;

import HyEnd.Proj.DTO.ActivityLogWithDetailsDTO;
import HyEnd.Proj.DTO.RequestActivitiesDTO;
import HyEnd.Proj.Entity.ActivityLog;
import HyEnd.Proj.Service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * 예시 Request Body Json
{
    "user_id": 123,
    "date": "2024-11-23T04:50:02.133+09:00",
    "group": [
        {
            "id": 4,
            "duration":30
        },
        {
            "id": 6,
            "duration": 40
        }
    ]
}
 */
@Controller
@RequestMapping("/activities-log") // 공통 경로
public class ActivityLogController {

    ActivityLogService activityLogService;

    @Autowired
    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    // localhost:8080/activities-log/receive-log + body에 위 예시대로 Json
    @PostMapping("/receive-log")
    @ResponseBody
    public Long saveActivityLog(@RequestBody RequestActivitiesDTO requestActivitiesDTO) {
        return activityLogService.save(requestActivitiesDTO);
    }

    // localhost:8080/activities-log/all
    @GetMapping("/all")
    @ResponseBody
    public List<ActivityLog> getActivityLogAll() {
        // db 활동내역 전부 받아보기
        return activityLogService.findAll();
    }

    // localhost:8080/activities-log/id?id=1
    @GetMapping("/id")
    @ResponseBody
    public Optional<ActivityLogWithDetailsDTO> getActivityLogById(@RequestParam("id") Long id) {
        return activityLogService.findById(id);
    }

}
