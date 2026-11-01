package ec.ambiensa.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Villa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private double precio;
    private String estado; // DISPONIBLE, RESERVADA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private TipoVilla tipo;

    @ManyToMany
    @JoinTable(name = "villa_servicio",
        joinColumns = @JoinColumn(name = "villa_id"),
        inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private Set<Servicio> servicios = new HashSet<>();

    @OneToMany(mappedBy = "villa")
    private Set<Reserva> reservas = new HashSet<>();

    public Villa() {}
    public Villa(String codigo, double precio, String estado, Proyecto proyecto, TipoVilla tipo) {
        this.codigo = codigo; this.precio = precio; this.estado = estado;
        this.proyecto = proyecto; this.tipo = tipo;
    }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public double getPrecio() { return precio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Proyecto getProyecto() { return proyecto; }
    public TipoVilla getTipo() { return tipo; }
    public Set<Servicio> getServicios() { return servicios; }
}
