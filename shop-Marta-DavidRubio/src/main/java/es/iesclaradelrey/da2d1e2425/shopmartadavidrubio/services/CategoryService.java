package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Collection<Category> findAll();
    Optional<Category> findById(long id);
    Page<Category> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir);
    void create(NewCategoryDto newCategoryDto);

}
