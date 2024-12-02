package HyEnd.Proj.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DietDTO {
    private String userId;
    private List<FoodDTO> foodList;
    private Date dietDate;
}

