package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.dto.CategoryDTO;
import br.com.compassuol.pb.challenge.msproducts.dto.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import br.com.compassuol.pb.challenge.msproducts.entity.Product;
import br.com.compassuol.pb.challenge.msproducts.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDTO findProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        ProductDTO productDTO = mapToProductDto(product);
        return productDTO;
    }

    public List<ProductDTO> findAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map((product -> {
                    return mapToProductDto(product);
                })).toList();
        return productDTOS;
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product savedProduct = productRepository.save(mapToProduct(productDTO));
        productDTO.setID(savedProduct.getID());
        return productDTO;
    }

    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        Product product = mapToProduct(productDTO);
        ProductDTO savedProductDTO = mapToProductDto(productRepository.save(product));
        savedProductDTO.setID(oldProduct.getID());
        return savedProductDTO;
    }

    public String deleteProduct(int id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        productRepository.deleteById(id);
        return "products with id " + id + " deleted successfully";
    }


    private ProductDTO mapToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        List<CategoryDTO> categories = product.getCategories()
                .stream()
                .map((category) -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setID(category.getID());
            return categoryDTO;
        }).toList();
        productDTO.setCategories(categories);
        return productDTO;
    }

    private Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        List<Category> categories = productDTO.getCategories()
                .stream()
                .map((categoryDTO) -> {
                    return categoryRepository.findById(categoryDTO.getID()).get();
                }).toList();
        product.setCategories(categories);
        return product;
    }

}
