package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(nullable = false, length = 1000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name= "category_id",nullable = false)
    private Category category;




}
