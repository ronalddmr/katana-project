package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pro_talla database table.
 * 
 */
@Entity
@Table(name="pro_talla")
@NamedQuery(name="ProTalla.findAll", query="SELECT p FROM ProTalla p")
public class ProTalla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_talla", unique=true, nullable=false)
	private Integer idTalla;

	@Column(length=5)
	private String descripcion;

	//bi-directional many-to-one association to ProProducto
	@OneToMany(mappedBy="proTalla")
	private List<ProProducto> proProductos;

	public ProTalla() {
	}

	public Integer getIdTalla() {
		return this.idTalla;
	}

	public void setIdTalla(Integer idTalla) {
		this.idTalla = idTalla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ProProducto> getProProductos() {
		return this.proProductos;
	}

	public void setProProductos(List<ProProducto> proProductos) {
		this.proProductos = proProductos;
	}

	public ProProducto addProProducto(ProProducto proProducto) {
		getProProductos().add(proProducto);
		proProducto.setProTalla(this);

		return proProducto;
	}

	public ProProducto removeProProducto(ProProducto proProducto) {
		getProProductos().remove(proProducto);
		proProducto.setProTalla(null);

		return proProducto;
	}

}