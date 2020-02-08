package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ped_detalle_pedido database table.
 * 
 */
@Entity
@Table(name="ped_detalle_pedido")
@NamedQuery(name="PedDetallePedido.findAll", query="SELECT p FROM PedDetallePedido p")
public class PedDetallePedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle", unique=true, nullable=false)
	private Integer idDetalle;

	@Column(name="cantidad_detalle", nullable=false, precision=10)
	private BigDecimal cantidadDetalle;

	@Column(name="precio_descuento", precision=10, scale=2)
	private BigDecimal precioDescuento;

	@Column(name="precio_producto", precision=10, scale=2)
	private BigDecimal precioProducto;

	@Column(precision=10, scale=2)
	private BigDecimal subtotal;

	@Column(name="total_detalle", precision=10, scale=2)
	private BigDecimal totalDetalle;

	@Column(name="valor_iva", precision=10, scale=2)
	private BigDecimal valorIva;

	//bi-directional many-to-one association to PedIva
	@ManyToOne
	@JoinColumn(name="iva", nullable=false)
	private PedIva pedIva;

	//bi-directional many-to-one association to PedPedido
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private PedPedido pedPedido;

	//bi-directional many-to-one association to ProCatalogo
	@ManyToOne
	@JoinColumn(name="id_catalogo")
	private ProCatalogo proCatalogo;

	public PedDetallePedido() {
	}

	public Integer getIdDetalle() {
		return this.idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public BigDecimal getCantidadDetalle() {
		return this.cantidadDetalle;
	}

	public void setCantidadDetalle(BigDecimal cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}

	public BigDecimal getPrecioDescuento() {
		return this.precioDescuento;
	}

	public void setPrecioDescuento(BigDecimal precioDescuento) {
		this.precioDescuento = precioDescuento;
	}

	public BigDecimal getPrecioProducto() {
		return this.precioProducto;
	}

	public void setPrecioProducto(BigDecimal precioProducto) {
		this.precioProducto = precioProducto;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotalDetalle() {
		return this.totalDetalle;
	}

	public void setTotalDetalle(BigDecimal totalDetalle) {
		this.totalDetalle = totalDetalle;
	}

	public BigDecimal getValorIva() {
		return this.valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public PedIva getPedIva() {
		return this.pedIva;
	}

	public void setPedIva(PedIva pedIva) {
		this.pedIva = pedIva;
	}

	public PedPedido getPedPedido() {
		return this.pedPedido;
	}

	public void setPedPedido(PedPedido pedPedido) {
		this.pedPedido = pedPedido;
	}

	public ProCatalogo getProCatalogo() {
		return this.proCatalogo;
	}

	public void setProCatalogo(ProCatalogo proCatalogo) {
		this.proCatalogo = proCatalogo;
	}

}