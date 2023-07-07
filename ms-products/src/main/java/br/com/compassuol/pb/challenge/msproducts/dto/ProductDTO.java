package br.com.compassuol.pb.challenge.msproducts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {

    @Past
    private LocalDateTime date;
    @NotBlank(message = "cannot be blank")
    private String description;
    @NotBlank(message = "cannot be blank")
    private String name;
    @NotBlank(message = "cannot be blank")
    private String imgUrl;
    @NotNull(message = "cannot be blank")
    private BigDecimal price;
    @NotNull(message = "cannot be null")
    private List<CategoryDTO> categories;

}