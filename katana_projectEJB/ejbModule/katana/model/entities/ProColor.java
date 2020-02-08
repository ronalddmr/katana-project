package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pro_color database table.
 * 
 */
@Entity
@Table(name="pro_color")
@NamedQuery(name="ProColor.findAll", query="SELECT p FROM ProColor p")
public class ProColor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_color", unique=true, nullable=false)
	private Integer idColor;

	@Column(length=50)
	private String nombre;

	//bi-directional many-to-one association to ProProducto
	@OneToMany(mappedBy="proColor")
	private List<ProProducto> proProductos;

	public ProColor() {
	}

	public Integer getIdColor() {
		return this.idColor;
	}

	public void setIdColor(Integer idColor) {
		this.idColor = idColor;
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
		proProducto.setProColor(this);

		return proProducto;
	}

	public ProProducto removeProProducto(ProProducto proProducto) {
		getProProductos().remove(proProducto);
		proProducto.setProColor(null);

		return proProducto;
	}

}