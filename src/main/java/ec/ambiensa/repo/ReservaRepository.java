package ec.ambiensa.repo;

import ec.ambiensa.model.Reserva;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Consulta 4: JOIN FETCH de tres asociaciones
    @Query("SELECT r FROM Reserva r JOIN FETCH r.villa JOIN FETCH r.cliente JOIN FETCH r.vendedor ORDER BY r.fecha")
    List<Reserva> reservasCompletas();

    // Consulta 5: agregacion con GROUP BY
    @Query("SELECT r.vendedor.nombre, COUNT(r), SUM(r.monto) FROM Reserva r GROUP BY r.vendedor.nombre ORDER BY SUM(r.monto) DESC")
    List<Object[]> ventasPorVendedor();
}
