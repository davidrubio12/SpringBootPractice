package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    private List<CartItemDto> items;
    private int totalQuantity;
    private double totalPrice;
}
