package ec.ambiensa.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vendedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "vendedor")
    private Set<Reserva> reservas = new HashSet<>();

    public Vendedor() {}
    public Vendedor(String nombre) { this.nombre = nombre; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
