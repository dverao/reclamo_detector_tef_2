package pe.uni.ia.reclamodetector.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_productos")
public class Producto {
	@Id
	private String idprod;
	private String descripcion;
	private int stock;
	private double precio;
	private int idtipo;
	private int estado;
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto(String idprod, String descripcion, int stock, double precio, int idtipo, int estado) {
		super();
		this.idprod = idprod;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.idtipo = idtipo;
		this.estado = estado;
	}
	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return "Producto[idprod="+idprod+", descripción="+descripcion+ ", "
				+ "stock=" + stock + ", precio="+precio+", idtipo="+idtipo+
				", estado="+ estado + "]";
	}
	
	
	
	
}
