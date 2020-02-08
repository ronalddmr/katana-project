package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pro_tipo_producto database table.
 * 
 */
@Entity
@Table(name="pro_tipo_producto")
@NamedQuery(name="ProTipoProducto.findAll", query="SELECT p FROM ProTipoProducto p")
public class ProTipoProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_producto", unique=true, nullable=false)
	private Integer idTipoProducto;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to ProProducto
	@OneToMany(mappedBy="proTipoProducto")
	private List<ProProducto> proProductos;

	public ProTipoProducto() {
	}

	public Integer getIdTipoProducto() {
		return this.idTipoProducto;
	}

	public void setIdTipoProducto(Integer idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
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
		proProducto.setProTipoProducto(this);

		return proProducto;
	}

	public ProProducto removeProProducto(ProProducto proProducto) {
		getProProductos().remove(proProducto);
		proProducto.setProTipoProducto(null);

		return proProducto;
	}

}