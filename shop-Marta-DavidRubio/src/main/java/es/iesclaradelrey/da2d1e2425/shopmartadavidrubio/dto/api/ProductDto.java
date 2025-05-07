package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;

}
