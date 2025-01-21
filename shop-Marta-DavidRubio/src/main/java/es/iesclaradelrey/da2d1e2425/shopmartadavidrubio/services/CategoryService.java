package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import org.yaml.snakeyaml.events.Event;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {

    Collection<Category> findAll();
    Optional<Category> findById(long id);


}
