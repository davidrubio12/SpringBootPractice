package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name","product_id"}) // Restringe una valoración por usuario y producto
        }
)
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 1000)
    private String comentary;

    @Column( length = 5 )
    private Double ratingNumber;

    @Column(nullable = false, length = 1000)
    private String imageUrl;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @PrePersist
    protected void onCreate() {
        this.date = new Date(); // Inicializa la fecha automáticamente
    }
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    public boolean isValidRatingNumber() {
        return ratingNumber != null && (ratingNumber * 10) % 5 == 0;
    }
}
