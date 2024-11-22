package HyEnd.Proj.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RequestActivitiesDTO {
    private String userId;
    private Date date;
    private List<ActivitiesGroupDTO> group;
}
