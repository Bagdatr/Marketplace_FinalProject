package kz.rakhimov.marketplace_java_final_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="t_comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private LocalDate date;
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

}