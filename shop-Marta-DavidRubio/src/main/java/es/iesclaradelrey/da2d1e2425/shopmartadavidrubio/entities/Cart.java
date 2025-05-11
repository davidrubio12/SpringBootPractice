package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart", uniqueConstraints = @UniqueConstraint(columnNames = {"product_id"}))
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;


    @Column(nullable = false, length = 100)
    private int quantity;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modified;
    @Column(nullable = false, length = 200)
    private String productName;
    @Column(nullable = false, length = 100)
    private Double price;
    @Column(nullable = false, length = 1000)
    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Cart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.date = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.productName = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }
}
