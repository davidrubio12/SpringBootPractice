package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.commandlinerunners;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryCommandLineRunner implements CommandLineRunner {
    private final ProductRepository productRepository;

    public ProductRepositoryCommandLineRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       // productRepository.save(new Product(11L,"Path of Exile","Descipción",null,1L,"/images/478.jpg"));
        //productRepository.save(new Product(12L,"Path of Kyado","Descipción",null,1L,null));
   //     productRepository.save(new Product(13L,"Path of Maqui","Descipción",null,1L,null));
     //   productRepository.save(new Product(14L,"Path of live","Descipción",null,1L,null));
     //   productRepository.save(new Product(15L,"Cimientos","Descipción",null,2L,null));
     //   productRepository.save(new Product(16L,"Arcane","Descipción",null,2L,null));



    }
}
