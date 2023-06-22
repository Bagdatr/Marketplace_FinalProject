package kz.rakhimov.marketplace_java_final_project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
