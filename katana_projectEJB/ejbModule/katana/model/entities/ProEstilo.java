package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pro_estilo database table.
 * 
 */
@Entity
@Table(name="pro_estilo")
@NamedQuery(name="ProEstilo.findAll", query="SELECT p FROM ProEstilo p")
public class ProEstilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estilo", unique=true, nullable=false)
	private Integer idEstilo;

	@Column(length=50)
	private String nombre;

	//bi-directional many-to-one association to ProProducto
	@OneToMany(mappedBy="proEstilo")
	private List<ProProducto> proProductos;

	public ProEstilo() {
	}

	public Integer getIdEstilo() {
		return this.idEstilo;
	}

	public void setIdEstilo(Integer idEstilo) {
		this.idEstilo = idEstilo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ProProducto> getProProductos() {
		return this.proProductos;
	}

	public void setProProductos(List<ProProducto> proProductos) {
		this.proProductos = proProductos;
	}

	public ProProducto addProProducto(ProProducto proProducto) {
		getProProductos().add(proProducto);
		proProducto.setProEstilo(this);

		return proProducto;
	}

	public ProProducto removeProProducto(ProProducto proProducto) {
		getProProductos().remove(proProducto);
		proProducto.setProEstilo(null);

		return proProducto;
	}

}