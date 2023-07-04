package br.com.compassuol.pb.challenge.msproducts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @NotBlank
    private String description;
    @NotBlank
    private String name;
    @NotBlank
    private String imgUrl;
    @NotNull
    private float price;
    @JoinColumn(name = "categoryId", foreignKey = @ForeignKey(name = "fk_product_category"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

}
