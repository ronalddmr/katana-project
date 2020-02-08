package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ped_iva database table.
 * 
 */
@Entity
@Table(name="ped_iva")
@NamedQuery(name="PedIva.findAll", query="SELECT p FROM PedIva p")
public class PedIva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_iva", unique=true, nullable=false)
	private Integer idIva;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio", nullable=false)
	private Date fechaInicio;

	//bi-directional many-to-one association to PedDetallePedido
	@OneToMany(mappedBy="pedIva")
	private List<PedDetallePedido> pedDetallePedidos;

	public PedIva() {
	}

	public Integer getIdIva() {
		return this.idIva;
	}

	public void setIdIva(Integer idIva) {
		this.idIva = idIva;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public List<PedDetallePedido> getPedDetallePedidos() {
		return this.pedDetallePedidos;
	}

	public void setPedDetallePedidos(List<PedDetallePedido> pedDetallePedidos) {
		this.pedDetallePedidos = pedDetallePedidos;
	}

	public PedDetallePedido addPedDetallePedido(PedDetallePedido pedDetallePedido) {
		getPedDetallePedidos().add(pedDetallePedido);
		pedDetallePedido.setPedIva(this);

		return pedDetallePedido;
	}

	public PedDetallePedido removePedDetallePedido(PedDetallePedido pedDetallePedido) {
		getPedDetallePedidos().remove(pedDetallePedido);
		pedDetallePedido.setPedIva(null);

		return pedDetallePedido;
	}

}