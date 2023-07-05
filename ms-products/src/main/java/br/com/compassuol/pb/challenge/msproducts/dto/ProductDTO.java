package br.com.compassuol.pb.challenge.msproducts.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private int id;
    private String description;
    private String name;
    private String imgUrl;
    private float price;
    private List<CategoryDTO> categories;

}