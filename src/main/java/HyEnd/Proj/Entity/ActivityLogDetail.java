package HyEnd.Proj.Entity;

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
    @JoinColumn(name = "activityLogId")
    private ActivityLog activityLog;

    @OneToOne
    @JoinColumn(name = "activityId")
    private Activity activity;

    @Column
    private String duration;

    @Column
    private Float caloriesBurned;

}
