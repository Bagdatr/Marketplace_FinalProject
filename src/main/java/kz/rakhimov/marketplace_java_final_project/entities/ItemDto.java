package kz.rakhimov.marketplace_java_final_project.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private Category category;
    private String description;
    private double price;
    private double discount;
    private double discountPrice;
    private int rating;
    private int quantity;
    private List<Photo> photos;

}
