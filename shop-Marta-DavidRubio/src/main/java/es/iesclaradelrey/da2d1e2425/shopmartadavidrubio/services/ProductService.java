package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(long id);

    Collection<Product> findByCategoryId(long id);

    Collection<Product> findAll();

    Page<Product> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir);

    void create(NewProductDto newProductDto);


}
