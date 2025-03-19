package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class NewCategoryDto {

    @NotNull
    @Size(min = 1, max = 50)
    @NotBlank(message = "Este campo no puede estar vacío")
    private String name;

    @NotNull
    @NotBlank(message = "La descripción no puede estar vacía.")
    private String description;



}
