package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pro_catalogo database table.
 * 
 */
@Entity
@Table(name="pro_catalogo")
@NamedQuery(name="ProCatalogo.findAll", query="SELECT p FROM ProCatalogo p")
public class ProCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_catalogo", unique=true, nullable=false)
	private Integer idCatalogo;

	private Integer cantidad;

	@Column(name="cantidad_vendida")
	private Integer cantidadVendida;

	@Column(precision=10, scale=2)
	private BigDecimal descuento;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Boolean mostrar;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal precio;

	@Column(name="stock_maximo", nullable=false)
	private Integer stockMaximo;

	@Column(name="stock_minimo", nullable=false)
	private Integer stockMinimo;

	//bi-directional many-to-one association to PedDetallePedido
	@OneToMany(mappedBy="proCatalogo")
	private List<PedDetallePedido> pedDetallePedidos;

	//bi-directional many-to-one association to ProProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private ProProducto proProducto;

	public ProCatalogo() {
	}

	public Integer getIdCatalogo() {
		return this.idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCantidadVendida() {
		return this.cantidadVendida;
	}

	public void setCantidadVendida(Integer cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getMostrar() {
		return this.mostrar;
	}

	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStockMaximo() {
		return this.stockMaximo;
	}

	public void setStockMaximo(Integer stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public Integer getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public List<PedDetallePedido> getPedDetallePedidos() {
		return this.pedDetallePedidos;
	}

	public void setPedDetallePedidos(List<PedDetallePedido> pedDetallePedidos) {
		this.pedDetallePedidos = pedDetallePedidos;
	}

	public PedDetallePedido addPedDetallePedido(PedDetallePedido pedDetallePedido) {
		getPedDetallePedidos().add(pedDetallePedido);
		pedDetallePedido.setProCatalogo(this);

		return pedDetallePedido;
	}

	public PedDetallePedido removePedDetallePedido(PedDetallePedido pedDetallePedido) {
		getPedDetallePedidos().remove(pedDetallePedido);
		pedDetallePedido.setProCatalogo(null);

		return pedDetallePedido;
	}

	public ProProducto getProProducto() {
		return this.proProducto;
	}

	public void setProProducto(ProProducto proProducto) {
		this.proProducto = proProducto;
	}

}