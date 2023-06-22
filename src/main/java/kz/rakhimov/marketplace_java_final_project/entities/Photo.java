package kz.rakhimov.marketplace_java_final_project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_photos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
