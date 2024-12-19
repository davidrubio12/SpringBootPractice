package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class Category implements Entity<Long>{
    private Long id;
    private String name;
    private String description;
    private String imageUrl;


}
