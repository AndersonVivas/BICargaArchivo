package ec.com.oncedev.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderTransaccciones")
public class OrderTransacccion implements Serializable {
	
	/** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cod;
	private String nombre;
	private String identificacion;
	private String valor;
	private String tipo;
	private String estadoValidacion;
	
	public OrderTransacccion() {		
	}

	public OrderTransacccion(Integer id, String cod, String nombre, String identificacion, String valor, String tipo,
			String estadoValidacion) {
		super();
		this.id = id;
		this.cod = cod;
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.valor = valor;
		this.tipo = tipo;
		this.estadoValidacion = estadoValidacion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstadoValidacion() {
		return estadoValidacion;
	}
	public void setEstadoValidacion(String estadoValidacion) {
		this.estadoValidacion = estadoValidacion;
	}
	
	

}
