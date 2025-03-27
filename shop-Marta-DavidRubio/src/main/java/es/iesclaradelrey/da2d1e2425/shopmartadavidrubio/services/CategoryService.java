package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryAlreadyExistsException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Collection<Category> findAll();
    Optional<Category> findById(long id);
    Page<Category> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir);
    void create(NewCategoryDto newCategoryDto);

    boolean existsByName(@NotNull @NotBlank(message = "Este campo no puede estar vacío") String name);

    void deleteById(Long id);

    void update(Long id, NewCategoryDto updatedCategory)
            throws CategoryNotFoundException, CategoryAlreadyExistsException;
}
