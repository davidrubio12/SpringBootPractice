package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long productId;
    private String productName;
    private String imageUrl;
    private double unitPrice;
    private int quantity;
    private double subtotal;
}
