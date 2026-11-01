package ec.ambiensa.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Servicio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "servicios")
    private Set<Villa> villas = new HashSet<>();

    public Servicio() {}
    public Servicio(String nombre) { this.nombre = nombre; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
