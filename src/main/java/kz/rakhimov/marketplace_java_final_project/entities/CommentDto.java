package kz.rakhimov.marketplace_java_final_project.entities;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CommentDto {
    private Long id;
    private String author;
    private LocalDate date;
    private String text;
    private Item item;

}