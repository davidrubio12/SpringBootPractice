package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "app_users")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 200)
    @NotNull
    @Column(name = "username", nullable = false, length = 200)
    private String username;


    @Size(max = 200)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;

    @Size(max = 200)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    @Size(max = 200)
    @NotNull
    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Size(max = 200)
    @NotNull
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    //Relacion
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

}