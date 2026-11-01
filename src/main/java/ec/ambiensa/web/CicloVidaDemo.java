package ec.ambiensa.web;

import ec.ambiensa.model.Cliente;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CicloVidaDemo {
    private final EntityManager em;
    public CicloVidaDemo(EntityManager em) { this.em = em; }

    /** Recorre los estados new, managed, detached y removed de una entidad Cliente. */
    @Transactional
    public List<String> ejecutar() {
        List<String> log = new ArrayList<>();
        Cliente c = new Cliente("Cliente Demo", "demo@correo.com");
        log.add("new: new Cliente() -> contains = " + em.contains(c) + ", id = " + c.getId());
        em.persist(c);
        log.add("managed: persist() -> contains = " + em.contains(c) + ", id = " + c.getId());
        em.flush();
        em.detach(c);
        log.add("detached: detach() -> contains = " + em.contains(c));
        Cliente m = em.merge(c);
        log.add("managed: merge() devuelve copia gestionada -> contains = " + em.contains(m));
        em.remove(m);
        log.add("removed: remove() -> contains = " + em.contains(m) + " (se borra al hacer commit)");
        return log;
    }
}
