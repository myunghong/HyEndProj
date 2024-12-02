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
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String foodName;

    @Column
    private Float caloriesPerserving;
}
//{
//        "userId": "test1",
//        "foodList": [
//        {
//        "id": 1,
//        "served": 1.5
//        },
//        {
//        "id": 2,
//        "served": 1
//        }
//        ],
//        "dietDate": "2024-12-02"
//
//        }