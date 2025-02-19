package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.commandlinerunners;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryCommandLineRunner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoryRepositoryCommandLineRunner(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public void run(String... args) throws Exception {


    //    categoryRepository.save(new Category(1L,"Cartas sueltas","Descripción","/images/478.jpg"));
      //  categoryRepository.save(new Category(2L,"Sobres","Descripción","/images/sobres.jpeg"));
        //categoryRepository.save(new Category( 3L,"Accesorios","Descripción","/images/accesorios.png"));


    }
}
