package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class NewCategoryDto {

    @NotNull
    @NotBlank(message = "Este campo no puede estar vacío")
    private String name;

    @NotNull
    private String description;



}
