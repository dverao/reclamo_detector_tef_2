package pe.uni.ia.reclamodetector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.uni.ia.reclamodetector.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, String> {

}
