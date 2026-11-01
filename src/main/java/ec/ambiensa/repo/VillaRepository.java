package ec.ambiensa.repo;

import ec.ambiensa.model.Villa;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VillaRepository extends JpaRepository<Villa, Long> {

    // Consulta 1: SELECT con parametros nombrados
    @Query("SELECT v FROM Villa v WHERE v.proyecto.nombre = :proyecto AND v.precio <= :max ORDER BY v.precio")
    List<Villa> buscarPorProyectoYPrecio(@Param("proyecto") String proyecto, @Param("max") double max);

    // Consulta 2: JOIN FETCH para evitar el problema N+1
    @Query("SELECT DISTINCT v FROM Villa v JOIN FETCH v.tipo JOIN FETCH v.proyecto " +
           "LEFT JOIN FETCH v.servicios WHERE v.estado = 'DISPONIBLE' ORDER BY v.codigo")
    List<Villa> disponiblesConDetalle();

    // Consulta 3: UPDATE masivo
    @Modifying
    @Query("UPDATE Villa v SET v.precio = v.precio * :factor WHERE v.proyecto.id = :proyectoId AND v.estado = 'DISPONIBLE'")
    int reajustarPrecios(@Param("proyectoId") Long proyectoId, @Param("factor") double factor);
}
