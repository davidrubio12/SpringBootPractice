package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryNotEmptyException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
        if (newCategoryDto.getDescription() == null) {
            throw new IllegalArgumentException("La descripción de la categoría no puede ser nula.");
        }


        if(existsByName(newCategoryDto.getName())) {
            throw new CategoryAlreadyExistsException("Ya existe una categoria con ese nombre."
                    + newCategoryDto.getName());
        }

        Category category = new Category();
        category.setName(newCategoryDto.getName());
        category.setDescription(newCategoryDto.getDescription());


        categoryRepository.save(category);



    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("No existe una categoría con ese ID.");
        }


        if (productRepository.existsByCategoryId(id)) {
            throw new CategoryNotEmptyException("La categoría no se puede borrar porque tiene productos asociados.");
        }

        categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, NewCategoryDto updatedCategory)
            throws CategoryNotFoundException, CategoryAlreadyExistsException {


        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada"));


        if (!existingCategory.getName().equals(updatedCategory.getName()) &&
                categoryRepository.existsByName(updatedCategory.getName())) {
            throw new CategoryAlreadyExistsException("Ya existe una categoría con ese nombre");
        }


        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());


        categoryRepository.save(existingCategory);
    }


}
