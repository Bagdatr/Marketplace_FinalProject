package kz.rakhimov.marketplace_java_final_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=1000)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @Column(length=5000)
    private String description;
    private double price;
    private double discount;
    private double discountPrice;
    private int rating;
    private int quantity;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Photo> photos;

}
