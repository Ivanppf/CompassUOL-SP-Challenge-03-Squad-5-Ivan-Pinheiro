package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import br.com.compassuol.pb.challenge.msproducts.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category findCategory(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(int id, Category newCategory) {
        Category oldCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
        newCategory.setId(oldCategory.getId());
        return categoryRepository.save(newCategory);
    }

    public String deleteCategory(int id) {
        categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
        categoryRepository.deleteById(id);
        return "Category with id " + id + " deleted successfully";
    }

}
