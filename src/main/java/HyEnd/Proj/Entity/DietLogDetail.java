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
public class DietLogDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dietLogId", nullable = false)
    private DietLog dietLog;

    @OneToOne
    @JoinColumn(name = "foodId")
    private Food food;

    @Column
    private Long gram;

    @Column
    private Long servingCalories;

}
