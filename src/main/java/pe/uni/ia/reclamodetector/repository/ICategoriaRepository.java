package pe.uni.ia.reclamodetector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.uni.ia.reclamodetector.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

}
