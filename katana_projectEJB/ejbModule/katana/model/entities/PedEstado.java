package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ped_estado database table.
 * 
 */
@Entity
@Table(name="ped_estado")
@NamedQuery(name="PedEstado.findAll", query="SELECT p FROM PedEstado p")
public class PedEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado", unique=true, nullable=false)
	private Integer idEstado;

	@Column(nullable=false, length=500)
	private String descripcion;

	@Column(length=50)
	private String nombre;

	//bi-directional many-to-one association to PedPedido
	@OneToMany(mappedBy="pedEstado")
	private List<PedPedido> pedPedidos;

	public PedEstado() {
	}

	public Integer getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PedPedido> getPedPedidos() {
		return this.pedPedidos;
	}

	public void setPedPedidos(List<PedPedido> pedPedidos) {
		this.pedPedidos = pedPedidos;
	}

	public PedPedido addPedPedido(PedPedido pedPedido) {
		getPedPedidos().add(pedPedido);
		pedPedido.setPedEstado(this);

		return pedPedido;
	}

	public PedPedido removePedPedido(PedPedido pedPedido) {
		getPedPedidos().remove(pedPedido);
		pedPedido.setPedEstado(null);

		return pedPedido;
	}

}