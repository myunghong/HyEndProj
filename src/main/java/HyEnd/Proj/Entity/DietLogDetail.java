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
public class DietLogDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dietLogId", nullable = false)
    private DietLog dietLog;

    @ManyToOne
    @JoinColumn(name = "foodId")
    private Food food;

    @Column
    private float servings; //몇인분인가?

    @Column
    private float servingCalories; //몇인분 * 1인분 칼로리

}
