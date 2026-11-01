package ec.ambiensa.web;

import ec.ambiensa.repo.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    private final VillaRepository villas;
    private final ReservaRepository reservas;
    private final ProyectoRepository proyectos;
    private final CicloVidaDemo demo;

    public WebController(VillaRepository v, ReservaRepository r, ProyectoRepository p, CicloVidaDemo d) {
        this.villas = v; this.reservas = r; this.proyectos = p; this.demo = d;
    }

    @GetMapping("/")
    public String inicio(@RequestParam(defaultValue = "Ciudad Celeste") String proyecto,
                         @RequestParam(defaultValue = "160000") double max,
                         @RequestParam(required = false) Integer reajuste,
                         Model m) {
        m.addAttribute("proyecto", proyecto);
        m.addAttribute("max", max);
        m.addAttribute("c1", villas.buscarPorProyectoYPrecio(proyecto, max));
        m.addAttribute("c2", villas.disponiblesConDetalle());
        m.addAttribute("c4", reservas.reservasCompletas());
        m.addAttribute("c5", reservas.ventasPorVendedor());
        m.addAttribute("reajuste", reajuste);
        m.addAttribute("ciclo", demo.ejecutar());
        return "index";
    }

    @PostMapping("/reajustar")
    @Transactional
    public String reajustar(@RequestParam Long proyectoId, @RequestParam double porcentaje) {
        int n = villas.reajustarPrecios(proyectoId, 1 + porcentaje / 100.0);
        return "redirect:/?reajuste=" + n;
    }
}
