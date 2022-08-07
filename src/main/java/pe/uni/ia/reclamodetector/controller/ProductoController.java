package pe.uni.ia.reclamodetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pe.uni.ia.reclamodetector.model.Producto;
import pe.uni.ia.reclamodetector.repository.ICategoriaRepository;
import pe.uni.ia.reclamodetector.repository.IProductoRepository;

@Controller
public class ProductoController {
	@Autowired
	private IProductoRepository repo;
	@Autowired
	private ICategoriaRepository repoCat;
	
	@GetMapping("/listar")
	public String listadoProductos(Model model) {
		model.addAttribute("lstProductos", repo.findAll());
		System.out.println(repo.findAll());
		return "listado";
	}
	
	@GetMapping("/cargar")
	public String cargarPag(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("lstCategorias", repoCat.findAll());
		return "crudproductos";
	}
	
	@PostMapping("/guardar")
	public String grabarProd(@ModelAttribute Producto producto) {
		System.out.println(producto);
		repo.save(producto);
		return "crudproductos";
	}
	
	@PostMapping("/editar")
	public String buscarpro(@ModelAttribute Producto p, Model model) {
		model.addAttribute("producto", repo.findById(p.getIdprod()));
		return "crudproductos";
	}
	
}
