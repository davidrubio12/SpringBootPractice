package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    @NotNull
    @NotBlank(message = "No puede estar vacio")
    private String name;



    @NotNull
    private String description;

    @NotNull
    @Positive(message = "No puedo ser numero negativo")
    private Double price;

    @NotNull

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
}
