package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductToCartDto {

    private Long productId;
    private int quantity;//unidades q se quieren añadir

}
