package ec.ambiensa.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proyecto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ciudad;

    @OneToMany(mappedBy = "proyecto")
    private List<Villa> villas = new ArrayList<>();

    public Proyecto() {}
    public Proyecto(String nombre, String ciudad) { this.nombre = nombre; this.ciudad = ciudad; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public List<Villa> getVillas() { return villas; }
}
