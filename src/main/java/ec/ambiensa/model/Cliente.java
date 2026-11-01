package ec.ambiensa.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    @OneToMany(mappedBy = "cliente")
    private Set<Reserva> reservas = new HashSet<>();

    public Cliente() {}
    public Cliente(String nombre, String email) { this.nombre = nombre; this.email = email; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}
