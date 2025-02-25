package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NewProductDto {
    private String name;
    private String descripcion;
    private double price;
    private Integer stockQuantity;
    private Long categoryId;


}
