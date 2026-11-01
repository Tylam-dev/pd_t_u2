package ec.ambiensa.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoVilla {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int dormitorios;
    private double areaM2;

    @OneToMany(mappedBy = "tipo")
    private List<Villa> villas = new ArrayList<>();

    public TipoVilla() {}
    public TipoVilla(String nombre, int dormitorios, double areaM2) {
        this.nombre = nombre; this.dormitorios = dormitorios; this.areaM2 = areaM2;
    }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getDormitorios() { return dormitorios; }
    public double getAreaM2() { return areaM2; }
}
