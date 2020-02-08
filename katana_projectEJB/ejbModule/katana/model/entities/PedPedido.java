package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ped_pedido database table.
 * 
 */
@Entity
@Table(name="ped_pedido")
@NamedQuery(name="PedPedido.findAll", query="SELECT p FROM PedPedido p")
public class PedPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido", unique=true, nullable=false)
	private Integer idPedido;

	@Column(name="base_cero", nullable=false, precision=10, scale=2)
	private BigDecimal baseCero;

	@Column(name="base_imponible", nullable=false, precision=10, scale=2)
	private BigDecimal baseImponible;

	@Column(name="costo_envio", nullable=false, precision=10, scale=2)
	private BigDecimal costoEnvio;

	@Column(nullable=false, length=100)
	private String direccion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_entrega", nullable=false)
	private Date fechaEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_pedido", nullable=false)
	private Date fechaPedido;

	@Column(name="forma_pago", nullable=false, length=50)
	private String formaPago;

	@Column(nullable=false, length=20)
	private String identificacion;

	@Column(name="iva_total", nullable=false, precision=10, scale=2)
	private BigDecimal ivaTotal;

	@Column(nullable=false, length=100)
	private String referencia;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal subtotal;

	@Column(nullable=false, precision=15)
	private BigDecimal telefono;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to PedDetallePedido
	@OneToMany(mappedBy="pedPedido")
	private List<PedDetallePedido> pedDetallePedidos;

	//bi-directional many-to-one association to PedDivpolitica
	@ManyToOne
	@JoinColumn(name="id_divpolitica")
	private PedDivpolitica pedDivpolitica;

	//bi-directional many-to-one association to PedEstado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private PedEstado pedEstado;

	//bi-directional many-to-one association to UsuUsuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private UsuUsuario usuUsuario;

	public PedPedido() {
	}

	public Integer getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public BigDecimal getBaseCero() {
		return this.baseCero;
	}

	public void setBaseCero(BigDecimal baseCero) {
		this.baseCero = baseCero;
	}

	public BigDecimal getBaseImponible() {
		return this.baseImponible;
	}

	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}

	public BigDecimal getCostoEnvio() {
		return this.costoEnvio;
	}

	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public BigDecimal getIvaTotal() {
		return this.ivaTotal;
	}

	public void setIvaTotal(BigDecimal ivaTotal) {
		this.ivaTotal = ivaTotal;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<PedDetallePedido> getPedDetallePedidos() {
		return this.pedDetallePedidos;
	}

	public void setPedDetallePedidos(List<PedDetallePedido> pedDetallePedidos) {
		this.pedDetallePedidos = pedDetallePedidos;
	}

	public PedDetallePedido addPedDetallePedido(PedDetallePedido pedDetallePedido) {
		getPedDetallePedidos().add(pedDetallePedido);
		pedDetallePedido.setPedPedido(this);

		return pedDetallePedido;
	}

	public PedDetallePedido removePedDetallePedido(PedDetallePedido pedDetallePedido) {
		getPedDetallePedidos().remove(pedDetallePedido);
		pedDetallePedido.setPedPedido(null);

		return pedDetallePedido;
	}

	public PedDivpolitica getPedDivpolitica() {
		return this.pedDivpolitica;
	}

	public void setPedDivpolitica(PedDivpolitica pedDivpolitica) {
		this.pedDivpolitica = pedDivpolitica;
	}

	public PedEstado getPedEstado() {
		return this.pedEstado;
	}

	public void setPedEstado(PedEstado pedEstado) {
		this.pedEstado = pedEstado;
	}

	public UsuUsuario getUsuUsuario() {
		return this.usuUsuario;
	}

	public void setUsuUsuario(UsuUsuario usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

}