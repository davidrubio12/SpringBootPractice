package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.base.RepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Product,Long> implements ProductRepository {

}
