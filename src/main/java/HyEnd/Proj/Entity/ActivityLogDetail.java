package HyEnd.Proj.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "activityLogId")
    private ActivityLog activityLog;

    @ManyToOne
    @JoinColumn(name = "activityId")
    private Activity activity;

    @Column
    private Long duration;

    @Column
    private Double caloriesBurned;

}
