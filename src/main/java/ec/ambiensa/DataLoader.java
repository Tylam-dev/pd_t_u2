package ec.ambiensa;

import ec.ambiensa.model.*;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final EntityManager em;
    public DataLoader(EntityManager em) { this.em = em; }

    @Override @Transactional
    public void run(String... args) {
        Proyecto p1 = new Proyecto("Ciudad Celeste", "Samborondon");
        Proyecto p2 = new Proyecto("Villa Real", "Cuenca");
        em.persist(p1); em.persist(p2);

        TipoVilla t1 = new TipoVilla("Mediterranea", 3, 180);
        TipoVilla t2 = new TipoVilla("Moderna", 4, 240);
        em.persist(t1); em.persist(t2);

        Servicio piscina = new Servicio("Piscina"), seg = new Servicio("Seguridad 24h"), club = new Servicio("Club social");
        em.persist(piscina); em.persist(seg); em.persist(club);

        Villa[] v = {
            new Villa("CC-01", 145000, "DISPONIBLE", p1, t1),
            new Villa("CC-02", 168000, "DISPONIBLE", p1, t2),
            new Villa("CC-03", 152000, "RESERVADA", p1, t1),
            new Villa("VR-01", 132000, "DISPONIBLE", p2, t1),
            new Villa("VR-02", 189000, "RESERVADA", p2, t2),
        };
        v[0].getServicios().add(piscina); v[0].getServicios().add(seg);
        v[1].getServicios().add(seg);     v[1].getServicios().add(club);
        v[2].getServicios().add(seg);
        v[3].getServicios().add(club);
        v[4].getServicios().add(piscina); v[4].getServicios().add(seg); v[4].getServicios().add(club);
        for (Villa x : v) em.persist(x);

        Cliente c1 = new Cliente("Maria Torres", "maria@correo.com");
        Cliente c2 = new Cliente("Luis Andrade", "luis@correo.com");
        em.persist(c1); em.persist(c2);
        Vendedor s1 = new Vendedor("Carla Mena"), s2 = new Vendedor("Jorge Salas");
        em.persist(s1); em.persist(s2);

        em.persist(new Reserva(LocalDate.of(2026, 9, 12), 5000, v[2], c1, s1));
        em.persist(new Reserva(LocalDate.of(2026, 10, 3), 8000, v[4], c2, s1));
        em.persist(new Reserva(LocalDate.of(2026, 10, 20), 4000, v[2], c2, s2));
    }
}
