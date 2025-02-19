package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false, length = 10)
    private Double price;
    @Column( length = 1000)
    private String imageUrl;
    @Column(nullable = false, length = 100)
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name= "category_id",nullable = false)
    private Category category;


    // Relación con Rating
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Rating> ratings;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Cart> carts;

}
