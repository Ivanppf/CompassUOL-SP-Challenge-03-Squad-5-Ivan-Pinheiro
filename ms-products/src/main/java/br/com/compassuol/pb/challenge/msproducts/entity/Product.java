package br.com.compassuol.pb.challenge.msproducts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Past
    @CreationTimestamp
    private LocalDateTime date;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "image url cannot be blank")
    private String imgUrl;
    private BigDecimal price;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
            )
    private List<Category> categories;

}
