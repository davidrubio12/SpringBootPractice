package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(length = 1000)
    private String imageUrl;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;




}
