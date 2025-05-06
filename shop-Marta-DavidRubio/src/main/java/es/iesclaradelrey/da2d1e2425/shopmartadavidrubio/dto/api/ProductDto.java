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

    //Descripción,Precio,url de la imagen

    @NotNull
    private String description;

    @NotNull
    @Positive(message = "No puedo ser numero negativo")
    private Double price;

    @NotNull
    private String imageUrl;

}
