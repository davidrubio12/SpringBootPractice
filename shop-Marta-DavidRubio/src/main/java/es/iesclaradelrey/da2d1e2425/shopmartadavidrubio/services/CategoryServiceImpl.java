package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Collection<Category> findAll() {

        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page <Category> findAll(Integer pageNumber, Integer pageSize,String orderBy,String orderDir){
        Sort.Direction direction = orderDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageRequest = PageRequest.of(pageNumber-1, pageSize, Sort.by(direction, orderBy));
        return categoryRepository.findAll(pageRequest);
    }


    @Override
    public void create(NewCategoryDto newCategoryDto) {


        if (newCategoryDto.getName() == null || newCategoryDto.getName().isBlank()) {
            throw new IllegalArgumentException("Product name not found");
        }
        if (newCategoryDto.getDescription() == null) {
            throw new IllegalArgumentException("Product description not found");
        }

        Category category = new Category();
        category.setName(newCategoryDto.getName());
        category.setDescription(newCategoryDto.getDescription());


        categoryRepository.save(category);



    }
}
