package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.ProductAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;



@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Product> findById(long id) {

        return productRepository.findById(id);
    }


    @Override
    public Collection<Product> findByCategoryId(long id) {
//         return productRepository.findAll()
//            .stream()
//             .filter(product -> product.getCategory().getId() == id)
//           .toList();
        return productRepository.findByCategoryId(id);
    }

    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize, String orderBy, String orderDir) {
        Sort.Direction direction = orderDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(direction, orderBy));
        return productRepository.findAll(pageRequest);
    }

    @Override
    public void create(NewProductDto newProductDto) {

        if (existsByName(newProductDto.getName())) {
            throw new ProductAlreadyExistsException("Ya existe un producto con ese nombre"
                    + newProductDto.getName());
        }

        Category category = categoryRepository.findById(newProductDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada "));


        Product product = new Product();

        product.setName(newProductDto.getName());
        product.setDescription(newProductDto.getDescription());
        product.setPrice(newProductDto.getPrice());
        product.setStockQuantity(newProductDto.getStockQuantity());
        product.setCategory(category);
        productRepository.save(product);


    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }


    @Override
    public void update(Long id, NewProductDto updatedProduct)
            throws ProductNotFoundException, ProductAlreadyExistsException, CategoryNotFoundException {


        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con id: " + id));


        if (!existingProduct.getName().equals(updatedProduct.getName()) &&
                productRepository.existsByName(updatedProduct.getName())) {
            throw new ProductAlreadyExistsException("Ya existe un producto con ese nombre");
        }


        Category category = categoryRepository.findById(updatedProduct.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada con id: " + updatedProduct.getCategoryId()));


        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(category);


        productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("No existe un producto con ese ID.");
        }

        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findWithFilters(String search, Long categoryId, String sortBy, String sortDir, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());

        if ((search == null || search.isBlank()) && categoryId == null) {
            return productRepository.findAll(pageable);
        }

        if (search != null && !search.isBlank() && categoryId != null) {
            return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategory_Id(
                    search, search, categoryId, pageable);
        }

        if (search != null && !search.isBlank()) {
            return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    search, search, pageable);
        }

        return productRepository.findByCategory_Id(categoryId, pageable);
    }


}
