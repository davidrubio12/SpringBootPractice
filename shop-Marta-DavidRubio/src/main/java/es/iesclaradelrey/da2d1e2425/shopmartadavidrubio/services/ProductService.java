package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import aj.org.objectweb.asm.commons.Remapper;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(long id);

    Collection<Product> findByCategoryId(long id);

    Collection<Product> findAll();

    Page<Product> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir);

    void create(NewProductDto newProductDto);


    boolean existsByName(@NotNull @NotBlank(message = "Este campo no puede estar vacío") String name);

    void update(Long id, @Valid NewProductDto updatedProduct);

    void deleteById(Long id);

   Page <Product> findWithFilters(String search, Long cat, String sortBy, String sortDir, int pageNumber, int pageSize);
}
