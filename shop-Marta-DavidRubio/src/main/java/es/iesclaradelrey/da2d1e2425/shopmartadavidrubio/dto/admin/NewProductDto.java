package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewProductDto {

    @NotNull
  @NotBlank(message = "No puede estar vacio")
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Positive(message = "No puedo ser numero negativo")
    private Double price;

    @NotBlank
    @Positive(message = "No puede ser nuemro negativo")
    private Integer stockQuantity;

    @NotNull(message="Debes seleccionar una categoría")
    private Long categoryId;


}
