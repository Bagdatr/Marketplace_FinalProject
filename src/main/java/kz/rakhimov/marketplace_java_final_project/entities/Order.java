package kz.rakhimov.marketplace_java_final_project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Item item;

    private String email;

}