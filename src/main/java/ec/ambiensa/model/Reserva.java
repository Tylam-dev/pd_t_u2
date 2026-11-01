package ec.ambiensa.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private double monto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "villa_id")
    private Villa villa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    public Reserva() {}
    public Reserva(LocalDate fecha, double monto, Villa villa, Cliente cliente, Vendedor vendedor) {
        this.fecha = fecha; this.monto = monto;
        this.villa = villa; this.cliente = cliente; this.vendedor = vendedor;
    }
    public Long getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public double getMonto() { return monto; }
    public Villa getVilla() { return villa; }
    public Cliente getCliente() { return cliente; }
    public Vendedor getVendedor() { return vendedor; }
}
